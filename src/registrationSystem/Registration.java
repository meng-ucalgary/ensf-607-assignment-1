package registrationSystem;

public class Registration {
    private Student theStudent;
    private Offering theOffering;
    private char grade;

    public Registration(Student theStudent, Offering theOffering) {
        this.setTheStudent(theStudent);
        this.setTheOffering(theOffering);
        this.addRegistration();
    }

    public Student getTheStudent() {
        return theStudent;
    }

    public void setTheStudent(Student theStudent) {
        this.theStudent = theStudent;
    }

    public Offering getTheOffering() {
        return theOffering;
    }

    public void setTheOffering(Offering theOffering) {
        this.theOffering = theOffering;
    }

    public char getGrade() {
        return grade;
    }

    // public void setGrade(char grade) {
    //     this.grade = grade;
    // }

    private void addRegistration() {
        theStudent.addRegistration(this);
        theOffering.addRegistration(this);
    }
}
