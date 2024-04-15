package de.badwalden.schule.model;

import java.util.List;

public class Parent extends User {
    private List<Student> children;

    public Parent() {

    }

    public List<Student> getChildren() {
        return children;
    }

    public void setChildren(List<Student> children) {
        this.children = children;
    }

}
