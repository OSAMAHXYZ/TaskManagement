package com.project.TaskManagement.mapper;
import com.project.TaskManagement.dto.TaskDTO;
import com.project.TaskManagement.entity.Task;
import org.springframework.beans.BeanUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskMapper {
    public static TaskDTO mapToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        BeanUtils.copyProperties(task, taskDTO);
        // Calculate remaining duration
        if (task.getDurationInHour() != null) {
            LocalDateTime endTime = task.getCreatedAt().plusHours(task.getDurationInHour());
            LocalDateTime now = LocalDateTime.now();

            if (now.isBefore(endTime)) {
                Duration d = Duration.between(now, endTime);
                taskDTO.setRemainingHours(d.toHours());
                taskDTO.setRemainingMinutes(d.minusHours(taskDTO.getRemainingHours()).toMinutes());
            } else {
                taskDTO.setRemainingHours(0);
                taskDTO.setRemainingMinutes(0);
                taskDTO.setPassed(true);
            }
        }else if(LocalDate.now().isAfter(taskDTO.getDeadline())){
            taskDTO.setPassed(true);
        }

        return taskDTO;
    }

    public static Task mapToTask(TaskDTO taskDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        return task;
    }

    public static List<TaskDTO> mapToTaskDTOList(List<Task> tasks) {
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            taskDTOs.add(mapToTaskDTO(task));
        }
        return taskDTOs;
    }

    public static List<Task> mapToTaskList(List<TaskDTO> taskDTOs) {
        List<Task> tasks = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOs) {
            tasks.add(mapToTask(taskDTO));
        }
        return tasks;
    }

}
