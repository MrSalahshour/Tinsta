package ir.sharif.math.ap99_2.tinsta_shared.response;

public class GetFileResponse extends Response {
    private String name;
    private String content;

    public GetFileResponse() {
    }

    public GetFileResponse(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
