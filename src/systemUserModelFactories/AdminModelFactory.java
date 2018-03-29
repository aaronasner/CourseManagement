package systemUserModelFactories;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import offerings.ICourseOffering;
import registrar.ModelRegister;
import systemUsers.AdminModel;
import systemUsers.InstructorModel;

public class AdminModelFactory implements SystemUserModelFactory {

	public AdminModel createSystemUserModel(BufferedReader br, ICourseOffering course) {
		AdminModel newAdminModel = new AdminModel();
		try{
			String line = br.readLine();
			if(!ModelRegister.getInstance().checkIfUserHasAlreadyBeenCreated(line.split(" ")[2])){
				newAdminModel.setName(line.split(" ")[0]);
				newAdminModel.setSurname(line.split(" ")[1]);
				newAdminModel.setID(line.split(" ")[2]);
				ModelRegister.getInstance().registerUser(newAdminModel.getID(), newAdminModel);
			}
			newAdminModel = (AdminModel) ModelRegister.getInstance().getRegisteredUser(line.split(" ")[2]);
			return newAdminModel;
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
			return null;
		}
	}


}

