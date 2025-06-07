package com.alcity.dto.plimport;


import com.alcity.dto.plimport.object.FeatureImport;
import com.alcity.dto.plimport.object.PLGroundPositionImport;
import com.alcity.dto.plimport.object.PositionImport;

import java.io.Serializable;

public class CameraSetupImport implements Serializable {

    PLGroundPositionImport Position;
    PLGroundPositionImport Rotation;
    FeatureImport features;

    public PLGroundPositionImport getPosition() {
        return Position;
    }

    public void setPosition(PLGroundPositionImport position) {
        Position = position;
    }

    public PLGroundPositionImport getRotation() {
        return Rotation;
    }

    public void setRotation(PLGroundPositionImport rotation) {
        Rotation = rotation;
    }

    public FeatureImport getFeatures() {
        return features;
    }

    public void setFeatures(FeatureImport features) {
        this.features = features;
    }

    public CameraSetupImport() {
    }

    public CameraSetupImport(PLGroundPositionImport position, PLGroundPositionImport rotation, FeatureImport features) {
        Position = position;
        Rotation = rotation;
        this.features = features;
    }
}
