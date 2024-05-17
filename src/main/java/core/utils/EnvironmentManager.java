package core.utils;

public class EnvironmentManager {
    private static ThreadLocal<String> environment = new ThreadLocal<>();

    public static void setEnvironment(String env) {
        environment.set(env);
    }

    public static String getEnvironment() {
        return environment.get();
    }
}
