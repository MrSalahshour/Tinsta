package ir.sharif.math.ap99_2.tinsta_shared.response;

public class GetIntegerResponse extends Response {
    private Integer integer;

    public GetIntegerResponse(Integer integer) {
        this.integer = integer;
    }

    public GetIntegerResponse() {
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }
}
