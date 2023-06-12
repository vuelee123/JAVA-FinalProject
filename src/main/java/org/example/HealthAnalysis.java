package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.example.Colors.*;
class HealthAnalysis {
    private String username;
    HealthAnalysis(String username) {
        this.username = username;
    }
    public static void displayExerciseLog(String username) {
    }
    void displayHealthSummary() {
        System.out.println(ANSI_BLUE + "------ Health Summary ------" + ANSI_RESET);

        File calorieIntakeFile = new File(username + "calories_intake.txt");
        if (calorieIntakeFile.exists()) {
            System.out.println(ANSI_YELLOW + "-- Calorie Intake --" + ANSI_RESET);
            displayFileSummary(calorieIntakeFile);
        } else {
            System.out.println(ANSI_RED + "No data available." + ANSI_RESET);
        }
        System.out.println(ANSI_YELLOW + "-----------------------" + ANSI_RESET);

        File exerciseActivityFile = new File(username + "exercise_activity.txt");
        if (exerciseActivityFile.exists()) {
            System.out.println(ANSI_YELLOW + "\n-- Exercise Activity --" + ANSI_RESET);
            displayFileSummary(exerciseActivityFile);
        } else {
            System.out.println(ANSI_RED +"\nNo data available." + ANSI_RESET);
        }
        System.out.println(ANSI_YELLOW + "-----------------------" + ANSI_RESET);

        File sleepRecordFile = new File(username + "sleep_record.txt");
        if (sleepRecordFile.exists()) {
            System.out.println(ANSI_YELLOW + "\n-- Sleep Log --" + ANSI_RESET);
            displayFileSummary(sleepRecordFile);
        } else {
            System.out.println(ANSI_RED + "\nNo data available." + ANSI_RESET);
        }
        System.out.println(ANSI_YELLOW + "-----------------------" + ANSI_RESET);
    }
    private void displayFileSummary(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                System.out.println(ANSI_MAGENTA + "Date: " + data[0] + ANSI_RESET);

                if (file.getName().contains("calories_intake")) {
                    System.out.println(ANSI_BLUE + "Food Item: " + data[1]);
                    System.out.println("Calories: " + data[2] + ANSI_RESET);
                } else if (file.getName().contains("exercise_activity")) {
                    System.out.println(ANSI_GREEN + "Exercise Type: " + data[1]);
                    System.out.println("Duration: " + data[2]);
                    System.out.println("Calories Burned: " + data[3] + ANSI_RESET);
                } else if (file.getName().contains("sleep_record")) {
                    System.out.println(ANSI_CYAN + "Sleep Time: " + data[1]);
                    System.out.println("Wake Up Time: " + data[2] + ANSI_RESET);
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED + "Error reading file: " + e.getMessage() + ANSI_RESET);
        }
    }
}
