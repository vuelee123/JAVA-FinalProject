package org.example;

import java.util.Scanner;

import static org.example.Colors.ANSI_RESET;
import static org.example.Colors.ANSI_YELLOW;

class HealthData {
    private String date;
    private int value;

    public HealthData(String date, int value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }
}

class CalorieIntake {
    private String date;
    private String foodItem;
    private int calories;

    public CalorieIntake(String date, String foodItem, int calories) {
        this.date = date;
        this.foodItem = foodItem;
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public int getCalories() {
        return calories;
    }
}

class ExerciseActivity {
    private String date;
    private String type;
    private int duration;
    private int caloriesBurned;

    public ExerciseActivity(String date, String type, int duration, int caloriesBurned) {
        this.date = date;
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }
}

class SleepRecord {
    private String date;
    private String sleepTime;
    private String wakeUpTime;

    public SleepRecord(String date, String sleepTime, String wakeUpTime) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.wakeUpTime = wakeUpTime;
    }

    public String getDate() {
        return date;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public String getWakeUpTime() {
        return wakeUpTime;
    }
}

class HealthDataInput {
    private static Scanner scanner = new Scanner(System.in);
    public static CalorieIntake enterCalorieIntake() {
        System.out.print(ANSI_YELLOW + "Enter the date (mm-dd-yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Enter the food item: ");
        String foodItem = scanner.nextLine();
        System.out.print("Enter the caloric value: " + ANSI_RESET);
        int calories = scanner.nextInt();
        scanner.nextLine();

        return new CalorieIntake(date, foodItem, calories);
    }
    public static ExerciseActivity enterExerciseActivity() {
        System.out.print(ANSI_YELLOW + "Enter the date (mm-dd-yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Enter the exercise: ");
        String type = scanner.nextLine();
        System.out.print("Enter the duration in minutes: ");
        int duration = scanner.nextInt();
        System.out.print("Enter the estimated calories burned: " + ANSI_RESET);
        int caloriesBurned = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        return new ExerciseActivity(date, type, duration, caloriesBurned);
    }
    public static SleepRecord enterSleepRecord() {
        System.out.print(ANSI_YELLOW + "Enter the date (mm-dd-yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Enter the sleep time (hh:mm): ");
        String sleepTime = scanner.nextLine();
        System.out.print("Enter the wake-up time (hh:mm): " + ANSI_RESET);
        String wakeUpTime = scanner.nextLine();

        return new SleepRecord(date, sleepTime, wakeUpTime);
    }
}