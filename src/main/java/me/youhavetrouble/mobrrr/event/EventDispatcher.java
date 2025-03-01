package me.youhavetrouble.mobrrr.event;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class EventDispatcher {

    private final Map<Class<? extends Event>, List<EventHandler<? extends Event>>> eventHandlers = new HashMap<>();

    public void registerEventHandler(@NotNull Class<? extends Event> eventType, @NotNull EventHandler<? extends Event> eventHandler) {
        eventHandlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(eventHandler);
    }

    @SuppressWarnings("unchecked")
    public void dispatchEvent(@NotNull Event event) {
        List<EventHandler<? extends Event>> handlers = eventHandlers.getOrDefault(event.getClass(), Collections.emptyList());
        handlers.forEach(handler -> {
            EventHandler<Event> eventHandler = (EventHandler<Event>) handler;
            eventHandler.handle(event);
        });
    }

}
