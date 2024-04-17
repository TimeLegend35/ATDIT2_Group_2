package de.badwalden.schule.model.outOfScope;

import java.util.Calendar;
import java.util.Map;

public class Subject {
    private int id;
    private String name;
    private boolean isMainSubject;
    private Calendar yearOfIntroduction;
    private Calendar lastYear;
    private int youngestGrade;
    private int oldestGrade;
    private Map<Calendar, Integer> grades;

    public Subject() {

    }
}
