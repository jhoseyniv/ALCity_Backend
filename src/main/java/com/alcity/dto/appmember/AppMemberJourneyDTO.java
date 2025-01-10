package com.alcity.dto.appmember;

import java.util.Collection;

public class AppMemberJourneyDTO {
    private Long id;
    private String title;
    private Long passedImageId;
    private Long currentImageId;
    private Long lockedImageId;

    private Collection<RoadMapDTO> roadMapDTOS;
    private Collection<AppMemberJourneyStepDTO> journeyStepsDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<RoadMapDTO> getRoadMapDTOS() {
        return roadMapDTOS;
    }

    public void setRoadMapDTOS(Collection<RoadMapDTO> roadMapDTOS) {
        this.roadMapDTOS = roadMapDTOS;
    }

    public Collection<AppMemberJourneyStepDTO> getJourneyStepsDTO() {
        return journeyStepsDTO;
    }

    public void setJourneyStepsDTO(Collection<AppMemberJourneyStepDTO> journeyStepsDTO) {
        this.journeyStepsDTO = journeyStepsDTO;
    }

    public Long getPassedImageId() {
        return passedImageId;
    }

    public void setPassedImageId(Long passedImageId) {
        this.passedImageId = passedImageId;
    }

    public Long getCurrentImageId() {
        return currentImageId;
    }

    public void setCurrentImageId(Long currentImageId) {
        this.currentImageId = currentImageId;
    }

    public Long getLockedImageId() {
        return lockedImageId;
    }

    public void setLockedImageId(Long lockedImageId) {
        this.lockedImageId = lockedImageId;
    }

    public AppMemberJourneyDTO() {
    }

    public AppMemberJourneyDTO(Long id, String title, Long passedImageId, Long currentImageId, Long lockedImageId,
                               Collection<RoadMapDTO> roadMapDTOS, Collection<AppMemberJourneyStepDTO> journeyStepsDTO) {
        this.id = id;
        this.title = title;
        this.passedImageId = passedImageId;
        this.currentImageId = currentImageId;
        this.lockedImageId = lockedImageId;
        this.roadMapDTOS = roadMapDTOS;
        this.journeyStepsDTO = journeyStepsDTO;
    }
}
