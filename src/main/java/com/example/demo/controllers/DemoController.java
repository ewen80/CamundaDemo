package com.example.demo.controllers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by wenliang on 2021-11-11
 */
@RestController
@RequestMapping("/test/")
public class DemoController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/{key}/start")
    public String startInstance(@PathVariable("key") String key) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("loanApproval");
        return instance.getId();
    }

    @DeleteMapping("/{id}/delete")
    public void removeInstance(@PathVariable("id") String id) {
        runtimeService.deleteProcessInstance(id, "");
    }

    @GetMapping("tasks")
    public List<Task> getInstances() {

        List<Task> tasks = taskService.createTaskQuery()
                .active()
                .taskAssignee("user1")
                .list();
        return tasks;
    }
}
