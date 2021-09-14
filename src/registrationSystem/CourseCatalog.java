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
            BufferedReader offeringFile;

            String catalogStr = "";
            String offeringStr = "";

            while ((catalogStr = catalogFile.readLine()) != null) {
                // 0 = course, 1..* course pre-requisite
                String[] courses = catalogStr.split(",");

                // create the object of the course
                Course theCourse = new Course(courses[0].split("-")[0].trim(), courses[0].split("-")[1].trim());

                // create the ArrayList of pre-requisite
                ArrayList<Course> preReqList = new ArrayList<>();

                // add the pre-requisite courses to the pre-requisite list
                for(int i=1; i<courses.length; i++) {
                    preReqList.add(new Course(courses[i].split("-")[0].trim(), courses[i].split("-")[1].trim()));
                }

                // create the ArrayList of course offerings
                ArrayList<Offering> offeringList = new ArrayList<>();

                // add the course offerings to the course offerings list
                offeringFile = new BufferedReader(new FileReader("course_offerings.txt"));

                while ((offeringStr = offeringFile.readLine()) != null) {
                    String[] offering = offeringStr.split(",");

                    // check if offering line read matches the current course line read
                    if(theCourse.equals(new Course(offering[0].split("-")[0].trim(), offering[0].split("-")[1].trim()))) {
                        Offering theOffering = new Offering(Integer.parseInt(offering[1].trim()), Integer.parseInt(offering[2].trim()));
                        theOffering.setTheCourse(theCourse);

                        offeringList.add(theOffering);
                    }

                    else {
                        continue;
                    }
                }

                offeringFile.close();

                // attach the pre-requisite list to the course
                theCourse.setPreReq(preReqList);

                // attach the offering list to the course
                theCourse.setOfferingList(offeringList);

                // add the course (along with its pre-requisite) to the catalog
                allCourses.add(theCourse);

                // System.out.printf("%s Section-%d Capacity-%d%n", theCourse, offeringList.get(0).getSectionNumber(), offeringList.get(0).getSectionCapacity());
            }

            catalogFile.close();
        }

        catch (FileNotFoundException e) {
            System.err.printf("%n[FAIL] File not found. Cannot proceed further. Bye!%n%n");
            e.printStackTrace();
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
            sb.append(String.format("%s%n", c.toStringCourseAndOffering()));
        }

        return sb.toString();
    }
}
