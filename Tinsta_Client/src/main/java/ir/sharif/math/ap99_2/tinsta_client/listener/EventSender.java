package ir.sharif.math.ap99_2.tinsta_client.listener;


import ir.sharif.math.ap99_2.tinsta_shared.event.Event;
import ir.sharif.math.ap99_2.tinsta_shared.response.Response;

public interface EventSender {
    Response send(Event event);
    void close();
}
