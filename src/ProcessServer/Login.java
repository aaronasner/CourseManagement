package ProcessServer;

import authenticatedUsers.LoggedInAuthenticatedUser;
import java.util.Scanner;
import registrar.ModelRegister;
import authenticationServer.AuthenticationToken;
import systemUsers.SystemUserModel;


public class Login implements IOperation {

    String STUDENT = "systemUsers.StudentModel";
    String ADMIN = "systemUsers.AdminModel";
    String INSTRUCTOR = "systemUsers.InstructorModel";
    static int i = 0;

    public LoggedInAuthenticatedUser perform(){
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
                userType = "student";
            }
            else if(type.equals(ADMIN)){
                userType = "admin";
            }
            else if(type.equals(INSTRUCTOR)){
                userType = "instructor";
            }
            AuthenticationToken authToken = new AuthenticationToken();
            authToken.setTokenID(Integer.parseInt(ID));
            authToken.setSessionID(i++);
            authToken.setUserType(userType);

        }
        return null;
    }

    private boolean checkUserValid(String ID){
        return ModelRegister.getInstance().checkIfUserHasAlreadyBeenCreated(ID);
    }
}
