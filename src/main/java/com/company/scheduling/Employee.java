package com.company.scheduling;

import java.util.*;

public class Employee {
    private String name;

    private Set<String> assignedShiftDays = new HashSet<>();

    private Map<String, Map<String, Integer>> shiftPriorities; // day -> (shift -> priority)

    private int assignedDays;

    public Employee(String name) {
        this.name = name;
        this.shiftPriorities = new HashMap<>();
        this.assignedShiftDays = new HashSet<>();
        this.assignedDays = 0;
    }

    public void addPreference(String day, String shift, int priority) {
        shiftPriorities.computeIfAbsent(day, k -> new HashMap<>())
                .put(shift, priority);
    }

    public boolean canWork(String day) {
        return !assignedShiftDays.contains(day) && assignedDays < 5;
    }

    public void assignToDay(String day) {
        assignedShiftDays.add(day);
        assignedDays++;
    }

    public Map<String, Integer> getPreferencesForDay(String day) {
        return shiftPriorities.getOrDefault(day, new HashMap<>());
    }

    public void incrementAssignedDays() {
        assignedDays++;
    }

    public int getAssignedDays() {
        return assignedDays;
    }

    public String getName() {
        return name;
    }
}
