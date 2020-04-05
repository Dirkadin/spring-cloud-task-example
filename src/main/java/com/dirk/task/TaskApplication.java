package com.dirk.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;

@SpringBootApplication
@EnableTask
public class TaskApplication implements CommandLineRunner, TaskExecutionListener {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String arg = args.length == 0 ? "you" : args[0];
        System.out.println("Welcome to " + arg);
    }

    @Override
    public void onTaskStartup(TaskExecution taskExecution) {
        System.out.println("Taskname " + taskExecution.getTaskName() + " " + taskExecution.getExecutionId() + " started....");
    }

    @Override
    public void onTaskEnd(TaskExecution taskExecution) {
        System.out.println("Taskname " + taskExecution.getTaskName() + " " + taskExecution.getExecutionId() + " completed....");
    }

    @Override
    public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
        System.out.println("Taskname " + taskExecution.getTaskName() + " " + taskExecution.getExecutionId() + " failed....");
    }
}
