package com.driver;

import org.apache.commons.collections.map.HashedMap;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)) {
            if (passWordValidation(newPassword)) {
//                System.out.println("Password changed successfully");
                this.password = newPassword;
            } else {
//                System.out.println("The new password is not valid !!!");
            }
        } else {
//            System.out.println("Your Current Password is not in our database");
        }
    }

    private boolean passWordValidation(String password) {
        return password.length() >= 8 && password.matches(".[A-Z].")
                && password.matches(".[a-z].") && password.matches(".\\d.")
                && password.matches(".[^a-zA-Z0-9].");
    }
}