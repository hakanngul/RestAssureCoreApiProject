package core.utils;

import java.util.function.Supplier;

public class RetryPolicy {
    public <T> T executeWithRetry(Supplier<T> action, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                return action.get();
            } catch (Exception e) {
                attempt++;
                if (attempt >= maxRetries) {
                    throw e;
                }
            }
        }
        return null;
    }
}
