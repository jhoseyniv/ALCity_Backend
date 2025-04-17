package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.Features;
import com.alcity.dto.Interpreter.object.Position;

import java.io.Serializable;

public class CameraSetupData implements Serializable {

    Position Position;
    Position Rotation;
    Features features;

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

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public CameraSetupData() {
    }

    public CameraSetupData(com.alcity.dto.Interpreter.object.Position position, com.alcity.dto.Interpreter.object.Position rotation, Features features) {
        Position = position;
        Rotation = rotation;
        this.features = features;
    }
}
