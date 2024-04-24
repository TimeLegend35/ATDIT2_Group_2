package de.badwalden.schule.model;

import de.badwalden.schule.dao.ParentDAO;

import java.util.List;

public class Parent extends User {
    private List<Student> children;

    public Parent(int id, String firstName, String lastName, String cityOfResidence) {
        super();

        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setResidence(cityOfResidence);
    }

    // leave a empty parent constructor for mocking / REMOVE LATER!
    public Parent() {
        super();
    }

    public List<Student> getChildren() {
        return children;
    }

    public void setChildren(List<Student> children) {
        this.children = children;
    }

}
