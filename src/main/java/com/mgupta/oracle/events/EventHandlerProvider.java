package com.mgupta.oracle.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EventHandlerProvider {
    private static ArrayList<DataEntityEventHandler> handlers = new ArrayList<>();

    public static void register(DataEntityEventHandler eventHandler) {
        handlers.add(eventHandler);
    }

    public static Collection<DataEntityEventHandler> handlers() {
        return Collections.unmodifiableCollection(handlers);
    }
}
