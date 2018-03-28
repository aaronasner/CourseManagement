package ProcessServer;

import authenticatedUsers.LoggedInAuthenticatedUser;
import java.util.Scanner;
import registrar.ModelRegister;
import authenticationServer.AuthenticationToken;
import systemUsers.SystemUserModel;
import loggedInUserFactory.LoggedInUserFactory;


public class Login extends IOperation {

    String STUDENT = "systemUsers.StudentModel";
    String ADMIN = "systemUsers.AdminModel";
    String INSTRUCTOR = "systemUsers.InstructorModel";
    static int i = 0;

    public LoggedInAuthenticatedUser perform(){
        LoggedInAuthenticatedUser loggedUser = null;
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String name = reader.nextLine();
        System.out.println("Enter Last Name:");
        String surname = reader.nextLine();
        System.out.println("Enter ID:");
        String ID = reader.nextLine();
        if(checkUserValid(ID)){
            SystemUserModel user = ModelRegister.getInstance().getRegisteredUser(ID);
            String type = user.getClass().getName();
            String userType = "";
            if(type.equals(STUDENT)){
                userType = "Student";
            }
            else if(type.equals(ADMIN)){
                userType = "Admin";
            }
            else if(type.equals(INSTRUCTOR)){
                userType = "Instructor";
            }
            AuthenticationToken authToken = new AuthenticationToken();
            authToken.setTokenID(Integer.parseInt(ID));
            authToken.setSessionID(i++);
            authToken.setUserType(userType);
            LoggedInUserFactory lu = new LoggedInUserFactory();
            loggedUser = lu.createAuthenticatedUser(authToken);
        }
        return loggedUser;
    }

    private boolean checkUserValid(String ID){
        return ModelRegister.getInstance().checkIfUserHasAlreadyBeenCreated(ID);
    }
}
