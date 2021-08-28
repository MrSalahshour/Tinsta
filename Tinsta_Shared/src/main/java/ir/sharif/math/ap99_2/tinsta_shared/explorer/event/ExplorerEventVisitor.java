package ir.sharif.math.ap99_2.tinsta_shared.explorer.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;

public interface ExplorerEventVisitor extends EventVisitor {

    Response blockOrUnBLockUser(User user);

    Response getUserProfileTextContent(User user);

    Response followOrUnFollowUser(User user);

    Response muteOrUnMuteUser(User user);

    Response searchUser(String origin,SearchUserEvent searchUserEvent);

    Response refreshExplorer();

    Response getCurrentTweet();

}
