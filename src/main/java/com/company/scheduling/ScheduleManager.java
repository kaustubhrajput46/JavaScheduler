package com.company.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleManager {
    private static final List<String> DAYS = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    private static final List<String> SHIFT_TYPES = List.of("Morning", "Afternoon", "Evening");

    private List<Employee> employees;
    private Map<String, Map<String, Shift>> weekSchedule;

    public ScheduleManager() {
        employees = new ArrayList<>();
        weekSchedule = new HashMap<>();
        initializeSchedule();
    }

    private void initializeSchedule() {
        for (String day : DAYS) {
            Map<String, Shift> dailyShifts = new HashMap<>();
            for (String type : SHIFT_TYPES) {
                dailyShifts.put(type, new Shift(day, type));
            }
            weekSchedule.put(day, dailyShifts);
        }
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void generateSchedule() {
        assignPreferredShifts();
        resolveConflicts();
        ensureMinimumStaffing();
    }



    private void assignPreferredShifts() {
        for (String day : DAYS) {
            Map<String, Shift> shifts = weekSchedule.get(day);
            for (Employee emp : employees) {
                if (!emp.canWork(day)) continue;

                Map<String, Integer> prefs = emp.getPreferencesForDay(day);
                if (prefs.isEmpty()) continue;

                // Sort preferences by priority
                List<Map.Entry<String, Integer>> sortedPrefs = new ArrayList<>(prefs.entrySet());
                sortedPrefs.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

                for (Map.Entry<String, Integer> pref : sortedPrefs) {
                    Shift shift = shifts.get(pref.getKey());
                    if (!shift.isFull()) {
                        shift.addEmployee(emp);
                        break;
                    }
                }
            }
        }
    }

    private void resolveConflicts() {
        for (String day : DAYS) {
            Map<String, Shift> shifts = weekSchedule.get(day);
            for (Employee emp : employees) {
                if (emp.canWork(day)) {
                    handleShiftConflict(emp, day, shifts);
                }
            }
        }
    }

    private void handleShiftConflict(Employee emp, String day, Map<String, Shift> shifts) {
        for (String shiftType : SHIFT_TYPES) {
            Shift shift = shifts.get(shiftType);
            if (!shift.isFull() && shift.addEmployee(emp)) {
                return;
            }
        }
    }

    private void ensureMinimumStaffing() {
        for (String day : DAYS) {
            for (String shiftType : SHIFT_TYPES) {
                Shift shift = weekSchedule.get(day).get(shiftType);
                while (!shift.hasMinimumStaff()) {
                    Employee availableEmp = findAvailableEmployee(day);
                    if (availableEmp == null) {
                        System.out.println("Warning: Unable to meet minimum staffing for " + day + " " + shiftType);
                        break;
                    }
                    shift.addEmployee(availableEmp);
                }
            }
        }
    }

    private Employee findAvailableEmployee(String day) {
        return employees.stream()
                .filter(emp -> emp.canWork(day))
                .findFirst()
                .orElse(null);
    }

    public void printSchedule() {
        for (String day : DAYS) {
            System.out.println("\n=== " + day + " ===");
            Map<String, Shift> shifts = weekSchedule.get(day);
            for (String type : SHIFT_TYPES) {
                Shift shift = shifts.get(type);
                System.out.printf("%-10s: ", type);
                List<Employee> employees = shift.getAssignedEmployees();
                if (employees.isEmpty()) {
                    System.out.println("No employees assigned");
                } else {
                    employees.forEach(e -> System.out.print(e.getName() + " "));
                    System.out.println();
                }
            }
        }
    }
}
