package ir.sharif.math.ap99_2.tinsta_shared.response;

import ir.sharif.math.ap99_2.tinsta_shared.home_page.event.GetMyInfoEvent;

public class GetMyInfoResponse extends Response {
    private GetMyInfoEvent getMyInfoEvent;

    public GetMyInfoResponse(GetMyInfoEvent getMyInfoEvent) {
        this.getMyInfoEvent = getMyInfoEvent;
    }

    public GetMyInfoResponse() {
    }

    public GetMyInfoEvent getGetMyInfoEvent() {
        return getMyInfoEvent;
    }

    public void setGetMyInfoEvent(GetMyInfoEvent getMyInfoEvent) {
        this.getMyInfoEvent = getMyInfoEvent;
    }
}
