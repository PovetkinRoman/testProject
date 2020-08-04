package com.rpovetkin.testTask.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    String name;
    String computerId;

    public User() {
    }

    public User(String name, String computerId) {
        this.name = name;
        this.computerId = computerId;
    }
}
