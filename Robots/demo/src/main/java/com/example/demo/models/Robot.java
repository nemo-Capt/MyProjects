package com.example.demo.models;

public class Robot {
    private int robotNumber;
    private String currentTask;
    private String problem = "0";
    private int timeLeft;
    private int result;
    private boolean isBusy;
    private int currentOperation;

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Robot(int robotNumber) {
        this.robotNumber = robotNumber;
    }

    public int getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(int currentOperation) {
        this.currentOperation = currentOperation;
    }

    public int getRobotNumber() {
        return robotNumber;
    }

    public void setRobotNumber(int robotNumber) {
        this.robotNumber = robotNumber;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int calculator(String problem) { //калькулятор на сложение и вычитание
        String[] symbols = problem.split("[0-9]");
        String[] numbers = problem.split("[\\s+-]");
        int solution = Integer.parseInt(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            if (symbols[i].equals(" "))
                solution += Integer.parseInt(numbers[i]);
            else
                solution -= Integer.parseInt(numbers[i]);
        }
        return solution;
    }

    public boolean assignTask(String task, String problem) { //устанавливает время на задание и тип задания роботу
        switch (task) {
            case "say":
                this.setCurrentOperation(1);
                this.setTimeLeft(10);
                this.setProblem(problem);
                break;
            case "calculator":
                this.setCurrentOperation(2);
                this.setTimeLeft(1);
                this.setProblem(problem);
                break;
            case "currentDate":
                this.setCurrentOperation(3);
                this.setTimeLeft(1);
                break;
            case "suicide":
                this.setCurrentOperation(4);
                this.setTimeLeft(1);
                break;
            case "kill":
                this.setCurrentOperation(5);
                this.setTimeLeft(5);
                this.setProblem(problem);
                break;
            case "create":
                this.setCurrentOperation(6);
                this.setTimeLeft(1);
                break;
            default:
                this.setCurrentOperation(0);
                return false;
        }
        return true;
    }

    public String currentDate() {
        return java.time.LocalDate.now() + " " + String.valueOf(java.time.LocalTime.now()).substring(0, 8);
    }

}
