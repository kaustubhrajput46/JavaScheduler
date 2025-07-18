package com.company.scheduling;

import java.util.ArrayList;
import java.util.List;

public class Shift {
    private String day;
    private String type; // "Morning", "Afternoon", "Evening"
    private List<Employee> assignedEmployees;

    public Shift(String day, String type) {
        this.day = day;
        this.type = type;
        this.assignedEmployees = new ArrayList<>();
    }

    public boolean isFull() {
        return assignedEmployees.size() >= 2;
    }

    public void addEmployee(Employee e) {
        if (!isFull() && e.getAssignedDays() < 5) {
            assignedEmployees.add(e);
            e.incrementAssignedDays();
        }
    }

    // Getter methods

    public String getDay() {
        return day;
    }

    public String getType() {
        return type;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }
}
