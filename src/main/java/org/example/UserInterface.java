package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.example.Colors.*;

class UserInterface {
        private static Scanner scanner = new Scanner(System.in);
        private static Map<String, User> users = new HashMap<>();
        private static HealthAnalysis healthAnalysis;
        private static String username;

        public static void main(String[] args) {
            boolean exit = false;

            while (!exit) {
                displayMainMenu();
                int choice = getUserChoice();

                switch (choice) {
                    case 1:
                        createUser();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        exit = true;
                        System.out.println(ANSI_YELLOW + "Exiting the program..." + ANSI_RESET);
                        break;
                    default:
                        System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                }
            }
        }
        private static void displayMainMenu() {
            System.out.println(ANSI_BLUE + "------ Health Tracker System ------" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "1. Create User");
            System.out.println("2. Log In");
            System.out.println("3. Exit" + ANSI_RESET);
            System.out.print(ANSI_MAGENTA + "Enter your choice: " + ANSI_RESET);
        }
        private static int getUserChoice() {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        }
        private static void createUser() {
            System.out.print(ANSI_GREEN + "Enter your username: " + ANSI_RESET);
            String username = scanner.nextLine();

            if (userExists(username)) {
                System.out.println(ANSI_RED + "Sorry, please try again with a different username." + ANSI_RESET);
                return;
            }
            User newUser = new User(username);
            users.put(username, newUser);

            System.out.println("Username created successfully!");
        }
        private static void login() {
            System.out.print(ANSI_GREEN + "Enter your username: " + ANSI_RESET);
            String username = scanner.nextLine();

            if (userExists(username)) {
                UserInterface.username = username;
                enterHealthData(username);
            } else {
                System.out.println(ANSI_RED + "User does not exist. Please try again." + ANSI_RESET);
            }
        }
        private static boolean userExists(String username) {
            return users.containsKey(username);
        }
    private static void enterHealthData(String username) {
        boolean exit = false;
        while (!exit) {
            displayHealthDataMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    enterCalorieIntake(username);
                    break;
                case 2:
                    enterExerciseActivity(username);
                    break;
                case 3:
                    enterSleepRecord(username);
                    break;
                case 4:
                    viewHealthDataAnalysis();
                    break;
                case 5:
                    exit = true;
                    System.out.println(ANSI_MAGENTA + "Returning to the main menu..." + ANSI_RESET);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }
    private static void displayHealthDataMenu() {
        System.out.println(ANSI_BLUE + "------ Health Data Input ------" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1. Enter Calorie Intake");
        System.out.println("2. Enter Exercise Activity");
        System.out.println("3. Enter Sleep Record");
        System.out.println("4. View Health Data Analysis");
        System.out.println("5. Return to Main Menu" + ANSI_RESET);
        System.out.print(ANSI_MAGENTA + "Enter your choice: " + ANSI_RESET);
    }
    private static void enterCalorieIntake(String username) {
        CalorieIntake calorieIntake = HealthDataInput.enterCalorieIntake();

        User user = users.get(username);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(username + "calories_intake.txt", true))) {
            writer.println(calorieIntake.getDate() + "," + calorieIntake.getFoodItem() + "," + calorieIntake.getCalories());
            System.out.println("Calorie intake recorded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED + "Cannot store calorie intake data. Please try again." + ANSI_RESET);
        }
    }
    private static void enterExerciseActivity(String username) {
        ExerciseActivity exerciseActivity = HealthDataInput.enterExerciseActivity();

        User user = users.get(username);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(username + "exercise_activity.txt", true))) {
            writer.println(exerciseActivity.getDate() + "," + exerciseActivity.getType() + "," +
                    exerciseActivity.getDuration() + "," + exerciseActivity.getCaloriesBurned());
            System.out.println("Exercise activity recorded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED + "Cannot store exercise activity data. Please try again." + ANSI_RESET);
        }
    }
    private static void enterSleepRecord(String username) {
        SleepRecord sleepRecord = HealthDataInput.enterSleepRecord();

        User user = users.get(username);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(username + "sleep_record.txt", true))) {
            writer.println(sleepRecord.getDate() + "," + sleepRecord.getSleepTime() + "," + sleepRecord.getWakeUpTime());
            System.out.println("Sleep record recorded successfully!");
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_RED + "Cannot store sleep log data. Please try again." + ANSI_RESET);
        }
    }

    private static void viewHealthDataAnalysis() {
        System.out.print(ANSI_GREEN + "Enter your username: " + ANSI_RESET);
        String username = scanner.nextLine();

        System.out.println(ANSI_BLUE + "------ Health Analysis ------" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1. Display Health Summary");
        System.out.println("0. Exit" + ANSI_RESET);

        int choice;
        do {
            System.out.print(ANSI_MAGENTA + "\nEnter your choice: " + ANSI_RESET);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    HealthAnalysis healthAnalysis = new HealthAnalysis(username);
                    healthAnalysis.displayHealthSummary();
                    break;
                case 0:
                    System.out.println(ANSI_MAGENTA + "Exiting..." + ANSI_RESET);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                    break;
            }
        } while (choice != 0);
    }
}
