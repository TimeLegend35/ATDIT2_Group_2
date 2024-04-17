package de.badwalden.schule.model;

import java.util.List;

public class Parent extends User {
    private List<Student> children;

    public Parent(String username) {

    }

    // leave a empty parent constructor for mocking / REMOVE LATER!
    public Parent() {

    }

    public List<Student> getChildren() {
        return children;
    }

    public void setChildren(List<Student> children) {
        this.children = children;
    }

}
