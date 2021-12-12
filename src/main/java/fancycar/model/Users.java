package fancycar.model;

public class Users {
	protected String userName;
	protected String Password;
	protected String firstName;
	protected String lastName;
	protected String Email;
	protected String Phone;
	
	public Users(String userName, String Password, String firstName, String lastName, String Email, String Phone) {
		this.userName = userName;
		this.Password = Password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Email = Email;
		this.Phone = Phone;
	}
	
	public Users(String userName, String Password, String firstName, String lastName, String Email) {
		this.userName = userName;
		this.Password = Password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.Email = Email;
	}

	public Users(String userName, String password) {
		this.userName = userName;
		Password = password;
	}

	public Users(String userName) {
		this.userName = userName;
	}
	
	/** Getters and setters */
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String Password) {
		this.Password = Password;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setPhone(String Phone) {
		this.Phone = Phone;
	}
	
	public String getPhone() {
		return Phone;
	}

	@Override
	public String toString() {
		return "Users{" +
				"Username = '" + userName + '\'' +
				", First Name = '" + firstName + '\'' +
				", Last Name = '" + lastName + '\'' +
				", Email = '" + Email + '\'' +
				", Phone = '" + Phone + '\'' +
				'}';
	}
}
