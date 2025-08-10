package com.alcity.dto.plimpexport;

import java.io.Serializable;

public class CameraSetupData implements Serializable {

    PositionDTO Position;
    PositionDTO Rotation;
    FeaturesData features;

    public PositionDTO getPosition() {
        return Position;
    }

    public void setPosition(PositionDTO position) {
        Position = position;
    }

    public PositionDTO getRotation() {
        return Rotation;
    }

    public void setRotation(PositionDTO rotation) {
        Rotation = rotation;
    }

    public FeaturesData getFeatures() {
        return features;
    }

    public void setFeatures(FeaturesData features) {
        this.features = features;
    }

    public CameraSetupData() {
    }

    public CameraSetupData(PositionDTO position, PositionDTO rotation, FeaturesData features) {
        Position = position;
        Rotation = rotation;
        features = features;
    }
}
