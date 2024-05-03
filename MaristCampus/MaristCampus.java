package assignment3;

public class MaristCampus{
    public static void main(String args[]){
        Course course1 = new Course("Computer science", "Bill Nye", 30);
        Course course2 = new Course("Science", "Nate Chudy", 12);
        Course course3 = new Course("History", "Jess Toscano", 25);
        Course course4 = new Course("Math", "Cole Casamento", 12);
        Course course5 = new Course("Physical Education", "Ty Morris", 4000);
        Course course6 = new Course("Art", "Brian Yetter", 45);
        Course course7 = new Course("Health", "James", 60);
        Course course8 = new Course("Health", "Lance", 30);

        Course[] hancock1 = {course1, course2};
        Course[] hancock2 = {course3, course4};

        Course[] mccan1 = {course5, course6};
        Course[] mccan2 = {course7, course8};

        Employee[] mccanWorker = {new Employee()};
        Employee[] hancockWorker = {new Employee()};
        
        Classroom classHancock1 = new Classroom(hancock1, "Blackboard", 300);
        Classroom classHancock2 = new Classroom(hancock2, "Whiteboard", 400);
        Office officeHancock = new Office(hancockWorker, true, true);
        Room[] roomsInHancock = {classHancock1, classHancock2, officeHancock};

        Classroom classMccan1 = new Classroom(mccan1, "Blackboard", 5000);
        Classroom classMccan2 = new Classroom(mccan2, "Chalkboard", 2000);
        Office officeMccan = new Office(mccanWorker, true, true);
        Room[] roomsInMccan = {classMccan1, classMccan2, officeMccan};

        Building McCann = new Building(roomsInMccan, 30, "West", "McCann");
        Building Hancock = new Building(roomsInHancock, 40, "East", "Hancock");
        Building[] buildingsAtMarist = {McCann, Hancock};
        Campus Marist = new Campus(buildingsAtMarist, "Poughkeepsie", "Sunny", "Marist");

        System.out.println();
        System.out.println("Printing the campus!:");
        System.out.println(Marist.toString());

        // The toString() on line 40 acts as a almost water fall effect through the classes
        // Campus, Building, Room, Classroom, Office, Course, and even Employee. This toString()
        // calls every toString() and ends up displaying everything on a campus at once. This 
        // also explains the look of the toString() methods in each class. It may seem weird
        // when just looking at the single class but it is meant not to be called directly but
        // to be called through the Campus class



        /* //*************UNCOMMENT HERE TO TEST COURSE METHODS*************

        // COURSE SETTERS AND GETTERS:
        System.out.println();
        System.out.println("Course1 Attributes:");
        System.out.println("Course1 course type: " + course1.getCourseType());
        System.out.println("Course1 professor name: " + course1.getProfessorName());
        System.out.println("Course1 number of students: " + course1.getNumStudents());

        course1.setCourseType("Health");
        course1.setNumStudents(400);
        course1.setProfessorName("Cole Casamento");

        System.out.println();
        System.out.println("New Course1 Attributes");
        System.out.println("Course1 course type: " + course1.getCourseType());
        System.out.println("Course1 professor name: " + course1.getProfessorName());
        System.out.println("Course1 number of students: " + course1.getNumStudents());



        //COURSE SPECIAL METHODS:
        System.out.println();
        System.out.println("Course1 special methods:");
        double[] GPAs = {4.0, 4.0, 3.1, 3.7, 3.9, 2.1, 3.9};
        System.out.printf("Average GPA: %.1f\n", course1.printAverageGPA(GPAs));
        System.out.println();
        String[] students = {"Nate", "Nick", "Maddie", "Tash", "Brendan", "Robin"};
        System.out.println("Student List:");
        for(int i = 0; i < students.length; i++){
            System.out.println("Student " + (i+1) + ": " + students[i]);
        }
        System.out.println();
        System.out.println("Swapped Student List: ");
        String[] swappedStudents = course1.swapStudents(students, "Nate", "Tash");
        for(int i = 0; i < swappedStudents.length; i++){
            System.out.println("Student " + (i+1) + ": " + swappedStudents[i]);
        }
        int[] ages = {22, 23, 26, 18, 53, 54};
        System.out.println();
        System.out.println("Average age: " + course1.calculateAverageAge(ages));

        */ //*************UNCOMMENT HERE TO TEST COURSE METHODS*************


    

        /* //*************UNCOMMENT HERE TO TEST CLASSROOM AND ROOM METHODS*************

        //CLASSROOM SETTERS AND GETTERS
        System.out.println();
        System.out.println("Courses in classHancock1:");
        for(int i = 0; i < classHancock1.getClassCourse().length; i++){
            System.out.println(classHancock1.getClassCourse()[i].toString());
        }
        System.out.println("Board type: " + classHancock1.getBoardType());
        System.out.println("Number of desks: " + classHancock1.getNumDesks());

        classHancock1.setClassCourse(hancock2);
        classHancock1.setBoardType("Chalkboard");
        classHancock1.setNumDesks(20);

        System.out.println();
        System.out.println("Courses in classHancock1:");
        for(int i = 0; i < classHancock1.getClassCourse().length; i++){
            System.out.println(classHancock1.getClassCourse()[i].toString());
        }
        System.out.println("Board type: " + classHancock1.getBoardType());
        System.out.println("Number of desks: " + classHancock1.getNumDesks());



        //CLASSROOM SPECIAL METHODS
        System.out.println("Writing on board: ");
        classHancock1.writeOnBoard("Hello Class");
        String[] students1 = {"Nate", "Nick", "Maddie", "Tash", "Brendan", "Robin"};
        System.out.println("Student List:");
        for(int i = 0; i < students1.length; i++){
            System.out.println("Student " + (i+1) + ": " + students1[i]);
        }
        String[] shuffledStudentList = classHancock1.shuffleStudents(students1);
        System.out.println("Shuffled Student Lift:");
        for(int i = 0; i < shuffledStudentList.length; i++){
            System.out.println("Student " + (i+1) + ": " + shuffledStudentList[i]);
        }
        System.out.println("Are there empty desks in course8: " + classHancock1.areDesksEmpty((course8)));



        //CLASSROOM INHERETED METHODS
        System.out.println();
        System.out.println("Inhereted Room Attributes:");
        System.out.println("Length: " + classHancock1.getLength() + " feet");
        System.out.println("Height: " + classHancock1.getHeight() + " feet");
        System.out.println("Width: " + classHancock1.getWidth() + " feet");
        System.out.println("Floor: " + classHancock1.getFloor());
        System.out.println("Number of entrances: " + classHancock1.getNumEntrances());

        classHancock1.setFloor(5);
        classHancock1.setHeight(15);
        classHancock1.setLength(25);
        classHancock1.setNumEntrances(3);

        System.out.println();
        System.out.println("Updated Inhereted Room Attributes:");
        System.out.println("Length: " + classHancock1.getLength() + " feet");
        System.out.println("Height: " + classHancock1.getHeight() + " feet");
        System.out.println("Width: " + classHancock1.getWidth() + " feet");
        System.out.println("Floor: " + classHancock1.getFloor());
        System.out.println("Number of entrances: " + classHancock1.getNumEntrances());

        //CLASSROOM INHERETED SPECIAL METHODS
        System.out.println();
        System.out.println("Special Inhereted Methods");
        System.out.println("Area of the floor: " + classHancock1.calculateFloorArea() + " square feet");
        System.out.println("Height in the air: " + classHancock1.calculateHeightInAir() + " feet");
        System.out.println("Is this class fire safe: " + classHancock1.isFireSafe());

        */ //*************UNCOMMENT HERE TO TEST CLASSROOM AND ROOM METHODS*************



        /* //*************UNCOMMENT HERE TO TEST OFFICE AND ROOM METHODS*************

        //OFFICE SETTERS AND GETTERS
        Employee[] listOfEmployees = {new Employee(), new Employee(), new Employee()};
        Office testOffice = new Office(listOfEmployees, true, true);
        System.out.println();
        System.out.println("testOffice Attributes:");
        System.out.println("Do you have a corner office?: " + testOffice.getCornerOffice());
        System.out.println("Do you have a view?: " + testOffice.getView());
        for(Employee e: testOffice.getEmployees()){
            System.out.println("Employee: " + e.toString());
        }

        testOffice.setCornerOffice(false);
        testOffice.setView(false);
        Employee[] newListOfEmployees = {new Employee(100, 350, true), new Employee(65, 120, false), new Employee()};
        testOffice.setEmployees(newListOfEmployees);

        System.out.println();
        System.out.println("Updated testOffice Attributes:");
        System.out.println("Do you have a corner office?: " + testOffice.getCornerOffice());
        System.out.println("Do you have a view?: " + testOffice.getView());
        for(Employee e: testOffice.getEmployees()){
            System.out.println("Employee: " + e.toString());
        }



        //OFFICE SPECIAL METHODS
        System.out.println();
        testOffice.addOfficeThing("plant");
        testOffice.toString();
        testOffice.changeOffice();
        testOffice.toString();
        String[] todo = {"Clean", "Submit files", "Send emails", "Take lunch"};
        System.out.println("Ordered ToDo list:");
        for(String s: todo){
            System.out.println(s);
        }
        String[] shuffle = testOffice.shuffleToDoList(todo);
        System.out.println();
        System.out.println("Shuffled ToDo list:");
        for(String s: shuffle){
            System.out.println(s);
        }



        //OFFICE INHERETED METHODS
        System.out.println();
        System.out.println("Inhereted Room Attributes:");
        System.out.println("Length: " + testOffice.getLength() + " feet");
        System.out.println("Height: " + testOffice.getHeight() + " feet");
        System.out.println("Width: " + testOffice.getWidth() + " feet");
        System.out.println("Floor: " + testOffice.getFloor());
        System.out.println("Number of entrances: " + classHancock1.getNumEntrances());

        testOffice.setFloor(5);
        testOffice.setHeight(15);
        testOffice.setLength(25);
        testOffice.setNumEntrances(3);

        System.out.println();
        System.out.println("Updated Inhereted Room Attributes:");
        System.out.println("Length: " + testOffice.getLength() + " feet");
        System.out.println("Height: " + testOffice.getHeight() + " feet");
        System.out.println("Width: " + testOffice.getWidth() + " feet");
        System.out.println("Floor: " + testOffice.getFloor());
        System.out.println("Number of entrances: " + classHancock1.getNumEntrances());

        */ //*************UNCOMMENT HERE TO TEST OFFICE AND ROOM METHODS*************



        /* //*************UNCOMMENT HERE TO TEST BUILDING METHODS*************

        //BUILDING SETTERS AND GETTTERS
        System.out.println();
        System.out.println("McCann Attributes");
        System.out.println("Name of building: " + McCann.getName());
        System.out.println("Height of McCann: " + McCann.getHeight());
        System.out.println("McCanns location on campus: " + McCann.getLocationOnCampus());
        for(Room r: McCann.getRooms()){
            System.out.println("Room: " + r.toString());
        }

        McCann.setHeight(500);
        McCann.setLocationOnCampus("East");
        McCann.setName("McCann"); // keeping the name the same
        Room[] newRooms = {new Classroom(), new Office()};
        McCann.setRooms(newRooms);

        System.out.println();
        System.out.println("Updated McCann Attributes");
        System.out.println("Name of building: " + McCann.getName());
        System.out.println("Height of McCann: " + McCann.getHeight());
        System.out.println("McCanns location on campus: " + McCann.getLocationOnCampus());
        for(Room r: McCann.getRooms()){
            System.out.println("Room: " + r.toString());
        }

        

        //BUILDING SPECIAL METHODS
        System.out.println();
        System.out.println("Height of McCann: " + McCann.getHeight());
        System.out.println(McCann.randomHeight());
        System.out.println("Height of McCann: " + McCann.getHeight());
        System.out.println(McCann.cleanBuilding());
        System.out.println(McCann.lockItUp());

        */ //*************UNCOMMENT HERE TO TEST BUILDING METHODS*************



        /* //*************UNCOMMENT HERE TO TEST CAMPUS METHODS*************

        //CAMPUS SETTERS AND GETTERS
        System.out.println();
        System.out.println("Marist Attributes:");
        System.out.println("Campus Name: " + Marist.getName());
        System.out.println("Campus Location: " + Marist.getLocation());
        System.out.println("Weather on campus: " + Marist.getWeather());

        Building McCannTest = new Building(roomsInMccan, 300, "West Side", "Donnely");
        Building HancockTest = new Building(roomsInHancock, 400, "East Side", "Upper Campus");
        Building[] bList = {McCannTest, HancockTest};

        Marist.setLocation("New York");
        Marist.setName("Its Still Marist");
        Marist.setWeather("Rainy");
        Marist.setBuildings(bList);

        System.out.println();
        System.out.println("Updated Marist Attributes:");
        System.out.println("Campus Name: " + Marist.getName());
        System.out.println("Campus Location: " + Marist.getLocation());
        System.out.println("Weather on campus: " + Marist.getWeather());



        //CAMPUS SPECIAL METHODS
        System.out.println();
        Marist.playGod();
        String[] teams = {"baseball", "basketball", "swimming", "volleyball"};
        Marist.hostSportingEvent(teams);
        Marist.randomCapmusLocation();

        */ //*************UNCOMMENT HERE TO TEST CAMPUS METHODS*************
    }
}