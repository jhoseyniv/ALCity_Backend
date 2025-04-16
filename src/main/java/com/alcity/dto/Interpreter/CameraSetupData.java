package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.Position;

import java.io.Serializable;

public class CameraSetupData implements Serializable {

    Position Position;
    Position Rotation;

    public Position getPosition() {
        return Position;
    }

    public void setPosition(Position position) {
        Position = position;
    }

    public Position getRotation() {
        return Rotation;
    }

    public void setRotation(Position rotation) {
        Rotation = rotation;
    }

    public CameraSetupData() {
    }

    public CameraSetupData(Position Position, Position Rotation) {
       this.Position= Position;
       this.Rotation = Rotation;
    }
}
