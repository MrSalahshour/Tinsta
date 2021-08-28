package ir.sharif.math.ap99_2.tinsta_client.model;


public class OfflineUser {
    private int userId;
    private int userNameId;
    private String profileImagePath;
    private int ChatRoomID;

    public OfflineUser() {
    }

    public OfflineUser(int userId, int userNameId, String profileImagePath, int chatRoomID) {
        this.userId = userId;
        this.userNameId = userNameId;
        this.profileImagePath = profileImagePath;
        ChatRoomID = chatRoomID;
    }

    public int getChatRoomID() {
        return ChatRoomID;
    }

    public void setChatRoomID(int chatRoomID) {
        ChatRoomID = chatRoomID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserNameId() {
        return userNameId;
    }

    public void setUserNameId(int userNameId) {
        this.userNameId = userNameId;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
