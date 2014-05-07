package util;

public class Print {

    public static String params() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        return className + "." + methodName;
    }
}
