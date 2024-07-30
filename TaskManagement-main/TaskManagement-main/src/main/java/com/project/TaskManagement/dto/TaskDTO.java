package com.project.TaskManagement.dto;
import com.project.TaskManagement.entity.Priority;
import com.project.TaskManagement.entity.SubTask;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private long id;
    private  LocalDateTime createdAt;
    private Integer durationInHour;
    private LocalDate deadline;
    private Priority priority;

    private boolean isCompleted;
    private String title;
    private String content;
    private long remainingHours;
    private long remainingMinutes;

    private boolean passed;


    public boolean isPassed() {
        return passed;
    }


    public void setPassed(boolean passed) {
        this.passed = passed;
    }


    private List<SubTask> subTasks = new ArrayList<>();
    public void getRemainingDuration() {
        if (durationInHour == null) {
            return;
        }

        LocalDateTime endTime = createdAt.plusHours(durationInHour);
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(endTime)) {
            Duration d =Duration.between(now, endTime);
            long remainingHours = d.toHours();
            long remainingMinutes = d.minusHours(remainingHours).toMinutes();

        } else {
            return ;
        }
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public Integer getDurationInHour() {
        return durationInHour;
    }


    public void setDurationInHour(Integer durationInHour) {
        this.durationInHour = durationInHour;
    }


    public LocalDate getDeadline() {
        return deadline;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }


    public Priority getPriority() {
        return priority;
    }


    public void setPriority(Priority priority) {
        this.priority = priority;
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


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public long getRemainingHours() {
        return remainingHours;
    }


    public void setRemainingHours(long remainingHours) {
        this.remainingHours = remainingHours;
    }


    public long getRemainingMinutes() {
        return remainingMinutes;
    }


    public void setRemainingMinutes(long remainingMinutes) {
        this.remainingMinutes = remainingMinutes;
    }


    public List<SubTask> getSubTasks() {
        return subTasks;
    }


    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }


    @Override
    public String toString() {
        return "TaskDTO{" + "id=" + id + ", createdAt=" + createdAt + ", durationInHour=" + durationInHour + ", deadline=" + deadline + ", priority=" + priority + ", isCompleted=" + isCompleted + ", title='" + title + '\'' + ", content='" + content + '\'' + ", remainingHours=" + remainingHours + ", remainingMinutes=" + remainingMinutes + ", subTasks=" + subTasks + '}';
    }
}
