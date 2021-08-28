package ir.sharif.math.ap99_2.tinsta_server.transmitters;


import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface ResponseSender {
    Event getEvent();

    void sendResponse(Response response);

    void close();
}
