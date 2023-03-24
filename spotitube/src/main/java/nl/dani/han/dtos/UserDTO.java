package nl.dani.han.dtos;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

public class UserDTO {

	private String username;

	private String password;

	public UserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}