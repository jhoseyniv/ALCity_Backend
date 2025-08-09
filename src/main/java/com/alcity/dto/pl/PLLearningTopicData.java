package com.alcity.dto.pl;

import java.io.Serializable;

public class PLLearningTopicData implements Serializable {
    private Long id;
    private String title;

    private Long learningContentId;

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

    public Long getLearningContentId() {
        return learningContentId;
    }

    public void setLearningContentId(Long learningContentId) {
        this.learningContentId = learningContentId;
    }

    public PLLearningTopicData(Long id, String title, Long learningContentId) {
        this.id = id;
        this.title = title;
        this.learningContentId = learningContentId;
    }
}
