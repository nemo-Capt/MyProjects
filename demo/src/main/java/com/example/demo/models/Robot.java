package com.example.demo.models;

public class Robot {
    private String currentTask;
    private int timeLeft;
    private int result;
    private boolean isBusy;
    private int currentOperation;

    public int getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(int currentOperation) {
        this.currentOperation = currentOperation;
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

    public String sayHello() {
        return "Hello!";
    }

    public int findSum(int[] sumArray) {
        int result = 0;
        for (int value : sumArray) result += value;
        return result;
    }

    public int findSubtraction(int[] subtractArray) {
        int result = 0;
        for (int value : subtractArray) result -= value;
        return result;
    }

    public int findMultiplying(int[] multiplyingArray) {
        int result = 1;
        for (int value : multiplyingArray) result *= value;
        return result;
    }

    public int findDivision(int[] divisionArray) {
        int result = divisionArray[0];
        for (int i=1; i<divisionArray.length; i++)
            result /= divisionArray[i];
        return result;
    }

    public String currentDate() {
        return java.time.LocalDate.now() + " " + String.valueOf(java.time.LocalTime.now()).substring(0, 8);
    }



}
