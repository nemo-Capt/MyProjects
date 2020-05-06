package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserCredits {

    private Long id;
    private double percent;
    private String name;
    private String period;
    private double maxloan;

    public UserCredits() {
    }

    public UserCredits(Long id, double percent, String name, String period, double maxloan) {
        this.id = id;
        this.percent = percent;
        this.name = name;
        this.period = period;
        this.maxloan = maxloan;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getMaxloan() {
        return maxloan;
    }

    public void setMaxloan(double maxloan) {
        this.maxloan = maxloan;
    }
}
