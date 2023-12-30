package com.beltexam.ideasgeniales.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginUser {

	@NotEmpty(message = "Email es requerido!")
	@Email(message = "Por favor, ingresa un email válido")
	private String email;

	@NotEmpty(message = "Contraseña es requerida!")
	@Size(min = 8, max = 128, message = "Contraseña debe contener al menos 8 caracteres")
	private String password;

	public LoginUser() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}