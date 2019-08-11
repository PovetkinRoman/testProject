package com.rpovetkin.testTask.model;

public enum TaskStatus {
    NEW ("Новая задача"),
    IN_PROGRESS ("В работе"),
    CLOSED ("Закрыта");

    private String title;

    TaskStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

}

