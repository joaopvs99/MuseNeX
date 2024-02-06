package com.ipca_project.musenex;

import processing.core.PApplet;

public class Sketch extends PApplet {

    float angle = 0;
    int height = 1000;
    int width = 1000;

    public void settings() {
        size(height, width, P3D);
    }

    public void setup() {
        fill(255);
    }

    public void draw() {
        background(0);
        translate(height/2, width/2, 0);
        rotateY(angle);
        angle += 0.01;
        box(200, 200, 200);
    }
}

