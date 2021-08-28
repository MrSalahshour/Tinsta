package ir.sharif.math.ap99_2.tinsta_shared.notification.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.profile.model.Profile;
import ir.sharif.math.ap99_2.tinsta_shared.user_info.model.UserName;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


import java.io.Serializable;
import java.util.LinkedList;

@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Notification.class)
public class Notification implements Serializable {
    private LinkedList<Integer> followRequestsUserId;
    private LinkedList<String> systemMessages;
    private Integer currentFollowRequestId;
    private boolean showRejectRequest;

    public Notification() {
        this.followRequestsUserId = new LinkedList<>();
        this.showRejectRequest = true;
        this.systemMessages = new LinkedList<>();
        this.currentFollowRequestId = null;
    }



    public void setFollowRequestsUserId(LinkedList<Integer> followRequestsUserId) {
        this.followRequestsUserId = followRequestsUserId;
    }

    public void setSystemMessages(LinkedList<String> systemMessages) {
        this.systemMessages = systemMessages;
    }

    public Integer getCurrentFollowRequestId() {
        return currentFollowRequestId;
    }

    public void setCurrentFollowRequestId(Integer currentFollowRequestId) {
        this.currentFollowRequestId = currentFollowRequestId;
    }

    public boolean isShowRejectRequest() {
        return showRejectRequest;
    }

    public void setShowRejectRequest(boolean showRejectRequest) {
        this.showRejectRequest = showRejectRequest;
    }

    public LinkedList<String> getSystemMessages() {
        return systemMessages;
    }

    public void addMessages(String messages) {
        this.systemMessages.add(messages);
    }

    public LinkedList<Integer> getFollowRequestsUserId() {
        return followRequestsUserId;
    }

    public void addFollowRequests(User user) {
        Integer userId = user.getId();
        this.followRequestsUserId.add(userId);
        setCurrentFollowRequestId(followRequestsUserId.getLast());
    }

    public boolean hasNextFollowRequest() {
        if (followRequestsUserId.isEmpty())
            return false;
        return !followRequestsUserId.getLast()
                .equals(getCurrentFollowRequestId());

    }

    public boolean hasPreviousFollowRequest() {
        if (followRequestsUserId.isEmpty())
            return false;
        return !followRequestsUserId.getFirst()
                .equals(getCurrentFollowRequestId());
    }

    public void goNextFollowRequest() {
        if (hasNextFollowRequest()) {
            setCurrentFollowRequestId(followRequestsUserId.get
                    (followRequestsUserId.indexOf(getCurrentFollowRequestId()) + 1));
        }
    }

    public void goPreviousFollowRequest() {
        if (hasPreviousFollowRequest())
            setCurrentFollowRequestId(followRequestsUserId.get
                    (followRequestsUserId.indexOf(getCurrentFollowRequestId()) - 1));
    }
}


