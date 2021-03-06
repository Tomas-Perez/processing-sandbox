package edu.austral.starship.base.framework;

import edu.austral.starship.CustomGameFramework;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class GameManager extends PApplet {
    private final GameFramework gameFramework = new CustomGameFramework();
    private final Set<Integer> keySet = new HashSet<>();
    private final WindowSettings windowsSettings = new WindowSettings(this);

    public void settings() {
        gameFramework.setup(windowsSettings, new ImageLoader(this));
    }

    public void setup() {
        windowsSettings.applyFrameRate();
    }

    public void draw() {
        clear();

        final float timeSinceLastFrame = (frameRate / 60) * 100;
        gameFramework.draw(g, timeSinceLastFrame, keySet);
    }

    public void keyPressed(KeyEvent event) {
        keySet.add(event.getKeyCode());

        gameFramework.keyPressed(event);
    }

    public void keyReleased(KeyEvent event) {
        keySet.add(event.getKeyCode());

        gameFramework.keyReleased(event);
    }
}
