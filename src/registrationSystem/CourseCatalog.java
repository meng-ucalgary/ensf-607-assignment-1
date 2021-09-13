package registrationSystem;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CourseCatalog {
    private ArrayList<Course> courseList;

    public CourseCatalog() {
        this.courseList = CourseCatalog.loadFromFile();
    }

    /**
     * Load the list of courses and their pre-requisites (if any) from the file on
     * the disk to the memory
     *
     * @return ArrayList of type {@link Course}
     */
    private static ArrayList<Course> loadFromFile() {
        ArrayList<Course> allCourses = new ArrayList<>();

        try {
            BufferedReader catalogFile = new BufferedReader(new FileReader("course_catalog.txt"));

            String str = "";

            while ((str = catalogFile.readLine()) != null) {
                // 0 = course, 1..* course pre-requisite
                String[] courses = str.split(",");

                // create the object of the course
                Course theCourse = new Course(courses[0].split("-")[0].trim(), courses[0].split("-")[1].trim());

                // create the ArrayList of pre-requisite
                ArrayList<Course> preReqList = new ArrayList<>();

                // add the pre-requisite courses to the pre-requisite list
                for(int i=1; i<courses.length; i++) {
                    preReqList.add(new Course(courses[i].split("-")[0].trim(), courses[i].split("-")[1].trim()));
                }

                // attach the pre-requisite list to the course
                theCourse.setPreReq(preReqList);

                // add the course (along with its pre-requisite) to the catalog
                allCourses.add(theCourse);
            }

            catalogFile.close();
        }

        catch (FileNotFoundException e) {
            System.err.printf("%n[FAIL] Course Catalog not found. Cannot proceed further. Bye!%n%n");
            Runtime.getRuntime().exit(1);
        }

        catch (IOException e) {
            e.printStackTrace();
            Runtime.getRuntime().exit(2);
        }

        return allCourses;
    }

    public void listCourses() {
        System.out.println(this);
    }

    public Course searchCatalog(String courseName, String courseNumber) {
        for (Course c : courseList) {
            if (c.equals(new Course(courseName, courseNumber))) {
                return c;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Course c : courseList) {
            sb.append(String.format("%s%n", c.toStringCourseAndPreReq()));
        }

        return sb.toString();
    }
}
