package com.playground.PostgreSQL.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AuditableEntity {

    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "created_by_user")
    private String createdByUser;

    @Column(name = "updated_by_user")
    private String updatedByUser;

    // Getters and Setters
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(String updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    // Lifecycle methods to automatically set audit fields
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
        this.deleted = false;

        // In a real application, you'd typically get the current user from
        // a security context or authentication service
        this.createdByUser = getCurrentUsername();
        this.updatedByUser = getCurrentUsername();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = LocalDateTime.now();
        this.updatedByUser = getCurrentUsername();
    }

    // Helper method to get current username
    // You would replace this with actual user retrieval logic
    private String getCurrentUsername() {
        // Example implementation - replace with your actual user retrieval mechanism
        // This could use Spring Security, a custom security context, etc.
        return "system"; // or return current authenticated user
    }
}
