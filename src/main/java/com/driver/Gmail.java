package com.driver;

import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Date;

class EmailObj{
    Date date;
    String sender;
    String message;

    public EmailObj(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}
public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    ArrayList<EmailObj> Inbox;
    ArrayList<EmailObj> Trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        Inbox = new ArrayList<>();
        Trash = new ArrayList<>();

    }

    public void receiveMail(Date date, String sender, String message){
        if(Inbox.size() == inboxCapacity){
            EmailObj emailTemplate = Inbox.get(0);
            Inbox.remove(0);
            Trash.add(emailTemplate);
        }

        EmailObj emailTemplate = new EmailObj(date, sender , message);
        Inbox.add(emailTemplate);

    }

    public void deleteMail(String message){

        EmailObj emailTemplate = null;
        for(int i=0; i<Inbox.size(); i++){
            EmailObj emailTemplate1 = Inbox.get(i);
            if(emailTemplate1.message.equals(message)){
                emailTemplate = emailTemplate1;
                break;
            }
        }
        if(emailTemplate != null){
            Inbox.remove(emailTemplate);
            Trash.add(emailTemplate);
        }
    }

    public String findLatestMessage(){

        if(Inbox.isEmpty()){
            return null;
        }


        EmailObj emailTemplate = Inbox.get(Inbox.size() - 1);
        return emailTemplate.message;
    }

    public String findOldestMessage(){

        if(Inbox.isEmpty()){
            return null;
        }


        EmailObj emailTemplate = Inbox.get(0);
        return emailTemplate.message;
    }

    public int findMailsBetweenDates(Date start, Date end){

        int count = 0;
        for(int i=0; i<Inbox.size(); i++){
            EmailObj emailTemplate = Inbox.get(i);
            //Compare the Date
            if((emailTemplate.date.compareTo(start) >= 0) && (emailTemplate.date.compareTo(end) <= 0)){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}