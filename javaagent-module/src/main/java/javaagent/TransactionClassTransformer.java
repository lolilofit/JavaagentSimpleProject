package javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class TransactionClassTransformer implements ClassFileTransformer {
    public static int loadedCount = 0;

    public static int getLoadedCount() {
        return loadedCount;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        loadedCount++;
        return classfileBuffer;
    }
}
