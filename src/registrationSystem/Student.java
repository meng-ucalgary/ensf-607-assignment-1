package registrationSystem;

import java.util.ArrayList;

public class Student {
    private String studentName;
    private int studentId;
    private ArrayList<Registration> courseList;

    public Student(String studentName, int studentId) {
        this.setStudentName(studentName);
        this.setStudentId(studentId);
        this.courseList = new ArrayList<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean registerForCourse(CourseCatalog cat, String courseName, String courseNumber, int sectionNumber) {
        boolean flag = false;
        Course myCourse = cat.searchCatalog(courseName, courseNumber);

        // max 6 complete
        if (this.courseList.size() == 6) {
            System.err.printf("%n[FAIL] You are already registered for 6 courses. You cannot register for more.");
            return false;
        }

        // invalid course
        else if (myCourse == null) {
            System.err.printf(
                    "%n[FAIL] The selected course does not exist. Please select a valid course from the catalog.");
            return false;
        }

        // course without any offering
        else if (myCourse.getOfferingList().size() == 0) {
            System.err.printf("%n[FAIL] The selected course does not has any offerings. Please select another course.");
            return false;
        }

        // check offering and pre-requisites
        else {
            Offering theOffering = myCourse.searchOffering(sectionNumber);

            // course with invalid course offering section number
            if (theOffering == null) {
                System.err.printf("%n[FAIL] Invalid course offering selected. Please select the correct offering.");
                return false;
            }

            else {
                // check if course is already registered
                for (Registration registeredCourses : this.courseList) {
                    if (registeredCourses.getTheOffering().getTheCourse().equals(myCourse)) {
                        System.out.printf("%n[FAIL] You are already registered for this course.");
                        return false;
                    }
                }

                // no pre-requisites
                if (myCourse.getPreReq().size() == 0) {
                    flag = true;
                }

                else {
                    // temporarily set the flag as true. It will be flipped if pre-requisites are
                    // not met
                    flag = true;

                    // check if pre-requisites are met
                    for (Course preReq : myCourse.getPreReq()) {
                        boolean preRequisiteSatisfied = false;

                        for (Registration registeredCourses : this.courseList) {
                            if (preReq.equals(registeredCourses.getTheOffering().getTheCourse())) {
                                preRequisiteSatisfied = true;
                            }
                        }

                        if (preRequisiteSatisfied == false) {
                            System.out.printf(
                                    "%n[FAIL] Pre-requisites not met. Please register for pre-requisite courses before selecting this course.");
                            return false;
                        }
                    }
                }

                if (flag == true) {
                    /* Registration reg = */new Registration(this, theOffering);
                    System.out.printf("%n[DONE] Successfully registered for the course %s in section %d.", myCourse,
                            theOffering.getSectionNumber());
                }
            }
        }

        return flag;
    }

    public boolean deRegisterFromCourse(CourseCatalog cat, String courseName, String courseNumber) {
        boolean flag = false;
        Registration toBeDeRegistered = null;
        Course myCourse = cat.searchCatalog(courseName, courseNumber);

        // invalid course
        if (myCourse == null) {
            System.err.printf("%n[FAIL] The selected course does not exist.");
            return false;
        }

        // check if course is actually registered
        boolean registrationCheck = false;

        for (Registration registeredCourses : this.courseList) {
            if (registeredCourses.getTheOffering().getTheCourse().equals(myCourse)) {
                toBeDeRegistered = registeredCourses;
                registrationCheck = true;
                break;
            }
        }

        if (registrationCheck == false) {
            System.err.printf("%n[FAIL] You are currently not registered for this course.");
            return false;
        }

        // check if the course has pre-requisite dependency on other registered courses
        for (Registration registeredCourse : this.courseList) {
            for (Course c : registeredCourse.getTheOffering().getTheCourse().getPreReq()) {
                if (myCourse.equals(c)) {
                    System.err.printf(
                            "%n[FAIL] This course is listed as a pre-requisite for some of the registered courses.");
                    System.err.printf("%n[FAIL] Please de-register from them before de-registering for this course.");

                    return false;
                }
            }
        }

        // now de-register from the course
        this.removeRegistration(toBeDeRegistered);
        toBeDeRegistered.getTheOffering().removeRegistration(toBeDeRegistered);
        System.out.printf("%n[DONE] Successfully de-registered from the course %s.", myCourse);
        flag = true;

        return flag;
    }

    public void addRegistration(Registration reg) {
        this.courseList.add(reg);
    }

    public void removeRegistration(Registration reg) {
        this.courseList.remove(reg);
    }

    public String printRegisteredCourses() {
        StringBuilder sb = new StringBuilder();

        for (Registration r : this.courseList) {
            sb.append(String.format("Course: %s, Section: %s%n", r.getTheOffering().getTheCourse(),
                    r.getTheOffering().getSectionNumber()));
        }

        return sb.toString();
    }
}
