package com.springapp.valgykla.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.springapp.valgykla.Model.Permission.*;

@RequiredArgsConstructor
public enum Role {

    USER(
            Set.of(
                    USER_READ
            )
    ),
    WORKER(
            Set.of(
                    USER_READ,
                    WORKER_CREATE,
                    WORKER_UPDATE,
                    WORKER_DELETE,
                    WORKER_READ
            )
    ),
    ADMIN(
            Set.of(
                    USER_READ,
                    WORKER_CREATE,
                    WORKER_UPDATE,
                    WORKER_DELETE,
                    WORKER_READ,
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE
            )
    )
    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
