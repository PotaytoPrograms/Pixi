package me.potaytoprograms.pixi.api.ashley.components;

import com.badlogic.ashley.core.Component;

public class TransformComponent implements Component {

    public float x, y, width, height, scaleX, scaleY;

    public TransformComponent(float x, float y, float width, float height, float scaleX, float scaleY){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
}
