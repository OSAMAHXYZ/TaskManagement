package com.project.TaskManagement.service;

import com.project.TaskManagement.dto.TaskDTO;
import com.project.TaskManagement.entity.SubTask;
import com.project.TaskManagement.entity.Task;
import com.project.TaskManagement.mapper.TaskMapper;
import com.project.TaskManagement.repository.SubTaskRepository;
import com.project.TaskManagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SubTaskRepository subTaskRepository;
    public long createTask(Task task){
        task.setSubTasks(task.getSubTasks().stream().filter(subTask -> !subTask.getTitle().isEmpty()).toList());

        taskRepository.save(task);
        taskRepository.flush();
        return task.getId();

    }

    public void changeSubTaskStatus(long id){
        SubTask s =subTaskRepository.findById(id).get();
        s.setCompleted(!s.isCompleted());
        subTaskRepository.save(s);
    }


    public void changeTaskStatus(Long id ){
        Optional<Task> t=taskRepository.findById(id);
        if(t.isPresent()){
            Task newTask=t.get();
            newTask.setCompleted(!newTask.isCompleted());
            taskRepository.save(newTask);
            taskRepository.flush();

        }
    }
    public void updateTask(Task task){
        task.setSubTasks(task.getSubTasks().stream().filter(subTask -> !subTask.getTitle().isEmpty()).toList());
        taskRepository.save(task);
        taskRepository.flush();
    }
    public TaskDTO getTaskById(Long id){
        Optional<Task> task=taskRepository.findById(id);
        return task.map(TaskMapper::mapToTaskDTO).orElse(null);
    }
    


    public Page<TaskDTO> getAllTaskWithPagination(int currentPage , int offset) {
        Pageable sortedByDate= PageRequest.of(currentPage,offset, Sort.by("deadline"));
        Page<Task> taskPage=taskRepository.findAll(sortedByDate);
        List<TaskDTO> taskDTOs  = taskPage.getContent()
                .stream()
                .map(TaskMapper::mapToTaskDTO)
                .toList();
        return new PageImpl<>(
                taskDTOs,
                PageRequest.of(taskPage.getPageable().getPageNumber(), taskPage.getPageable().getPageSize()),
                taskPage.getTotalElements()
        );


    }


    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }
}
