package io.github.Isabelnorton;

// import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CountdownTimer {
    private BitmapFont font;

    // 5 minutes = 300 seconds
    private float timeRemaining;
    private boolean timerFinished;

    public CountdownTimer() {
        timeRemaining = 5f;
        timerFinished = false;
        font = new BitmapFont(); // Default LibGDX font
        font.getData().setScale(2.0f); // Scale font up for readability
    }

    public void draw(float delta, SpriteBatch batch) {
        // 1. Accumulate frame time and decrease timer
        if (!timerFinished) {
            timeRemaining -= delta;
            if (timeRemaining <= 0) {
                timeRemaining = 0;
                timerFinished = true;
                onTimerFinished();
            }
        }

        // 2. Format remaining seconds to MM:SS format
        int minutes = (int) (timeRemaining / 60);
        int seconds = (int) (timeRemaining % 60);
        String timeText = String.format("%02d:%02d", minutes, seconds);

        // 3. Clear screen and render
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
        
        if (timerFinished) {
            font.draw(batch, "TIME'S UP!", 150, 450/2);
        } else {
            font.draw(batch, "Time Remaining: " + timeText, 300, 450);
        }
        
    }

    private void onTimerFinished() {
        Gdx.app.log("Timer", "5 minutes elapsed!");
        return TimeFinished

    public void dispose() {
        font.dispose();
    }




}