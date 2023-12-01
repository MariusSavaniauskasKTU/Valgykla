package com.springapp.valgykla.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    WORKER_READ("worker:read"),
    WORKER_UPDATE("worker:update"),
    WORKER_CREATE("worker:create"),
    WORKER_DELETE("worker:delete"),
    USER_READ("user:read")
    ;

    @Getter
    private final String permission;
}
