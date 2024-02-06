package com.ipca_project.musenex;

import processing.core.PApplet;
import processing.core.PShape;

public class Sketch extends PApplet {

    float angle = 0;
    PShape vase;
    int height = 1000;
    int width = 1000;

    public void settings() {
        size(height, width, P3D);
    }

    public void setup() {
        fill(255);
        vase = loadShape("obj/vase.obj");

    }

    public void draw() {
        background(100);
        lights();
        float nearClip = 1; // Adjust this value according to your scene
        float farClip = 1000; // Adjust this value according to your scene
        perspective(PI/3, (float)width/height, nearClip, farClip);
        translate(height/2, width/2, 810);
        rotateZ(-PI);
        rotateY(map(mouseX, mouseY, width, (float)2.5, (float)-2.5));
        pushMatrix();
        rotateX(-PI/2);
        shape(vase);
        popMatrix();
    }
}