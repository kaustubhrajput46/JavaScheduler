package com.company.scheduling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employee {
    private String name;

    private Map<String, List<String>> preferences; // "Monday" => ["Morning", "Evening"]

    private int assignedDays;

    public Employee(String name) {
        this.name = name;
        this.preferences = new HashMap<>();
        this.assignedDays = 0;
    }

    public void addPreference(String day, List<String> shiftPrefs) {
        preferences.put(day, shiftPrefs);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<String>> getPreferences() {
        return preferences;
    }

    public void setPreferences(Map<String, List<String>> preferences) {
        this.preferences = preferences;
    }

    public int getAssignedDays() {
        return assignedDays;
    }

    public void setAssignedDays(int assignedDays) {
        this.assignedDays = assignedDays;
    }
}
