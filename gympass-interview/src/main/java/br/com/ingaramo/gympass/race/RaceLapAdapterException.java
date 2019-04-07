package br.com.ingaramo.gympass.race;

public class RaceLapAdapterException extends RuntimeException {
    public RaceLapAdapterException() {
        super();
    }

    public RaceLapAdapterException(String message) {
        super(message);
    }

    public RaceLapAdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RaceLapAdapterException(Throwable cause) {
        super(cause);
    }

    protected RaceLapAdapterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
