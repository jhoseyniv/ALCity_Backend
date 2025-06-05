package com.alcity.dto.plimport;


import com.alcity.dto.plimport.object.PositionImport;

import java.io.Serializable;

public class CameraSetupImport implements Serializable {

    PositionImport Position;
    PositionImport Rotation;

    public PositionImport getPosition() {
        return Position;
    }

    public void setPosition(PositionImport position) {
        Position = position;
    }

    public PositionImport getRotation() {
        return Rotation;
    }

    public void setRotation(PositionImport rotation) {
        Rotation = rotation;
    }

    public CameraSetupImport() {
    }

    public CameraSetupImport(PositionImport Position, PositionImport Rotation) {
       this.Position= Position;
       this.Rotation = Rotation;
    }
}
