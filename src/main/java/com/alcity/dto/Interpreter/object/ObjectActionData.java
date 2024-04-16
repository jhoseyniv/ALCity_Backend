package com.alcity.dto.Interpreter.object;

public class ObjectActionData {
    private Long id;
    private String actionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public ObjectActionData() {
    }

    public ObjectActionData(Long id, String actionName) {
        this.id = id;
        this.actionName = actionName;
    }
}
