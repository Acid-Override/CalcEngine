package com.playground.PostgreSQL.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerSearchCriteria {
    private String name;
    private String email;
    private LocalDateTime createdAfter;
    private LocalDateTime createdBefore;
    private String sortField;
    private boolean sortAscending = true;


    // Builder pattern for fluent API
    public CustomerSearchCriteria withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerSearchCriteria withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerSearchCriteria withCreatedAfter(LocalDateTime createdAfter) {
        this.createdAfter = createdAfter;
        return this;
    }

    public CustomerSearchCriteria withCreatedBefore(LocalDateTime createdBefore) {
        this.createdBefore = createdBefore;
        return this;
    }

    public CustomerSearchCriteria withSortField(String sortField) {
        this.sortField = sortField;
        return this;
    }

    public CustomerSearchCriteria withSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
        return this;
    }
}
