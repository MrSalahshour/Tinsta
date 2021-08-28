package ir.sharif.math.ap99_2.tinsta_shared.response;

public class SaveOfflineUserResponse extends Response{
    private String profileImagePath;

    public SaveOfflineUserResponse(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public SaveOfflineUserResponse() {
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
