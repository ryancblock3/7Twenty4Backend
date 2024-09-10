package com.twenty4.WebApp.model;

import java.math.BigDecimal;

public class EmployeeHours {
    private String name;
    private BigDecimal totalHours;

    public EmployeeHours(String name, BigDecimal totalHours) {
        this.name = name;
        this.totalHours = totalHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(BigDecimal totalHours) {
        this.totalHours = totalHours;
    }
}
