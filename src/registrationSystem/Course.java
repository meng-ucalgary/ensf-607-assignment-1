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
        preReq = new ArrayList<Course>();
        offeringList = new ArrayList<Offering>();
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

    public void addOffering(Offering theOffering) {
        offeringList.add(theOffering);
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

    public Offering searchOffering(int sectionNumber) {
        for (Offering o : this.getOfferingList()) {
            if (o.getSectionNumber() == sectionNumber) {
                return o;
            }
        }

        return null;
    }

    /**
     * Returns a String containing course name-number, its pre-requisites, and its
     * offerings
     *
     * @return String containing course name-number, its pre-requisites, and its
     *         offerings
     */
    public String toStringDetailed() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.toString());

        if (preReq.size() == 0) {
            sb.append("  PreRequisite(s): None");
        }

        else {
            sb.append("  PreRequisite(s): ");

            for (Course preReqCourse : preReq) {
                sb.append(preReqCourse + " ");
            }
        }

        if (offeringList.size() == 0) {
            sb.append(String.format("%n          Not offered%n"));
        }

        else {
            sb.append(String.format("%n          Offerings:%n"));

            for (Offering offering : offeringList) {
                sb.append(String.format("            Section: %d, Capacity: %d/%d%n", offering.getSectionNumber(),
                        offering.occupancy(), offering.getSectionCapacity()));
            }
        }

        return sb.toString();
    }
}
