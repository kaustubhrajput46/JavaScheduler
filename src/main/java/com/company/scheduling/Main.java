package com.company.scheduling;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        ScheduleManager manager = new ScheduleManager();

        Employee john = new Employee("John");
        Employee mary = new Employee("Mary");
        Employee peter = new Employee("Peter");
        Employee sarah = new Employee("Sarah");

        addEmployeePreferences(john);
        addEmployeePreferences(mary);
        addEmployeePreferences(peter);
        addEmployeePreferences(sarah);

        manager.addEmployee(john);
        manager.addEmployee(mary);
        manager.addEmployee(peter);
        manager.addEmployee(sarah);

        // Generate and print schedule
        manager.generateSchedule();
        manager.printSchedule();
    }

    private static void addEmployeePreferences(Employee emp) {
        Random rand = new Random();
        List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        List<String> shifts = Arrays.asList("Morning", "Afternoon", "Evening");

        for (String day : days) {
            // Randomly assign 1-3 preferences for each day
            int numPrefs = rand.nextInt(3) + 1;
            List<String> availableShifts = new ArrayList<>(shifts);
            Collections.shuffle(availableShifts);

            for (int i = 0; i < numPrefs; i++) {
                emp.addPreference(day, availableShifts.get(i), numPrefs - i);
            }
        }
    }

}
