package com.alcity.dto.plexport;

import com.alcity.dto.plexport.object.Features;
import com.alcity.dto.plexport.object.PLGroundPostion;

import java.io.Serializable;

public class CameraSetupData implements Serializable {

    PLGroundPostion Position;
    PLGroundPostion Rotation;
    Features features;

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

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public CameraSetupData() {
    }

    public CameraSetupData(com.alcity.dto.plexport.object.PLGroundPostion position, com.alcity.dto.plexport.object.PLGroundPostion rotation, Features features) {
        Position = position;
        Rotation = rotation;
        features = features;
    }
}
