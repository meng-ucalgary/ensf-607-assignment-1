package registrationSystem;

import java.util.ArrayList;

public class Offering {
    private int sectionNumber;
    private int sectionCapacity;
    private Course theCourse;
    private ArrayList<Registration> studentList;

    public Offering(int sectionNumber, int sectionCapacity) {
        this.setSectionNumber(sectionNumber);
        this.setSectionCapacity(sectionCapacity);
        this.studentList = new ArrayList<>();
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public int getSectionCapacity() {
        return sectionCapacity;
    }

    public void setSectionCapacity(int sectionCapacity) {
        this.sectionCapacity = sectionCapacity;
    }

    public Course getTheCourse() {
        return theCourse;
    }

    public void setTheCourse(Course theCourse) {
        this.theCourse = theCourse;
    }

    public void addRegistration(Registration reg) {
        this.studentList.add(reg);
    }

    public void removeRegistration(Registration reg) {
        this.studentList.remove(reg);
    }
}
