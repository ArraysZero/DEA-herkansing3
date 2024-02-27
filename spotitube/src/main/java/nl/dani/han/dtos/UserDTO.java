package nl.dani.han.dtos;

public class UserDTO extends DataTransferObject {
	private String user;
	private String password;

	public UserDTO(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public UserDTO() {
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}