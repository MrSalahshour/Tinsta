package ir.sharif.math.ap99_2.tinsta_shared.response;

import ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model.Comment;

public class GetCommentResponse extends Response{
    private Comment comment;

    public GetCommentResponse(Comment comment) {
        this.comment = comment;
    }

    public GetCommentResponse() {
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
