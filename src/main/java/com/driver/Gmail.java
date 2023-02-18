package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    class newMail{
        Date date;
        String sender;
        String message;

        public newMail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    int inboxSize = 0;
    int trashMailSize = 0;

    List<newMail> allMails = new ArrayList<>();
    List<newMail> trashMails = new ArrayList<>();
    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inboxCapacity>allMails.size()){
            newMail newOne = new newMail(date,sender,message);
            allMails.add(newOne);
        }
        else{
//            System.out.println(" Inbox size full, moving oldest mail to trash ");
            newMail oldestMail = allMails.get(0);
            trashMails.add(oldestMail);
            allMails.remove(oldestMail);
            newMail newOne = new newMail(date,sender,message);
            allMails.add(newOne);
        }

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0; i<allMails.size(); i++){
            if(allMails.get(i).message==message){
                trashMails.add(allMails.get(i));
                allMails.remove(i);
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        return allMails.get(allMails.size()-1).message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(allMails.isEmpty()) return null;
        return allMails.get(0).message;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int mailsBetweenDate = 0;
        for(int i=0; i<allMails.size(); i++){
            Date mailDate = allMails.get(i).date;
            if((mailDate.before(end) && mailDate.after(start)) || mailDate.equals(start) || mailDate.equals(end) ) mailsBetweenDate++;
        }
        return mailsBetweenDate;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return allMails.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashMails.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashMails.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
