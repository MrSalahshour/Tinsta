package ir.sharif.math.ap99_2.tinsta_shared.response;

public class GroupExistResponse extends Response {
    private boolean exist;

    public GroupExistResponse(boolean exist) {
        this.exist = exist;
    }

    public GroupExistResponse() {
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
