package com.project.TaskManagement.controller;
import com.project.TaskManagement.dto.TaskDTO;
import com.project.TaskManagement.entity.Priority;
import com.project.TaskManagement.entity.Task;
import com.project.TaskManagement.mapper.TaskMapper;
import com.project.TaskManagement.repository.TaskRepository;
import com.project.TaskManagement.service.TaskService;
import com.project.TaskManagement.validation.MyCustomValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.time.LocalDateTime;
import java.util.List;

@Controller

public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private MyCustomValidation myCustomValidation;
    @GetMapping("/task/{id}")
    public String viewTaskById(@PathVariable("id") long id,Model model) {
        TaskDTO task=taskService.getTaskById(id);
        model.addAttribute("task" , task);

        return "task-details";
    }

    @GetMapping("/task/edit/{id}")
    public String editTaskForm(@PathVariable("id") long id, Model model){
        TaskDTO task=taskService.getTaskById(id);
        Task task1=TaskMapper.mapToTask(task);
        model.addAttribute("task" , task1);
        return "task-edit";
    }
    @PostMapping("/task/edit/{id}")
    public String editTask( @PathVariable("id") long id, @Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model){
        myCustomValidation.validate(task,bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("task", task);
            return "task-edit";

        }

        taskService.updateTask(task);
        return "redirect:/task/"+id;
    }

    @PostMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable("id") long id){
        taskService.deleteTask(id);
        return "redirect:/";

    }

    @GetMapping("/create-task")
    public String showCreateTaskForm(Model model){
        Task task=new Task();
        model.addAttribute("task" , task);
        return "task-create";
    }

    @PostMapping("/mark/{id}")
    public String markTask( @ModelAttribute("task") TaskDTO task , @PathVariable("id") long id){
        taskService.changeTaskStatus(id);
        return "redirect:/task/"+id;
    }
    @PostMapping("/{id}")
    public String markSubTaskTaskAsComplete(@RequestParam long sub_id , @PathVariable("id") long id){
        taskService.changeSubTaskStatus(sub_id);
        return "redirect:/task/"+id;
    }




    @GetMapping("/")
    public String tasks(Model model , @RequestParam(defaultValue = "0") int page){
        Page<TaskDTO> taskList=taskService.getAllTaskWithPagination(page , 4);
        model.addAttribute("tasks" , taskList.getContent());
        model.addAttribute("totalPages", taskList.getTotalPages());
        model.addAttribute("currentPage" , page);

        return "my-tasks";
    }

    @GetMapping("/tasks")
    public String tasksPagination(Model model , @RequestParam(defaultValue = "0") int page){
        Page<TaskDTO> taskList=taskService.getAllTaskWithPagination(page , 4);
        model.addAttribute("tasks" , taskList.getContent());
        model.addAttribute("totalPages", taskList.getTotalPages());
        model.addAttribute("currentPage" , page);

        return "my-tasks";
    }
    @PostMapping("/tasks/save")
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model){
        myCustomValidation.validate(task,bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("task" , task);
            return "task-create";

        }
        long id=taskService.createTask(task);

        return "redirect:/task/"+id;
    }


}
