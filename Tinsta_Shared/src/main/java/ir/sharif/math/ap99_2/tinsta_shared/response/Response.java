package ir.sharif.math.ap99_2.tinsta_shared.response;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DontHaveFollowReqResponse.class, name = "DontHaveFollowReqResponse"),
        @JsonSubTypes.Type(value = ExceptionResponse.class, name = "ExceptionResponse"),
        @JsonSubTypes.Type(value = GetBooleanResponse.class, name = "GetBooleanResponse"),
        @JsonSubTypes.Type(value = GetCommentResponse.class, name = "GetCommentResponse"),
        @JsonSubTypes.Type(value = GetIntegerResponse.class, name = "GetIntegerResponse"),
        @JsonSubTypes.Type(value = GetMessageResponse.class, name = "GetMessageResponse"),
        @JsonSubTypes.Type(value = GetMyInfoResponse.class, name = "GetMyInfoResponse"),
        @JsonSubTypes.Type(value = GetStringResponse.class, name = "GetStringResponse"),
        @JsonSubTypes.Type(value = GetTweetResponse.class, name = "GetTweetResponse"),
        @JsonSubTypes.Type(value = GetUserNameResponse.class, name = "GetUserNameResponse"),
        @JsonSubTypes.Type(value = GetUserResponse.class, name = "GetUserResponse"),
        @JsonSubTypes.Type(value = GroupExistResponse.class, name = "GroupExistResponse"),
        @JsonSubTypes.Type(value = HasNextFollowReqResponse.class, name = "HasNextFollowReqResponse"),
        @JsonSubTypes.Type(value = HasPreviousFollowReqResponse.class, name = "HasPreviousFollowReqResponse"),
        @JsonSubTypes.Type(value = LoginResponse.class, name = "LoginResponse"),
        @JsonSubTypes.Type(value = VoidResponse.class, name = "VoidResponse"),
        @JsonSubTypes.Type(value = GetFileResponse.class, name = "GetFileResponse"),
        @JsonSubTypes.Type(value = Response.WrongAPI.class, name = "WrongAPI"),
        @JsonSubTypes.Type(value = SaveMessageResponse.class, name = "SaveMessageResponse"),
        @JsonSubTypes.Type(value = SaveOfflineUserResponse.class, name = "SaveOfflineUser"),
        @JsonSubTypes.Type(value = SaveChatRoomResponse.class, name = "SaveChatRoomResponse"),
        @JsonSubTypes.Type(value = SaveUserNameResponse.class, name = "SaveUserNameResponse"),
})
public abstract class Response  {

    protected String nonNull(String string) {
        if (string == null) string = "";
        return string;
    }

    public static WrongAPI getWrongApi() {
        return WrongAPI.instance;
    }


    static class WrongAPI extends Response {
        private final static WrongAPI instance = new WrongAPI();

        private WrongAPI() {
        }
    }

}
