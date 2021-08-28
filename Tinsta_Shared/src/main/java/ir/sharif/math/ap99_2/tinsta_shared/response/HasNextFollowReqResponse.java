package ir.sharif.math.ap99_2.tinsta_shared.response;

public class HasNextFollowReqResponse extends Response {
    private boolean hasNext;

    public HasNextFollowReqResponse(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public HasNextFollowReqResponse() {
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
