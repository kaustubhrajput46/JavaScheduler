
package com.company.scheduling;

import java.util.*;
import java.util.stream.Collectors;

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
        // Reset all assignments
        resetEmployeeAssignments();

        // Use a more balanced approach
        assignEmployeesBalanced();

        // Fill any remaining understaffed shifts
        fillUnderstaffedShifts();
    }

    private void resetEmployeeAssignments() {
        for (Employee emp : employees) {
            emp.reset(); // We need to add this method to Employee class
        }
    }

    private void assignEmployeesBalanced() {
        // Create a list of all shifts that need staffing
        List<ShiftSlot> allShifts = new ArrayList<>();

        for (String day : DAYS) {
            for (String shiftType : SHIFT_TYPES) {
                Shift shift = weekSchedule.get(day).get(shiftType);
                // Each shift needs 2 employees, so create 2 slots
                allShifts.add(new ShiftSlot(day, shiftType, shift, 1));
                allShifts.add(new ShiftSlot(day, shiftType, shift, 2));
            }
        }

        // Shuffle to randomize assignment order
        Collections.shuffle(allShifts);

        // Sort employees by current workload (ascending)
        for (ShiftSlot slot : allShifts) {
            List<Employee> availableEmployees = employees.stream()
                    .filter(emp -> emp.canWork(slot.day))
                    .filter(emp -> emp.getAssignedDays() < 5)
                    .sorted(Comparator.comparingInt(Employee::getAssignedDays))
                    .collect(Collectors.toList());

            if (!availableEmployees.isEmpty()) {
                Employee selected = selectBestEmployee(availableEmployees, slot);
                if (selected != null) {
                    slot.shift.addEmployee(selected);
                }
            }
        }
    }

    private Employee selectBestEmployee(List<Employee> candidates, ShiftSlot slot) {
        // Prefer employees with fewer assigned days
        return candidates.stream()
                .min(Comparator.comparingInt(Employee::getAssignedDays))
                .orElse(null);
    }

    private void fillUnderstaffedShifts() {
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
                .filter(emp -> emp.getAssignedDays() < 5)
                .min(Comparator.comparingInt(Employee::getAssignedDays))
                .orElse(null);
    }

    public void printSchedule() {
        System.out.println("\n=== WEEKLY SCHEDULE ===");
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
                    System.out.println(" (" + employees.size() + " employees)");
                }
            }
        }

        // Print employee workload summary
        printWorkloadSummary();
    }

    private void printWorkloadSummary() {
        System.out.println("\n=== EMPLOYEE WORKLOAD SUMMARY ===");
        for (Employee emp : employees) {
            System.out.println(emp.getName() + ": " + emp.getAssignedDays() + " days assigned");
        }
    }

    // Helper class for shift assignment
    private static class ShiftSlot {
        String day;
        String shiftType;
        Shift shift;
        int slotNumber;

        ShiftSlot(String day, String shiftType, Shift shift, int slotNumber) {
            this.day = day;
            this.shiftType = shiftType;
            this.shift = shift;
            this.slotNumber = slotNumber;
        }
    }
}