package ir.sharif.math.ap99_2.tinsta_shared.response;

public class HasPreviousFollowReqResponse extends Response {
    private boolean hasPrevious;

    public HasPreviousFollowReqResponse(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public HasPreviousFollowReqResponse() {
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
