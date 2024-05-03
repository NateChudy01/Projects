package assignment3; // puts Course.java in the same package as the other classes

// public class Course{}
// This class's purpose is to be able to create a course with different aspects that 
// can then be given to a classroom object. You can make an instance of this class and 
// the constructor of this class sets three values. STRING COURSE_TYPE is the type of 
// course that is being taught, STRING PROFESSOR_NAME is the name of the professor 
// teaching the course, and INT NUM_STUDENTS is the number of students in the course
public class Course {
    private String courseType; // type of course being taught
    private String professorName; // name of professor teaching course
    private int numStudents; // number of students in the course

    public Course() { // default constructor makes a "Computer Science" course
        // with the professor as "Michael E. Gildein II" and 20 students in the course
        courseType = "Computer Science";
        professorName = "Michael E. Gildein II";
        numStudents = 20;
    }

    public Course(String courseType, String professorName, int numStudents) { // takes
        // input from the user when creating the course object
        this.courseType = courseType;
        this.professorName = professorName;
        this.numStudents = numStudents;
    }

    public String getCourseType() { // courseType getter
        return courseType;
    }

    public String getProfessorName() { // professorName getter
        return professorName;
    }

    public int getNumStudents() { // numStudents getter
        return numStudents;
    }

    public void setCourseType(String courseType) { // courseType setter
        this.courseType = courseType;
    }

    public void setProfessorName(String professorName) { // professorName setter
        this.professorName = professorName;
    }

    public void setNumStudents(int numStudents) { // numStudents setter
        this.numStudents = numStudents;
    }

    // public double printAverageGPA(double[] GPA)
    // The method below's purpose is to calculate the average GPA of the
    // students in the course. This method has one parameter DOUBLE[] GPA
    // which is a list of double of the GPAs of the students in the course.
    // This method returns a double which is the average GPA of the students
    public double printAverageGPA(double[] GPA) {
        double total = 0.0; // keeps a running total to then calculate the average
        for (double grade : GPA) { // loops through each GPA
            total = total + grade; // adds each GPA to total
        }
        return (total / GPA.length); // returns the average of the GPAs
    }

    // public String[] swapStudents(String[] seetingChart, String student1, String
    // student2)
    // This method below's purpose is if given a seeting chart it swaps two students
    // in the seeting chart that are given as parameters. This method has three
    // parameters, STRING[] SEETINGCHART is a list of students names, STRING
    // STUDENT1 is the name of the first student that is wanted to be swapped,
    // STRING STUDENT2 is the second name of the student that is wasnted to swap.
    // This method returns a String[] list that is the same as the input parameter
    // list but with the two students names swapped
    public String[] swapStudents(String[] seetingChart, String student1, String student2) {
        int studentIndex1 = -1; // initalize index of first student
        int studentIndex2 = -1; // initalize index of second student
        String studentTempSwap; // used to store one student for swapping them
        for (int i = 0; i < seetingChart.length; i++) { // loop through all students
            if (seetingChart[i].equalsIgnoreCase(student1)) { // find the index of the first student and then store the
                                                              // index it is at
                studentIndex1 = i;
            }
            if (seetingChart[i].equalsIgnoreCase(student2)) { // find the index of the second student and then store the
                                                              // index it is at
                studentIndex2 = i;
            }
        }
        if (studentIndex1 == -1 || studentIndex2 == -1) { // if either of the index of the students is -1 it means that
            // one of them was not in the String[] list and therefore it is necessary to
            // print out that one of the
            // students was not found and return the inital student list
            System.out.println("Could not find one of the students in the class list");
            return seetingChart;
        }
        studentTempSwap = seetingChart[studentIndex1]; // these lines swap the students and return the updated list
        seetingChart[studentIndex1] = seetingChart[studentIndex2];
        seetingChart[studentIndex2] = studentTempSwap;
        return seetingChart;
    }

    // public int calculateAverageAge(int[] ages)
    // This methods below's purpose is to calculate the average age of the students
    // in the course. This method takes in one parameter INT[] AGES which is a list
    // of ints that are the ages of the students in the class. This method returns
    // an int
    public int calculateAverageAge(int[] ages) {
        int total = 0; // tracking the total age
        for (int singleAge : ages) { // loops through each age to then add to the total
            total = total + singleAge;
        }
        return (total / ages.length); // returns the average age truncated
    }

    // public String toString()
    // this method is the toString of the course class. Whenever the toString is
    // called it returns the string in the method. No parameters are taken in and
    // the return type is a String
    // [See MaristCampus.java for toString() explaination lines 42 - 47]
    public String toString() {
        return "Course: " + courseType + ", Professor: " + professorName + ", Number of Students: " + numStudents;
    }
}