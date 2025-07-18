package com.company.scheduling;

import java.util.ArrayList;
import java.util.List;

public class Shift {
    private static final int MIN_EMPLOYEES = 2;

    private String day;
    private String type; // "Morning", "Afternoon", "Evening"
    private List<Employee> assignedEmployees;

    public Shift(String day, String type) {
        this.day = day;
        this.type = type;
        this.assignedEmployees = new ArrayList<>();
    }

    public boolean isFull() {
        return assignedEmployees.size() >= MIN_EMPLOYEES;
    }

    public boolean hasMinimumStaff() {
        return assignedEmployees.size() >= MIN_EMPLOYEES;
    }

    public boolean addEmployee(Employee e) {
        if (e.canWork(day)) {
            assignedEmployees.add(e);
            e.assignToDay(day);
            return true;
        }
        return false;
    }

    public String getDay() {
        return day;
    }

    public String getType() {
        return type;
    }

    public List<Employee> getAssignedEmployees() {
        return new ArrayList<>(assignedEmployees);
    }

    public int getCurrentStaffCount() {
        return assignedEmployees.size();
    }
}
