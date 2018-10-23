package com.wawey.processing.sandbox;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Perez Molina
 */
public class MySketch extends PApplet {

    private List<Ball> balls = new ArrayList<>();

    public void settings(){
        fullScreen();
        //size(800, 600);
        balls.add(new Ball(this, width/2, height/2));
    }

    public void draw(){
        background(64);
        for(Ball b : balls){
            b.step();
            b.render();
        }
    }

    public void mouseDragged(){
        balls.add(new Ball(this, mouseX, mouseY));
    }

    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        MySketch mySketch = new MySketch();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
