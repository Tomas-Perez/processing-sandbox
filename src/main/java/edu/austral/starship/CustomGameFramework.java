package edu.austral.starship;

import com.wawey.processing.StarshipGame;
import edu.austral.starship.base.framework.GameFramework;
import edu.austral.starship.base.framework.ImageLoader;
import edu.austral.starship.base.framework.WindowSettings;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.Set;

public class CustomGameFramework implements GameFramework {
    StarshipGame starshipGame = new StarshipGame();

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        starshipGame.setup(windowsSettings, imageLoader);
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        starshipGame.draw(graphics, timeSinceLastDraw, keySet);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        starshipGame.keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        starshipGame.keyReleased(event);
    }
}
