package ir.sharif.math.ap99_2.tinsta_shared.response;

public class GetStringResponse extends Response {
    private String content;

    public GetStringResponse(String content) {
        this.content = content;
    }

    public GetStringResponse() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
