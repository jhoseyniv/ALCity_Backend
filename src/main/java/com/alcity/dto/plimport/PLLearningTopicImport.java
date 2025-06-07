package com.alcity.dto.plimport;

import com.alcity.entity.learning.LearningTopic;

import java.io.Serializable;

public class PLLearningTopicImport implements Serializable {
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

    public PLLearningTopicImport(Long id, String title, Long learningContentId) {
        this.id = id;
        this.title = title;
        this.learningContentId = learningContentId;
    }
}
