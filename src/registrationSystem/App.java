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
     * Helper function to read from stdin after displaying the prompt
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
     * Console based menu
     */
    public void appMenu() {
        // priming input ch
        int choice = 9;

        while (choice != 6) {
            App.clearConsole();

            System.out.printf("%nCourse Registration System");
            System.out.printf("%n%n[1] Search catalogue courses");
            System.out.printf("%n%n[2] Add course to student courses");
            System.out.printf("%n%n[3] Remove course from student courses");
            System.out.printf("%n%n[4] View All courses in catalogue");
            System.out.printf("%n%n[5] View all courses taken by student");
            System.out.printf("%n%n[6] Exit");

            try {
                choice = Integer.parseInt(this.readLine("%n%n%n[QUES] Please enter your choice: "));

                switch (choice) {

                    case 1: {
                        System.out.printf("Searching for course in the catalog");
                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 2: {
                        System.out.printf("Adding course");
                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 3: {
                        System.out.printf("Remove course");
                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 4: {
                        App.clearConsole();
                        System.out.printf("%nYou are viewing all the courses in the catalog%n%n");
                        cat.listCourses();
                        this.readLine("%n%n%nPress enter to return to the menu ");
                    }
                        break;

                    case 5: {
                        System.out.printf("View all courses by the Student");
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
        app.appMenu();

        // Student s1 = new Student("Amy", 30144835);
        // Student s2 = new Student("Jackson", 30144837);
    }
}
