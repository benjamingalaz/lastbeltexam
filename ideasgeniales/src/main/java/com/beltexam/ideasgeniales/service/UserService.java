package com.beltexam.ideasgeniales.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.beltexam.ideasgeniales.models.User;
import com.beltexam.ideasgeniales.repository.UserRepository;

@Service
public class UserService {
    
    	//INYECCION DE DEPENDENCIAS
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User encontrarPorEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User encontrarPorId(Long id) {
		Optional<User> usuario = userRepository.findById(id);
		if(usuario.isPresent()) {
			return usuario.get();
			
		}
		return null;
	}
	
	//REGISTRAR USUARIO
	
	public User registrarUsuario(User usuario, BindingResult resultado) {
        User usuarioRegistrar = userRepository.findByEmail(usuario.getEmail());

        if(usuarioRegistrar != null) {
            resultado.rejectValue("email", "Matches", "Email ya existe");
        }
        if(!usuario.getPassword().equals(usuario.getPasswordConfirmation())) {
            resultado.rejectValue("password", "Matches", "Password no coincide");
        }
        if(resultado.hasErrors()) {
            return null;
        }
        String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(hashed);
        return userRepository.save(usuario);
    }
	
	// AUTENTICACION USUARIO (LOGIN)
	public boolean autenticacionUser(String email, String password, BindingResult resultado) {
		User usuarioRegistrar = userRepository.findByEmail(email);
		
		if(usuarioRegistrar == null) {
			resultado.rejectValue("email", "Matches", "Email no valido");
			return false;

		}else {
			if(BCrypt.checkpw(password, usuarioRegistrar.getPassword())) {
				return true;
			}else {
				resultado.rejectValue("password", "Matches", "Contraseña incorrecta");
				return false;
			}
		}	
	}
}
