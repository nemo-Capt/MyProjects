package com.example.demo.controllers;

import com.example.demo.models.Robot;
import com.example.demo.models.RobotsInfo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RobotController {
    private List<Robot> robots = new ArrayList<>();
    private List<String> logList = Collections.synchronizedList(new ArrayList<>());
    private RobotsInfo robotsInfo = new RobotsInfo();
    private int robotNumber;
    private int robotsBusy = 0;
    private int universalRobotCounter = 0;
    private boolean multipleRobots = false;
    private List<String> helpingRobots = new ArrayList<>();

    public RobotController() {
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("addtask")
    public void addTask(@RequestParam String task, @RequestParam String robotNumberStr, @RequestParam String problem) {
        if (robotNumberStr.length() > 1) { //если роботов указано несколько
            String[] robotNumberArray = robotNumberStr.split("\\s+");
            for (int j = 0; j < robotNumberArray.length; j++) {
                for (int i = 0; i < robots.size(); i++) {
                    if (robotNumberArray[j].equals(String.valueOf(robots.get(i).getRobotNumber()))) {
                        robotNumberArray[j] = String.valueOf(i);
                        break;
                    }
                }
            }
            helpingRobots.addAll(Arrays.asList(robotNumberArray));
            robotNumber = Integer.parseInt(robotNumberStr.substring(0, 1));
            multipleRobots = true;
        } else
            robotNumber = Integer.parseInt(robotNumberStr);
        boolean robotIsAlive = false;
        for (int i = 0; i < robots.size(); i++) {
            if (robotNumber == robots.get(i).getRobotNumber()) {
                robotNumber = i;
                robotIsAlive = true;
                break;
            }
        }
        if (!robotIsAlive) {
            logList.add("Unfortunately your robot no longer exists");
            return;
        }
        boolean doesTaskExist;
        if (robotNumber > robots.size() - 1) {
            logList.add("WARNING: robot № " + robotNumber + " doesn't exist, creating additional one...");
            universalRobotCounter++;
            robots.add(new Robot(universalRobotCounter));
        }
        if (robots.get(robotNumber).isBusy()) {
            logList.add("Robot № " + robotNumber + " is busy right now, please select another one");
        } else {

            doesTaskExist = robots.get(robotNumber).assignTask(task, problem); //назначение полученного задания выбранному роботу, возвращает false если задания не существует
            if (multipleRobots) {
                robots.get(robotNumber).setTimeLeft(robots.get(robotNumber).getTimeLeft() / helpingRobots.size()); //сокращает время в соответствии с количеством помогающих рбоотов
            }

            if (doesTaskExist) {
                logList.add("Robot № " + robots.get(robotNumber).getRobotNumber() + " is assigned with task: " + task);
                robotsBusy++;
                robots.get(robotNumber).setBusy(true);
                if (multipleRobots) {
                    for (int i = 1; i < helpingRobots.size(); i++) {
                        robots.get(Integer.parseInt(helpingRobots.get(i))).setTimeLeft(robots.get(robotNumber).getTimeLeft());
                        robots.get(Integer.parseInt(helpingRobots.get(i))).setBusy(true); //помогающие робот переходят в режим занят
                    }
                }
            } else logList.add("Task \"" + task + "\" doesn't exist");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("logs")
    public List<String> sendLogs() {
        return logList;
    }

    @CrossOrigin(origins = "http://localhost:4200") //отправка логов
    @GetMapping("info")
    public RobotsInfo sendInfo() {
        List<Integer> temp = new ArrayList<>();
        for (Robot robot : robots) {
            temp.add(robot.getRobotNumber());
        }
        int temp2 = 0;
        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i).isBusy()){
                temp2++;
            }
        }
        robotsBusy=temp2;
        robotsInfo.setRobotsAlive(temp);
        robotsInfo.setRobotsBusy(robotsBusy);
        return robotsInfo;
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
            robots.add(new Robot(i));
            universalRobotCounter = i;
        }
        Thread s = new Thread(new Thread1());
        s.start();
    }

    public class Thread1 implements Runnable { //выполнение задания

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < robots.size(); i++) {
                    if (robots.get(i).getTimeLeft() == 0 && robots.get(i).isBusy()) {
                        robots.get(i).setBusy(false);
                        switch (robots.get(i).getCurrentOperation()) {
                            case 1:
                                logList.add(robots.get(i).getProblem() + " - Robot № " + robots.get(i).getRobotNumber());
                                break;
                            case 2:
                                logList.add("Counted sum: " + robots.get(i).calculator(robots.get(i).getProblem()));
                                break;
                            case 3:
                                logList.add("Current date: " + robots.get(i).currentDate());
                                break;
                            case 4:
                                logList.add("Robot № " + robots.get(robotNumber).getRobotNumber() + " self destructed");
                                robots.remove(robots.get(robotNumber));
                                break;
                            case 5:
                                logList.add("Robot № " + robots.get(robotNumber).getRobotNumber() + " destroyed robot № " + robots.get(robotNumber).getProblem());
                                System.out.println(robots.get(i).getProblem());
                                int temp = Integer.parseInt(robots.get(i).getProblem());
                                for (int j = 0; j < robots.size(); j++) {
                                    if (temp == robots.get(j).getRobotNumber()) {
                                        temp = j;
                                        break;
                                    }
                                }
                                robots.remove(temp);
                                break;
                            case 6:
                                universalRobotCounter++;
                                robots.add(new Robot(universalRobotCounter));
                                logList.add("Creating additional robot...");
                                break;

                        }
                        helpingRobots.clear();
                        multipleRobots = false;
                        if (robots.size() < 10) {
                            logList.add("WARNING: not enough robots, creating additional one...");
                            universalRobotCounter++;
                            robots.add(new Robot(universalRobotCounter));
                        }
                    }
                    if (robots.get(i).isBusy())
                        robots.get(i).setTimeLeft(robots.get(i).getTimeLeft() - 1);
                    if (robotsBusy == robots.size()) {
                        logList.add("WARNING: not enough robots for you task, creating additional one...");
                        universalRobotCounter++;
                        robots.add(new Robot(universalRobotCounter));
                    }
                }
            }
        }
    }
}
