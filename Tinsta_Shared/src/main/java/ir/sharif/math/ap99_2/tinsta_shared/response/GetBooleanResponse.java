package ir.sharif.math.ap99_2.tinsta_shared.response;

public class GetBooleanResponse extends Response {
    private boolean aBoolean;

    public GetBooleanResponse(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public GetBooleanResponse() {
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
