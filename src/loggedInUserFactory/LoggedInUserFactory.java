package loggedInUserFactory;

import authenticatedUsers.LoggedInAdmin;
import authenticatedUsers.LoggedInAuthenticatedUser;
import authenticatedUsers.LoggedInInstructor;
import authenticatedUsers.LoggedInStudent;
import authenticationServer.AuthenticationToken;
import registrar.ModelRegister;
import systemUsers.SystemUserModel;

public class LoggedInUserFactory {

	public LoggedInUserFactory(){
		
	}
	
	public LoggedInAuthenticatedUser createAuthenticatedUser(AuthenticationToken authenticationToken){
		switch(authenticationToken.getUserType()){
		case "Admin":
			return createLoggedInAdmin(authenticationToken);
		case "Student":
			return createLoggedInStudent(authenticationToken);
		case "Instructor":
			return createLoggedInInstructor(authenticationToken);
		default:
			return null;
		}
	}
	
	public LoggedInStudent createLoggedInStudent(AuthenticationToken authenticationToken){
		String ID = authenticationToken.getTokenID().toString();
		ModelRegister reg = ModelRegister.getInstance();
		SystemUserModel user = reg.getRegisteredUser(ID);
		LoggedInStudent student = new LoggedInStudent();
		student.setAuthenticationToken(authenticationToken);
		student.setID(user.getID());
		student.setName(user.getName());
		student.setSurname(user.getSurname());
		return student;
	}
	
	public LoggedInAdmin createLoggedInAdmin(AuthenticationToken authenticationToken){
		String ID = authenticationToken.getTokenID().toString();
		ModelRegister reg = ModelRegister.getInstance();
		SystemUserModel user = reg.getRegisteredUser(ID);
		LoggedInAdmin admin = new LoggedInAdmin();
		admin.setAuthenticationToken(authenticationToken);
		admin.setID(user.getID());
		admin.setName(user.getName());
		admin.setSurname(user.getSurname());
		return admin;
	}
	
	public LoggedInInstructor createLoggedInInstructor(AuthenticationToken authenticationToken){
		String ID = authenticationToken.getTokenID().toString();
		ModelRegister reg = ModelRegister.getInstance();
		SystemUserModel user = reg.getRegisteredUser(ID);
		LoggedInInstructor instructor = new LoggedInInstructor();
		instructor.setAuthenticationToken(authenticationToken);
		instructor.setID(user.getID());
		instructor.setName(user.getName());
		instructor.setSurname(user.getSurname());
		return instructor;
	}
}
