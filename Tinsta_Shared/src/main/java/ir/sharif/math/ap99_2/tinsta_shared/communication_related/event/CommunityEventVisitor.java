package ir.sharif.math.ap99_2.tinsta_shared.communication_related.event;

import ir.sharif.math.ap99_2.tinsta_shared.event.EventVisitor;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface CommunityEventVisitor extends EventVisitor {

    Response addUserToFolder(AddToUserFolderEvent addToUserFolderEvent);

    Response createFolder(CreateNewFolderEvent createNewFolderEvent);

    Response removeUserFromFolder(RemoveUserFromFolderEvent removeUserFromFolderEvent);

    Response showAFolder(ShowAFolderContentEvent showAFolderContentEvent);

    Response showBlackList(ShowBlackListsEvent showBlackListsEvent);

    Response showFolders(ShowFoldersListEvent showFoldersListEvent);

    Response showFollowers(ShowFollowersEvent showFollowersEvent);

    Response showFollowings(ShowFollowingsEvent showFollowingsEvent);

    Response showMutedList(ShowMutedListEvent showMutedListEvent);







}
