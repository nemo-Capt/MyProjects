package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class RobotsInfo {

    private int robotsBusy;
    private List<Integer> robotsAlive = new ArrayList<>();

    public int getRobotsBusy() {
        return robotsBusy;
    }

    public void setRobotsBusy(int robotsBusy) {
        this.robotsBusy = robotsBusy;
    }

    public List<Integer> getRobotsAlive() {
        return robotsAlive;
    }

    public void setRobotsAlive(List<Integer> robotsAlive) {
        this.robotsAlive = robotsAlive;
    }

}
