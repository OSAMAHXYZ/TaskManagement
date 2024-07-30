package com.project.TaskManagement.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tasks")
public class Task {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq")
    @SequenceGenerator(name = "tasks_seq", sequenceName = "tasks_seq", allocationSize = 1)
    private long id;
    @Column(name = "created_at")
    private  LocalDateTime createdAt=LocalDateTime.now();
    @Column(nullable = true)
    @Range(min = 1 ,max = 24)

    private Integer durationInHour;
    @Future
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private  LocalDate deadline;
    @Enumerated(EnumType.STRING) // Specify how the enum should be persisted

    private Priority priority;

    @Column(name = "is_completed")
    private boolean isCompleted;
    @NotBlank
    @Size(min = 3 ,max = 300)
    private String title;
    private String content;


    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")

    private List<SubTask> subTasks = new ArrayList<>();


    public List<SubTask> getSubTasks() {

        return subTasks;
    }


    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }


    public Task(long id,  LocalDate deadline,  Priority priority, boolean isCompleted,
            String title, String content, Integer durationInHour) {
        this.id = id;
        this.deadline = deadline;
        this.priority = priority;
        this.isCompleted = isCompleted;
        this.title = title;
        this.content = content;
        this.durationInHour = durationInHour;
    }


    public Integer getDurationInHour() {
        return durationInHour;
    }


    public void setDurationInHour(Integer durationInHour) {
        this.durationInHour = durationInHour;
    }


    public Task() {
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


    public LocalDate getDeadline() {
        return deadline;
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

    public Duration getRemainingDuration() {
        if (durationInHour == null) {
            return null;
        }

        LocalDateTime endTime = createdAt.plusHours(durationInHour);
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(endTime)) {
            return Duration.between(now, endTime);
        } else {
            return Duration.ZERO;
        }
    }

}
