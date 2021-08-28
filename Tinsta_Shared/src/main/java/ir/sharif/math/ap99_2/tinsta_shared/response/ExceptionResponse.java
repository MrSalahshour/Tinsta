package ir.sharif.math.ap99_2.tinsta_shared.response;

public class ExceptionResponse extends Response {
    private Exception exception;

    public ExceptionResponse(Exception exception) {
        this.exception = exception;
    }

    public ExceptionResponse() {
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
