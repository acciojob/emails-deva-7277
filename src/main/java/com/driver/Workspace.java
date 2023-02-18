package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Workspace extends Gmail{

    ArrayList<Meeting> calendar = new ArrayList<>(); // Stores all the meetings


    public Workspace(String emailId) {
        super(emailId, Integer.MAX_VALUE);
        // The inboxCapacity is equal to the maximum value an integer can store.
    }

    boolean isFirstMeeting = true;
    public void addMeeting(Meeting meeting){
        boolean isPossible = true;
        if(isFirstMeeting){
            calendar.add(meeting);
            isFirstMeeting = false;
        }
        else {
            for (int i = 0; i < calendar.size(); i++) {
                LocalTime oldStart = calendar.get(i).getStartTime();
                LocalTime oldEnd = calendar.get(i).getEndTime();

                if((meeting.getStartTime().isAfter(oldStart) && meeting.getEndTime().isBefore(oldStart))
                    && meeting.getEndTime().isBefore(oldEnd) && meeting.getStartTime().isAfter(oldStart)) isPossible = false;
            }

            if(isPossible) calendar.add(meeting);
        }


    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        return calendar.size();
    }
}
