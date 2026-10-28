package io.github.Isabelnorton;

import java.util.HashSet;
import java.util.Set;


public class GameTrackers {

    private float timeRemaining =300.0f;
    private int itemsUsed = 0;
    private final Set<String> encounteredEvents = new HashSet<>();

    private boolean paused = false;
    private boolean gameOver = false;

    public void update(float deltaTime){
        if (paused || gameOver){
            return;
        }
        timeRemaining -= deltaTime;
        if (timeRemaining <= 0){
            timeRemaining = 0;
            gameOver = true;
        }
    }
    public void deductTime(){
        if (!paused && !gameOver){
            timeRemaining -= 5;
            if (timeRemaining <= 0){
                timeRemaining = 0;
                gameOver = true;
            }
        }
    }
    public void recordItemUsed(){
        if (!paused && !gameOver){
            itemsUsed++;
        }
    }
    public void recordHiddenEvent(String eventID){
        if (!paused && !gameOver && eventID != null){
            encounteredEvents.add(eventID);
        }
    }
    public void setPaused(boolean paused){
        this.paused = paused;
    }
    public int getTimeRemaining(){
        return (int) Math.ceil(timeRemaining);
    }
    public String getFormattedTime(){
        int totalSeconds = getTimeRemaining();
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    public int getItemsUsed(){
        return itemsUsed;
    }
    public int getEventsEncountered(){
        return encounteredEvents.size();
    }
    public boolean isGameOver(){
        return gameOver;
    }
    public boolean isPaused(){
        return paused;
    }
}
