package ir.sharif.math.ap99_2.tinsta_shared.response;

public class DontHaveFollowReqResponse extends Response{
    private boolean dontHave;

    public DontHaveFollowReqResponse(boolean dontHave) {
        this.dontHave = dontHave;
    }

    public DontHaveFollowReqResponse() {
    }

    public boolean isDontHave() {
        return dontHave;
    }

    public void setDontHave(boolean dontHave) {
        this.dontHave = dontHave;
    }
}
