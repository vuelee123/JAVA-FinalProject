package org.example;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
class DataManagement {
    private static final String DATA_DIRECTORY = "data/";
    private static final String CALORIE_INTAKE_FILE = DATA_DIRECTORY + "calorie_intake.txt";
    private static final String EXERCISE_ACTIVITY_FILE = DATA_DIRECTORY + "exercise_activity.txt";
    private static final String SLEEP_RECORD_FILE = DATA_DIRECTORY + "sleep_record.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static List<CalorieIntake> readCalorieIntakeData() {
        List<CalorieIntake> calorieIntakeList = new ArrayList<>();
        return calorieIntakeList;
    }
}
