package com.project.TaskManagement.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity(name = "subtasks")
public class SubTask {
    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subtasks_seq")
    @SequenceGenerator(name = "subtasks_seq", sequenceName = "subtasks_seq", allocationSize = 1)
    private long id;
    @Column(name = "is_completed")
    private boolean isCompleted=false;
    @Size(max = 300)
    private String title;
    @ManyToOne()
    private Task task;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public boolean isCompleted() {
        return isCompleted;
    }


    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public Task getTask() {
        return task;
    }


    public void setTask(Task task) {
        this.task = task;
    }


    @Override
    public String toString() {
        return "SubTask{" + "id=" + id + ", isCompleted=" + isCompleted + ", title='" + title + '\'' + ", task=" + task + '}';
    }
}
