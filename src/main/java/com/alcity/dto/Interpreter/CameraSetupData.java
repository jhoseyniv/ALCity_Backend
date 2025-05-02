package com.alcity.dto.Interpreter;

import com.alcity.dto.Interpreter.object.Features;
import com.alcity.dto.Interpreter.object.PLGroundPostion;
import com.alcity.dto.Interpreter.object.Position;

import java.io.Serializable;

public class CameraSetupData implements Serializable {

    PLGroundPostion Position;
    PLGroundPostion Rotation;
    Features Feature;

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


    public Features getFeature() {
        return Feature;
    }

    public void setFeature(Features feature) {
        Feature = feature;
    }

    public CameraSetupData() {
    }

    public CameraSetupData(com.alcity.dto.Interpreter.object.PLGroundPostion position, com.alcity.dto.Interpreter.object.PLGroundPostion rotation, Features feature) {
        Position = position;
        Rotation = rotation;
        Feature = feature;
    }
}
