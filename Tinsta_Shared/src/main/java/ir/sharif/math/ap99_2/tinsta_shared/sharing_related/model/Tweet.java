package ir.sharif.math.ap99_2.tinsta_shared.sharing_related.model;



import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

import java.util.LinkedList;


public class Tweet extends Message {
    private LinkedList<Integer> commentsId;
    private LinkedList<Integer> likesId;
    private LinkedList<Integer> retweetsOwnerId;
    private int reports;
    private Integer currentCommentId;


    public Tweet(Integer owner, String content, String imagePath) {
        super(owner, content,imagePath);
        this.commentsId = new LinkedList<>();
        this.likesId = new LinkedList<>();
        this.retweetsOwnerId = new LinkedList<>();
        this.reports = 0;
        this.currentCommentId = null;

    }

    public Tweet() {
    }

    public void setCommentsId(LinkedList<Integer> commentsId) {
        this.commentsId = commentsId;
    }

    public int getReports() {
        return reports;
    }


    public LinkedList<Integer> getCommentsId() {
        return commentsId;
    }

    public LinkedList<Integer> getLikesId() {
        return likesId;
    }

    public LinkedList<Integer> getRetweetsOwnerId() {
        return this.retweetsOwnerId;
    }

    public Integer getCurrentCommentId() {
        return currentCommentId;
    }

    public void setCurrentCommentId(Integer currentCommentId) {
        this.currentCommentId = currentCommentId;
    }

    public boolean hasNextComment() {
        return !(commentsId.indexOf(currentCommentId) == commentsId.size() - 1);
    }

    public boolean hasPreviousComment() {
        return !(commentsId.indexOf(currentCommentId) == 0);
    }

    public void goNextComment() {
        if (hasNextComment())
            setCurrentCommentId(commentsId.get(commentsId.indexOf(getCurrentCommentId()) + 1));
    }

    public void goPreviousComment() {
        if (hasPreviousComment())
            setCurrentCommentId(commentsId.get(commentsId.indexOf(getCurrentCommentId()) - 1));
    }

    public Comment addComments(User user, String content,String imagePath) {
        Comment comment = new Comment(this.getId(), user.getId(), content,imagePath);
        this.commentsId.add(comment.getId());
        setCurrentCommentId(commentsId.getLast());
        return comment;

    }

    public void addLikes(Like like) {
        this.likesId.add(like.getId());
    }

    public void addToRetweets(User user,User owner) {
        this.retweetsOwnerId.add(user.getId());
        user.getMenu().getHomePage().getMyTweetsId().add(this.getId());
    }


    public void addReport() {
        reports++;
    }



}
