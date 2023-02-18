package com.driver;

import java.sql.SQLOutput;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword == this.password){
            int upperCase = 0, lowerCase = 0, digit = 0, specialCharacter = 0, alphabetic = 0;
            if(newPassword.length()>=8){
                for(int i=0; i<newPassword.length(); i++){
                    char c = newPassword.charAt(i);
                    if(Character.isAlphabetic(c)) alphabetic++;
                    if(Character.isDigit(c)) digit++;
                    if(Character.isUpperCase(c)) upperCase++;
                    if(Character.isLowerCase(c)) lowerCase++;
                    else specialCharacter++;
                }
                if(upperCase>0 && lowerCase>0 && digit>0&& specialCharacter>0){
                    this.password = newPassword;
//                    System.out.println(" Password changed Successfully ");
                }
                else{
//                    System.out.println(" Please Enter correct combination ");
                }
            }
            else{
//                System.out.println(" Please Enter password of 8 characters ");
            }
        }
        else{
//            System.out.println(" Old password is wrong ");
        }
    }
}
