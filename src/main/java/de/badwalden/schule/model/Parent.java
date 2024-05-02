package de.badwalden.schule.model;

import de.badwalden.schule.dao.ParentDAO;

import java.util.List;


/**
 * Represents a parent, extending the User class with a list of children.
 */
public class Parent extends User {
    private List<Student> children;

    /**
     * Constructs a new Parent with the specified personal details.
     */
    public Parent(int id, String firstName, String lastName, String cityOfResidence) {
        super();

        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setResidence(cityOfResidence);
    }

    public List<Student> getChildren() {
        return children;
    }

    public void setChildren(List<Student> children) {
        this.children = children;
    }

}
