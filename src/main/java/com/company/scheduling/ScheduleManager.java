package com.company.scheduling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleManager {
    private List<Employee> employees;
    private Map<String, Map<String, Shift>> weekSchedule; // Day → ShiftType → Shift

    public ScheduleManager() {
        employees = new ArrayList<>();
        weekSchedule = new HashMap<>();
        initializeSchedule();
    }

    private void initializeSchedule() {
        // Create 7 days * 3 shifts each
        for (String day : List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")) {
            Map<String, Shift> dailyShifts = new HashMap<>();
            for (String type : List.of("Morning", "Afternoon", "Evening")) {
                dailyShifts.put(type, new Shift(day, type));
            }
            weekSchedule.put(day, dailyShifts);
        }
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void generateSchedule() {
        for (String day : weekSchedule.keySet()) {
            Map<String, Shift> shifts = weekSchedule.get(day);
            for (Employee emp : employees) {
                List<String> prefs = emp.getPreferences().get(day);
                if (prefs == null || emp.getAssignedDays() >= 5) continue;

                for (String pref : prefs) {
                    Shift shift = shifts.get(pref);
                    if (!shift.isFull() && emp.canWork(day)) {
                        shift.addEmployee(emp);
                        break;
                    }
                }
                // Handle conflict cases if all preferred shifts full
            }
        }
        // Fill insufficiently staffed shifts
    }

    public void printSchedule() {
        for (String day : weekSchedule.keySet()) {
            System.out.println("--- " + day + " ---");
            Map<String, Shift> shifts = weekSchedule.get(day);
            for (String type : shifts.keySet()) {
                Shift s = shifts.get(type);
                System.out.print(type + ": ");
                for (Employee e : s.getAssignedEmployees()) {
                    System.out.print(e.getName() + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
