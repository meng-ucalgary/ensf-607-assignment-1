# Course Registration System

## About

A course registration system based on the specifications laid in [Assignment_1.pdf](Assignment_1.pdf)


## Dependencies

+ JDK 1.7 or above configured


## Program design and flow 

+ As soon as the program is launched, it reads the [resource files](#details-of-resource-files) to create course catalog, and registers all the dummy students

+ You as a student would enter your details to enter the system

+ Before registering a course (program menu option number 2), the pre-requisites of the course are checked. You must be registered for the pre-requisite courses to register for any course.

+ After registration, a warning is showed only if there are less than 8 students registered in the selected offering

+ All the course details, offering details, and occupancy details can be printed using program menu option number 4.

+ Refer to the [screenshots](#screenshots) to get an idea of the working of the program


## Details of resource files

+ Data required for Course Catalog is stored in [lib/course_catalog.txt](lib/course_catalog.txt)

   + Each line in the file follows the below format
     ```
     <course name>, <comma separated list of pre-requisite courses>
     ```

+ Data required for Course Offering is stored in [lib/course_offerings.txt](lib/course_offerings.txt)

   + Each line in the file follows the below format
     ```
     <course name>, <offering section number>, <offering maximum capacity>
     ```

+ Dummy students and their registration informations is stored in [lib/dummy_students.txt](lib/dummy_students.txt)

   + Each line in the file follows the below format
   ```
   <student name>, <student ID number>, <comma separated list of registered offering in the format <courseName-courseNumber-offeringSection>>
   ```


## Running the program

1. Extract the downloaded the zip archive or clone the project on your local machine

2. If using Windows OS

   1. Navigate to the directory where the project is extracted/cloned

   2. Double click on the file `Run.cmd`

   3. Follow the onscreen instructions

3. If not using Windows OS
   
   1. Using the terminal, go to the directory where the project is extracted/cloned

   2. Run the below commands
      ```shell
      chmod +x Run.sh
      ./Run.sh
      ```

   3. Then follow the onscreen instructions


## Screenshots

Student entering his/her details

![0.1-Student-Enter.png](screenshots/0.1-Student-Enter.png)

Program menu options

![0.2-Menu-Options.png](screenshots/0.2-Menu-Options.png)

Menu option 1: no search results

![1.1-No-Search-Results.png](screenshots/1.1-No-Search-Results.png)

Menu option 1: search produced some results

![1.2-Search-Results.png](screenshots/1.2-Search-Results.png)

Menu option 2: couldn't register as all registrations are full in this offering

![2.1-No-Space-For-Registration.png](screenshots/2.1-No-Space-For-Registration.png)

Menu option 2: couldn't register as pre-requisites are not met

![2.2-Pre-Requisites-Not-Met.png](screenshots/2.2-Pre-Requisites-Not-Met.png)

Menu option 2: successfully registered

![2.3-Successful-Registration.png](screenshots/2.3-Successful-Registration.png)

Menu option 2: successfully registered but a warning is issued

![2.4-Successful-Registration-With-Warning.png](screenshots/2.4-Successful-Registration-With-Warning.png)

Menu option 2: cannot register for more than 6 courses

![2.5-Maxed-Out-Registration.png](screenshots/2.5-Maxed-Out-Registration.png)

Menu option 3: cannot de-register as registered courses has depedency

![3.1-Unsuccessful-De-Registration.png](screenshots/3.1-Unsuccessful-De-Registration.png)

Menu option 3: successfully de-registered

![3.2-Successful-De-Registration.png](screenshots/3.2-Successful-De-Registration.png)

Menu option 4: view the entire course catalog

![4-View-Catalog.png](screenshots/4-View-Catalog.png)

Menu option 5: show all the registered courses for the current student

![5-All-Registered-Courses.png](screenshots/5-All-Registered-Courses.png)

Menu option 6: exit the program

![6-Exit.png](screenshots/6-Exit.png)
