package org.example;
import java.util.*;

class User {
        private String username;
        public User(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }
    }
    class UserManager {
        private List<User> userList;
        public UserManager() {
            userList = new ArrayList<>();
        }
        public boolean createUser(String username) {
            if (isUsernameTaken(username)) {
                System.out.println("Sorry, please choose a different username.");
                return false;
            }
            User newUser = new User(username);
            userList.add(newUser);
            System.out.println("User created successfully!");
            return true;
        }
        public boolean login(String username) {
            if (isUsernameTaken(username)) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("User does not exist. Please try again.");
                return false;
            }
        }
        private boolean isUsernameTaken(String username) {
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
            return false;
        }
    }