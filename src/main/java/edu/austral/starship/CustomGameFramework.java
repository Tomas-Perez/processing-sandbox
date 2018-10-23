package edu.austral.starship;

import com.wawey.processing.sandbox.Test;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.Set;

public class CustomGameFramework implements GameFramework {
    Test test = new Test();

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        test.setup(windowsSettings, imageLoader);
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        test.draw(graphics, timeSinceLastDraw, keySet);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        test.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        test.keyReleased(event);
    }
}
