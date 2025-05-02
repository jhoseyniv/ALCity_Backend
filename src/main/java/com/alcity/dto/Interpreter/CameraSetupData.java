package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.Features;
import com.alcity.dto.Interpreter.object.PLGroundPostion;
import com.alcity.dto.Interpreter.object.Position;

import java.io.Serializable;

public class CameraSetupData implements Serializable {

    PLGroundPostion Position;
    PLGroundPostion Rotation;
    Features Features;

    public PLGroundPostion getPosition() {
        return Position;
    }

    public void setPosition(PLGroundPostion position) {
        Position = position;
    }

    public PLGroundPostion getRotation() {
        return Rotation;
    }

    public void setRotation(PLGroundPostion rotation) {
        Rotation = rotation;
    }

    public com.alcity.dto.Interpreter.object.Features getFeatures() {
        return Features;
    }

    public void setFeatures(com.alcity.dto.Interpreter.object.Features features) {
        Features = features;
    }

    public CameraSetupData() {
    }

    public CameraSetupData(com.alcity.dto.Interpreter.object.PLGroundPostion position, com.alcity.dto.Interpreter.object.PLGroundPostion rotation, Features features) {
        Position = position;
        Rotation = rotation;
        Features = features;
    }
}
