package com.alcity.dto.plimport.object;

import java.io.Serializable;

public class FeatureImport implements Serializable {
    private Boolean zoom;
    private Boolean pan;
    private Boolean rotation;

    public Boolean getZoom() {
        return zoom;
    }

    public void setZoom(Boolean zoom) {
        this.zoom = zoom;
    }

    public Boolean getPan() {
        return pan;
    }

    public void setPan(Boolean pan) {
        this.pan = pan;
    }

    public Boolean getRotation() {
        return rotation;
    }

    public void setRotation(Boolean rotation) {
        this.rotation = rotation;
    }

    public FeatureImport() {
    }

    public FeatureImport(Boolean zoom, Boolean pan, Boolean rotation) {
        this.zoom = zoom;
        this.pan = pan;
        this.rotation = rotation;
    }
}
