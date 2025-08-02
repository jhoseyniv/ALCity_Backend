package com.alcity.dto.Interpreter.object;

import com.alcity.dto.plimport.object.PLRulePostActionImport;
import com.alcity.dto.plimport.object.PostActionTreeImport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostActionTreeExport<P> implements Serializable {

    public RuleActionData postAction;
    public List<PostActionTreeExport<RuleActionData>> children;

    public RuleActionData getPostAction() {
        return postAction;
    }

    public void setPostAction(RuleActionData postAction) {
        this.postAction = postAction;
    }

    public List<PostActionTreeExport<RuleActionData>> getChildren() {
        return children;
    }

    public void setChildren(List<PostActionTreeExport<RuleActionData>> children) {
        this.children = children;
    }

    public PostActionTreeExport() {
    }

    public PostActionTreeExport(RuleActionData postAction, List<PostActionTreeExport<RuleActionData>> children) {
        this.postAction = postAction;
        this.children = children;
    }
    public PostActionTreeExport(RuleActionData value) {
        this.postAction = value;
        this.children = new ArrayList<>();
    }
    // Optional: add a method to add a child
    public void addChild(PostActionTreeExport<RuleActionData> child) {
        children.add(child);
    }

    // Optional: enforce a maximum of N children
    public void addChild(PostActionTreeExport<RuleActionData> child, int maxChildren) {
        if (children.size() < maxChildren) {
            children.add(child);
        } else {
            throw new IllegalStateException("Maximum number of children (" + maxChildren + ") exceeded");
        }
    }


}
