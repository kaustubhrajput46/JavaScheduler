package com.company.scheduling;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        ScheduleManager manager = new ScheduleManager();

        List<Employee> employees = Arrays.asList(
                new Employee("John"),
                new Employee("Mary"),
                new Employee("Peter"),
                new Employee("Sarah"),
                new Employee("Mike"),
                new Employee("Lisa"),
                new Employee("David"),
                new Employee("Emma")
        );

        for (Employee emp : employees) {
            addEmployeePreferences(emp);
            manager.addEmployee(emp);
        }

        // Generate and print schedule
        manager.generateSchedule();
        manager.printSchedule();
    }

    private static void addEmployeePreferences(Employee emp) {
        Random rand = new Random();
        List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        List<String> shifts = Arrays.asList("Morning", "Afternoon", "Evening");

        // Ensure each employee has preferences for at least 5 days
        for (String day : days) {
            // Add all shifts with different priorities
            int priority = 3;
            for (String shift : shifts) {
                emp.addPreference(day, shift, priority--);
            }
        }
    }
}
