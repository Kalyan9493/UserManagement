package com.usermanagement.usermanagement.utility;

import com.usermanagement.usermanagement.exceptionhandlers.UserException;
import com.usermanagement.usermanagement.service.UserService;
import com.usermanagement.usermanagement.service.impl.UserServiceImpl;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;
import java.util.Optional;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @PrePersist
    protected void onCreate() {
        Instant now = GlobalTimeUtil.getGlobalTime();
        createdAt = now;
        updatedAt = now;
        createdBy = getCurrentUser();
        updatedBy = getCurrentUser();
        isDeleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = GlobalTimeUtil.getGlobalTime();
        updatedBy = getCurrentUser();
        isDeleted = false;
    }

    private String getCurrentUser() {
        UserService userService = new UserServiceImpl();
        Optional<String> username = Optional.ofNullable(userService.getCurrentUsername());
        return username.orElse("System");
    }

}

