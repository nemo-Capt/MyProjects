package com.example.demo.controllers;

import com.example.demo.models.Robot;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestController {

    List<Robot> robots = new ArrayList<>();
    List<String> logList = Collections.synchronizedList(new ArrayList<>());
    int[] info = {0, 0};

    public TestController() {

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("addtask")
    public void addTask(@RequestParam String task) {
        int robotNumber = 0;
        for (int i = 0; i < robots.size(); i++) {
            if (!robots.get(i).isBusy()) {
                robotNumber = i;
                break;
            }
        }
        System.out.println(task);
        boolean doesTaskExist = true;
        switch (task) {
            case "sayHello":
                robots.get(robotNumber).setCurrentOperation(1);
                robots.get(robotNumber).setTimeLeft(10);
                break;
            case "findSum":
                robots.get(robotNumber).setCurrentOperation(2);
                robots.get(robotNumber).setTimeLeft(15);
                break;
            case "currentDate":
                robots.get(robotNumber).setCurrentOperation(3);
                robots.get(robotNumber).setTimeLeft(1);
                break;
            case "suicide":
                robots.get(robotNumber).setCurrentOperation(4);
                robots.get(robotNumber).setTimeLeft(1);
                break;
            case "kill":
                robots.get(robotNumber).setCurrentOperation(5);
                robots.get(robotNumber).setTimeLeft(5);
                break;
            case "create":
                robots.get(robotNumber).setCurrentOperation(6);
                robots.get(robotNumber).setTimeLeft(1);
                break;
            default:
                doesTaskExist = false;
                break;
        }
        info[1]++;
        if (doesTaskExist)
            logList.add("Robot № " + robotNumber + " is assigned with task: " + task);
        else logList.add("Task \"" + task + "\" doesn't exist");
        robots.get(robotNumber).setBusy(true);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("test")
    public List<String> testController() {
        return logList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("info")
    public int[] sendInfo() {
        return info;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("clear")
    public void clear() {
        logList.clear();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("start")
    public void startThread() {
        for (int i = 0; i < 10; i++) {
            robots.add(new Robot());
            info[0]++;
        }
        Thread s = new Thread(new Thread1());
        s.start();
    }

    public class Thread1 implements Runnable {

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < robots.size(); i++) {
                    if (robots.get(i).getTimeLeft() == 0 && robots.get(i).isBusy()) {
                        info[1]--;
                        switch (robots.get(i).getCurrentOperation()) {
                            case 1:
                                logList.add(robots.get(i).sayHello() + " Robot № " + i);
                                break;
                            case 2:
                                int[] sumArray = {1, 2, 3, 4, 5, 6, 7};
                                logList.add("Counted sum: " + robots.get(i).findSum(sumArray));
                                break;
                            case 3:
                                logList.add("Current date: " + robots.get(i).currentDate());
                                break;
                            case 4:
                                info[0]--;
                                robots.remove(robots.get(i));
                                logList.add("Robot № " + i + " self destructed");
                                break;
                            case 5:
                                info[0]--;
                                robots.remove(robots.get(i++));
                                logList.add("Robot № " + (i - 1) + " destroyed robot № " + i);
                                break;
                            case 6:
                                robots.add(new Robot());
                                logList.add("Creating additional robot...");
                                info[0]++;
                        }
                        if (robots.size() < 10) {
                            logList.add("WARNING: not enough robots, creating additional one...");
                            robots.add(new Robot());
                            info[0]++;
                        }
                        robots.get(i).setBusy(false);
                    }
                    if (robots.get(i).isBusy())
                        robots.get(i).setTimeLeft(robots.get(i).getTimeLeft() - 1);
                }
                if (info[1] == robots.size()) {
                    logList.add("WARNING: not enough robots for you task, creating additional one...");
                    robots.add(new Robot());
                    info[0]++;
                }
            }
        }
    }
}
