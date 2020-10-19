package javaagent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class JavaAgentImpl {
    private static void modifyCode() throws NotFoundException, CannotCompileException, IOException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cf = cp.get("javaagent.TransactionProcessor");
        CtMethod main = cf.getDeclaredMethod("main");

        main.insertAfter("System.out.println(\"Loaded classes: \" + javaagent.TransactionClassTransformer.getLoadedCount());");

        CtMethod processTransaction = cf.getDeclaredMethod("processTransaction");

        processTransaction.addLocalVariable("startTime", CtPrimitiveType.longType);
        processTransaction.addLocalVariable("endTime", CtPrimitiveType.longType);
        processTransaction.addLocalVariable("callingTime", CtPrimitiveType.longType);

        processTransaction.insertBefore("{startTime = System.currentTimeMillis();}");

        processTransaction.insertAfter("{" +
                "endTime = System.currentTimeMillis();" +
                "callingTime = endTime - startTime;" +
                "if(callingTime > javaagent.TimeCounters.getMaxTime())" +
                "javaagent.TimeCounters.setMaxTime(callingTime);" +
                "if(callingTime < javaagent.TimeCounters.getMinTime())" +
                "javaagent.TimeCounters.setMinTime(callingTime);" +
                "javaagent.TimeCounters.setAverageTime(callingTime + javaagent.TimeCounters.getAverageTime());" +
                "javaagent.TimeCounters.setNumberCalls(javaagent.TimeCounters.getNumberCalls() + 1);" +
                "}");

        main.insertAfter("{System.out.println(\"Min time (ms): \" + javaagent.TimeCounters.getMinTime());}");
        main.insertAfter("{System.out.println(\"Max time (ms): \" + javaagent.TimeCounters.getMaxTime());}");
        main.insertAfter("{System.out.println(\"Average time (ms): \" + javaagent.TimeCounters.getAverageTime()/javaagent.TimeCounters.getNumberCalls());}");

        processTransaction.insertBefore("{txNum += 99;}");

        cf.writeFile();
    }

    public static void premain(String args, Instrumentation inst) throws NotFoundException, CannotCompileException, IOException {
        inst.addTransformer(new TransactionClassTransformer());
        modifyCode();
    }
}
