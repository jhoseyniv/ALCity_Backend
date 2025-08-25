package com.alcity.dto.plimpexport;

import java.io.Serializable;

public class CameraSetupData implements Serializable {

    PositionDTO Position;
    PositionDTO Rotation;
    FeaturesData features;
    InitialValuesDTO initialValues;

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

    public InitialValuesDTO getInitialValues() {
        return initialValues;
    }

    public void setInitialValues(InitialValuesDTO initialValues) {
        this.initialValues = initialValues;
    }

    public CameraSetupData() {
    }

    public CameraSetupData(PositionDTO position, PositionDTO rotation, FeaturesData features,InitialValuesDTO initialValues) {
        this.Position = position;
        this.Rotation = rotation;
        this.features = features;
        this.initialValues = initialValues;
    }
}
