package registrationSystem;

import org.fusesource.jansi.AnsiConsole;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Provides front end for the Course Registration System
 */
public class App {
    BufferedReader br;
    CourseCatalog cat;
    Student stu;

    private App() {
        AnsiConsole.systemInstall();
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.cat = new CourseCatalog();
    }

    /**
     * Clears the stdout console
     */
    public static void clearConsole() {
        System.out.print("\033\143");
    }

    /**
     * Helper method to read from stdin after displaying the prompt
     *
     * @param prompt the prompt to print to stdout
     * @return String input by user
     */
    public String readLine(String fmt, Object... args) {
        System.out.printf(fmt, args);

        try {
            return this.br.readLine();
        }

        catch (IOException e) {
            return "";
        }
    }

    /**
     * Helper method to read the student details from the user
     */
    public void getStudent() {
        System.out.printf("%n[INFO] PLEASE ENTER YOUR DETAILS%n%n");
        String studentName = this.readLine("%n[QUES] Student name: ");
        int studentId;

        while (true) {
            try {
                studentId = Integer.parseInt(this.readLine("%n[QUES] Student ID number: "));
                break;
            }

            catch (NumberFormatException e) {
                System.err.printf("%n[FAIL] Please enter a valid numeric ID%n");
            }
        }

        this.stu = new Student(studentName, studentId);
    }

    /**
     * Console based menu
     */
    public void appMenu() {
        // priming input ch
        int choice = 9;

        while (choice != 6) {
            App.clearConsole();

            System.out.printf("%nCourse Registration System");
            System.out.printf("%n%n[1] Search catalog courses");
            System.out.printf("%n%n[2] Add course to student courses");
            System.out.printf("%n%n[3] Remove course from student courses");
            System.out.printf("%n%n[4] View all courses in catalog");
            System.out.printf("%n%n[5] View all courses taken by student");
            System.out.printf("%n%n[6] Exit");

            try {
                choice = Integer.parseInt(this.readLine("%n%n%n[QUES] Please enter your choice: "));

                switch (choice) {

                    case 1: {
                        App.clearConsole();
                        System.out.printf("%n[INFO] SEARCH THE COURSE CATALOG%n%n");

                        String courseName = this
                                .readLine("%n[QUES] Enter the course name that you want to search: ")
                                .toUpperCase();

                        String output = cat.searchCatalog(courseName);

                        if(output == null) {
                            System.out.printf("%n[DONE] Sorry no courses found");
                        }

                        else {
                            System.out.printf("%n[DONE] Search results -%n%n%s", output);
                        }

                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 2: {
                        App.clearConsole();
                        System.out.printf("%n[INFO] REGISTER FOR A COURSE%n%n");

                        String courseName = this
                                .readLine("%n[QUES] Enter the course name for which you want to register: ")
                                .toUpperCase();
                        String courseNumber = this
                                .readLine("%n[QUES] Enter the course number for which you want to register: ");
                        int sectionNumber;

                        while (true) {
                            try {
                                sectionNumber = Integer.parseInt(
                                        this.readLine("%n[QUES] Enter the section number of the course offering: "));
                                break;
                            }

                            catch (NumberFormatException e) {
                                System.err.printf("%n%n[FAIL] Please enter a valid section number%n");
                            }
                        }

                        if (this.stu.registerForCourse(this.cat, courseName, courseNumber, sectionNumber) == false) {
                            System.out.printf("%n%n[FAIL] Sorry could not register for the course");
                        }

                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 3: {
                        App.clearConsole();
                        System.out.printf("%n[INFO] DE-REGISTER FROM A COURSE%n%n");

                        String courseName = this
                                .readLine("%n[QUES] Enter the course name from which you want to de-register: ")
                                .toUpperCase();
                        String courseNumber = this
                                .readLine("%n[QUES] Enter the course number from which you want to de-register: ");

                        if (this.stu.deRegisterFromCourse(this.cat, courseName, courseNumber) == false) {
                            System.out.printf("%n%n[FAIL] Sorry could not de-register from the course");
                        }

                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 4: {
                        App.clearConsole();
                        System.out.printf("%nALL COURSES IN THE CATALOG%n%n");
                        cat.listCourses();
                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 5: {
                        App.clearConsole();
                        System.out.printf("%n[INFO] ALL REGISTERED COURSES BY STUDENT%n%n");

                        String output = stu.printRegisteredCourses();

                        if(output == null) {
                            System.out.printf("%n[DONE] You are not registered in any course");
                        }

                        else {
                            System.out.printf("%n%s", output);
                        }

                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 6: {
                        choice = 6;
                    }
                        break;

                    default: {
                        throw new NumberFormatException();
                    }
                }
            }

            catch (NumberFormatException e) {
                System.out.printf("%n%n[FAIL] Please enter a valid choice");
                this.readLine("%n%n%nPress enter to return to the menu ");
            }

            catch (java.util.NoSuchElementException e) {
                System.out.printf("%n%n[FAIL] Bye.");
                Runtime.getRuntime().exit(0);
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.getStudent();
        app.appMenu();
    }
}
