package systemUsers;

public interface SystemUserModel extends SystemUser {

	private String name;
	private String surname;
	private String ID;

	public void setName(String name){
		this.name = name
	}

	public void setSurname(String surname){
		this.surname = surname
	}

	public void setID(String ID){
		this.ID = ID
	}
	
	public String getName(){
		return this.name
	}
	
	public String getSurname(){
		return this.suname
	}

	public String getID(){
		return this.ID
	}
}
