package testHarness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import authenticatedUsers.LoggedInAuthenticatedUser;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import offerings.OfferingFactory;
import registrar.ModelRegister;
import systemUserModelFactories.AdminModelFactory;
import systemUsers.AdminModel;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;
import processServer.*;
import systemUsers.SystemUserModel;

public class TestStudentModelFactory_1 {

//	public static void main(String[] args) throws IOException{
//		// TODO Auto-generated method stub
//		SystemUserModelFactory factory = new StudentModelFactory();
//		BufferedReader br = new BufferedReader(new FileReader(new File("note_1.txt")));
//		factory.createSystemUserModel(br, null);
//	}

	public static void main(String[] args) throws IOException{
//		Create an instance of an OfferingFactory
		OfferingFactory factory = new OfferingFactory();
		BufferedReader br = new BufferedReader(new FileReader(new File("note_1.txt")));
//		Use the factory to populate as many instances of courses as many files we've got.
		CourseOffering	courseOffering = factory.createCourseOffering(br);
		br.close();
//		Loading 1 file at a time
		br = new BufferedReader(new FileReader(new File("note_2.txt")));
//		here we have only two files
		courseOffering = factory.createCourseOffering(br);

		// read from admin file and create admin users
        br = new BufferedReader(new FileReader(new File("admins_1.txt")));
        String line = br.readLine();
        AdminModelFactory theFactory = new AdminModelFactory();
        for (int i=0;i<Integer.parseInt(line.split(" ")[2]);i++) {
            theFactory.createSystemUserModel(br, courseOffering);
        }

		br.close();
//		code to perform sanity checking of all our code
//		by printing all of the data that we've loaded
        /*
		for(CourseOffering course : ModelRegister.getInstance().getAllCourses()){
			System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " + 
			course.getSemester());
			System.out.println("Students allowed to enroll\n");
			for(StudentModel student : course.getStudentsAllowedToEnroll()){
				System.out.println("Student name : " + student.getName() + "\nStudent surname : " + student.getSurname() + 
						"\nStudent ID : " + student.getID() + "\nStudent EvaluationType : " + 
						student.getEvaluationEntities().get(course) + "\n\n");
			}
			
			for(StudentModel student : course.getStudentsAllowedToEnroll()){
				for(ICourseOffering course2 : student.getCoursesAllowed())
				System.out.println(student.getName() + "\t\t -> " + course2.getCourseName());
			}
			for(InstructorModel instructor : course.getInstructor()){
                System.out.println(instructor.getName() +"\n"
                        + instructor.getSurname() + "\n"
                        + instructor.getID());
            }

		}*/

        while(true) {
            LoggedInAuthenticatedUser user = null;
            Scanner reader = new Scanner(System.in);
            Login login = new Login();
            //asks whether user would like to quit or continue the program
            System.out.println("Press q to quit program or enter to continue.");
            String cont = reader.nextLine();
            if(cont.equals("q")){
                System.exit(69);
            }
            else {
            //if user would like to continue will perform login operation
                System.out.println("\n-------Login-------\n");
                user = login.perform();
                //while loop to rerun login operation if user input was invalid
                while (user == null) {
                    System.out.println("\nUser does not exist, please try again.");
                    System.out.println("Press q to quit program or enter to continue.");
                    cont = reader.nextLine();
                    //exits if user entered "q"
                    if(cont.equals("q")){
                        System.exit(69);
                    }
                    System.out.println("\n-------Login-------\n");
                    //else performs login
                    user = login.perform();
                }
            }

            //gets logged in user type for use of providing appropriate operations
            String userType = user.getAuthenticationToken().getUserType();

            //if user is admin, will provide the admin with operations
            adminLoop:
            while(userType.equals("Admin")){
                //asks for operation user wishes to perform
                System.out.println("Choose an Operation:\n" +
                        "(1) Start\n" +
                        "(2) Stop\n" +
                        "(3) Logout");
                int adminChoice = reader.nextInt();
                switch(adminChoice){
                    case 1:
                        Start start = new Start();
                        start.perform(user);
                        break;
                    case 2:
                        Stop stop = new Stop();
                        stop.perform(user);
                        break;
                    case 3:
                        Logout logout = new Logout();
                        logout.perform(user);
                        System.out.println("Successfully logged out.");
                        break adminLoop;
                }
            }


            operationLoop:
            while (SystemState.status) { //loop which runs when state is "on" and provides students and instructors with their operations
                switch (userType) {
                    case "Student":
                        System.out.println("Choose an Operation:\n" +
                                "(1) Enroll in Course\n" +
                                "(2) Change Notification Preference\n" +
                                "(3) Print Course Record\n" +
                                "(4) Select Notification Status\n" +
                                "(5) Logout");
                        int studentChoice = reader.nextInt();
                        switch (studentChoice) {
                            case 1:
                                System.out.println("1");
                                break;
                            case 2:
                                System.out.println("2");
                                break;
                            case 3:
                                System.out.println("3");
                                break;
                            case 4:
                                System.out.println("4");
                                break;
                            case 5:
                                Logout logout = new Logout();
                                logout.perform(user);
                                System.out.println("Successfully logged out.");
                                break operationLoop; //breaks operation loop and asks for another log in
                        }
                        break;

                    case "Instructor":
                        System.out.println("Choose an Operation:\n" +
                                "(1) Add mark for student\n" +
                                "(2) Modify mark for sutdent\n" +
                                "(3) Calculate final grade for student\n" +
                                "(4) Print class record\n" +
                                "(5) Logout");
                        int instructorChoice = reader.nextInt();
                        switch (instructorChoice) {
                            case 1:
                                System.out.println("instructor 1");
                                break;
                            case 2:
                                System.out.println("2");
                                break;
                            case 3:
                                System.out.println("3");
                                break;
                            case 4:
                                System.out.println("4");
                                break;
                            case 5:
                                Logout logout = new Logout();
                                logout.perform(user);
                                System.out.println("Successfully logged out.");
                                break operationLoop; //breaks operation loop and asks for another log in
                        }
                        break;
                    default:
                        break operationLoop;
                }
            }

            if(!SystemState.status){
                System.out.println("The system is not running.\n" +
                        "You will now be logged out.\n");
                Logout logout = new Logout();
                logout.perform(user);
            }
        }



		
	}
}
