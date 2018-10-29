package edu.austral.starship.base.framework;

import processing.core.PApplet;

public class WindowSettings {
    private final PApplet applet;
    private int frameRate = 60;

    public WindowSettings(PApplet applet) {
        this.applet = applet;
    }

    public WindowSettings setSize(int width, int height) {
        applet.size(width, height);
        return this;
    }

    public WindowSettings enableHighPixelDensity() {
        applet.pixelDensity(2);
        return this;
    }

    public WindowSettings disableCursor() {
        applet.noCursor();
        return this;
    }

    public WindowSettings enableCursor() {
        applet.cursor();
        return this;
    }

    public WindowSettings fullScreen() {
        applet.fullScreen();
        return this;
    }

    public WindowSettings setFrameRate(int rate) {
        frameRate = rate;
        return this;
    }

    void applyFrameRate() {
        applet.frameRate(frameRate);
    }
}
