package com.koobing.koobing.logs;

import java.util.UUID;

public class Context {
    private static final ThreadLocal<UUID> correlationId = new ThreadLocal<>();

    public static UUID correlationId() {
        var id = correlationId.get();
        if (id == null) {
            id = UUID.randomUUID();
            correlationId.set(id);
        }
        return id;
    }


}
