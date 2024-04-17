package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.Position;

public class CameraSetupData {

    Position Position;
    Position Rotation;

    public com.alcity.dto.Interpreter.object.Position getPosition() {
        return Position;
    }

    public void setPosition(com.alcity.dto.Interpreter.object.Position position) {
        Position = position;
    }

    public com.alcity.dto.Interpreter.object.Position getRotation() {
        return Rotation;
    }

    public void setRotation(com.alcity.dto.Interpreter.object.Position rotation) {
        Rotation = rotation;
    }

    public CameraSetupData() {
    }

    public CameraSetupData(Position Position, Position Rotation) {
       this.Position= Position;
       this.Rotation = Rotation;
    }
}
