package registrationSystem;

import java.util.ArrayList;

/**
 * Represents a course in the Course Registration System
 */
public class Course {
    private String courseName;
    private String courseNumber;
    private ArrayList<Course> preReq;
    private ArrayList<Offering> offeringList;

    public Course(String courseName, String courseNumber) {
        this.setCourseName(courseName);
        this.setCourseNumber(courseNumber);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public ArrayList<Course> getPreReq() {
        return preReq;
    }

    public void setPreReq(ArrayList<Course> preReq) {
        this.preReq = preReq;
    }

    public ArrayList<Offering> getOfferingList() {
        return offeringList;
    }

    public void setOfferingList(ArrayList<Offering> offeringList) {
        this.offeringList = offeringList;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", this.getCourseName(), this.getCourseNumber());
    }

    @Override
    public boolean equals(Object obj) {
        Course compareCourse = (Course) obj;

        if (compareCourse.getCourseName().equals(this.getCourseName())
                && compareCourse.getCourseNumber().equals(this.getCourseNumber())) {
            return true;
        }

        return false;
    }

    /**
     * Returns a String containing course name-number, and all its pre-requisites
     *
     * @return String containing course name-number, and all its pre-requisites
     */
    public String toStringCourseAndPreReq() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s-%s", this.getCourseName(), this.getCourseNumber()));

        if (preReq.size() == 0) {
        }

        else {
            sb.append("    preReq: ");

            for (Course preReqCourse : preReq) {
                sb.append(preReqCourse + " ");
            }
        }

        return sb.toString();
    }

    /**
     * Returns a String containing course name-number, and all its offerings
     *
     * @return String containing course name-number, and all its offerings
     */
    public String toStringCourseAndOffering() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s-%s", this.getCourseName(), this.getCourseNumber()));

        if (offeringList.size() == 0) {
        }

        else {
            sb.append(String.format("    %s:%n", "Offerings"));

            for (Offering offering : offeringList) {
                sb.append(String.format("Section: %d, Capacity: %d%n", offering.getSectionNumber(),
                        offering.getSectionCapacity()));
            }
        }

        return sb.toString();
    }
}
