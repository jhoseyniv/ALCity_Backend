package com.alcity.dto.plimpexport.rulemport;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostActionTreeImport<P> implements Serializable {
    public PostActionTreeImport() {
    }

    public PostActionTreeImport(PLRulePostActionImport postAction, List<PostActionTreeImport<PLRulePostActionImport>> children) {
        this.postAction = postAction;
        this.children = children;
    }

    public PLRulePostActionImport postAction;
    public List<PostActionTreeImport<PLRulePostActionImport>> children;

    public PostActionTreeImport(PLRulePostActionImport value) {
        this.postAction = value;
        this.children = new ArrayList<>();
    }

    // Optional: add a method to add a child
    public void addChild(PostActionTreeImport<PLRulePostActionImport> child) {
        children.add(child);
    }

    // Optional: enforce a maximum of N children
    public void addChild(PostActionTreeImport<PLRulePostActionImport> child, int maxChildren) {
        if (children.size() < maxChildren) {
            children.add(child);
        } else {
            throw new IllegalStateException("Maximum number of children (" + maxChildren + ") exceeded");
        }
    }

     public static void main(String[] args) {

        /*
        PLRulePostActionImport root0= new PLRulePostActionImport("postActionType_1", "postActionOwnerType_1",1,
                "1", "root","subAction_1",1L,"variable_1", new StringBuffer("valueExpression_1"),
                "alertType_1", "alertMessage_1");
        PLRulePostActionImport child1= new PLRulePostActionImport("postActionType_2", "postActionOwnerType_2",2,
                "2", "child 1","subAction_2",2L,"variable_2", new StringBuffer("valueExpression_2"),
                "alertType_2", "alertMessage_2");
        PLRulePostActionImport child2= new PLRulePostActionImport("postActionType_3", "postActionOwnerType_3",3,
                "3", "child 2","subAction_3",3L,"variable_3", new StringBuffer("valueExpression_3"),
                "alertType_3", "alertMessage_3");
        PLRulePostActionImport child1_1= new PLRulePostActionImport("postActionType_4", "postActionOwnerType_4",4,
                "4", "chlid 1, 1","subAction_4",4L,"variable_4", new StringBuffer("valueExpression_4"),
                "alertType_4", "alertMessage_4");

        PLRulePostActionImport child1_2= new PLRulePostActionImport("postActionType_5", "postActionOwnerType_5",5,
                "5", "chlid 1, 2","subAction_5",5L,"variable_5", new StringBuffer("valueExpression_5"),
                "alertType_5", "alertMessage_5");

        PLRulePostActionImport child1_3= new PLRulePostActionImport("postActionType_6", "postActionOwnerType_6",6,
                "6", "chlid 1, 3","subAction_6",6L,"variable_6", new StringBuffer("valueExpression_6"),
                "alertType_6", "alertMessage_6");

        PLRulePostActionImport child2_1= new PLRulePostActionImport("postActionType_7", "postActionOwnerType_7",7,
                "7", "chlid 2, 1","subAction_7",7L,"variable_7", new StringBuffer("valueExpression_7"),
                "alertType_7", "alertMessage_7");
        PLRulePostActionImport child2_2= new PLRulePostActionImport("postActionType_8", "postActionOwnerType_7",7,
                "7", "chlid 2, 2","subAction_7",7L,"variable_7", new StringBuffer("valueExpression_7"),
                "alertType_7", "alertMessage_7");

        PostActionTreeImport<PLRulePostActionImport> root = new PostActionTreeImport<>(root0);
        PostActionTreeImport<PLRulePostActionImport> root_child1 = new PostActionTreeImport<>(child1);
        PostActionTreeImport<PLRulePostActionImport> root_child2 = new PostActionTreeImport<>(child2);

        root.addChild(root_child1);
        root.addChild(root_child2);

        root_child1.addChild(new PostActionTreeImport<>(child1_1));
        root_child1.addChild(new PostActionTreeImport<>(child1_2));
        root_child1.addChild(new PostActionTreeImport<>(child1_3));

        root_child2.addChild(new PostActionTreeImport<>(child2_1));
        root_child2.addChild(new PostActionTreeImport<>(child2_2));


        //preOrderTraversal(root);
*/

    }
}


