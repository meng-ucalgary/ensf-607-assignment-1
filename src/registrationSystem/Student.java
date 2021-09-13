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

    public void registerForCourse(CourseCatalog cat, String courseName, String courseNumber, int sectionNumber) {
        Course myCourse = cat.searchCatalog(courseName, courseNumber);

        if (myCourse == null) {
            return;
        }

        else {
            // TODO : fix this later
            Offering theOffering = myCourse.getOfferingList().get(sectionNumber - 1);

            Registration reg = new Registration(this, theOffering);
        }
    }

    public void addRegistration(Registration reg) {
        this.courseList.add(reg);
    }
}
