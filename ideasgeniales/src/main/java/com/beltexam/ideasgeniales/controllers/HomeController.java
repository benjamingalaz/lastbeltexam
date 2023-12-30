package com.beltexam.ideasgeniales.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.beltexam.ideasgeniales.models.Idea;
import com.beltexam.ideasgeniales.models.LoginUser;
import com.beltexam.ideasgeniales.models.User;
import com.beltexam.ideasgeniales.repository.IdeaRepository;
import com.beltexam.ideasgeniales.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    private final UserService userService;
    private final IdeaRepository ideaRepository;

    public HomeController(UserService userService, IdeaRepository ideaRepository) {
        this.userService = userService;
        this.ideaRepository = ideaRepository;
    }

    @GetMapping("/")
    public String index(Model modelo) {
        modelo.addAttribute("registro", new User());
        modelo.addAttribute("login", new LoginUser());

        return "index.jsp";
    }

    @PostMapping("/register")
    public String registro(@Valid @ModelAttribute("registro") User nuevoUsuario,
            BindingResult resultado, Model modelo, HttpSession sesion) {

        if (resultado.hasErrors()) {
            modelo.addAttribute("login", new LoginUser());
            return "index.jsp";
        }

        User usuarioRegistrar = userService.registrarUsuario(nuevoUsuario, resultado);

        if (usuarioRegistrar != null) {
            modelo.addAttribute("login", new LoginUser());
            modelo.addAttribute("registro", new User());
            modelo.addAttribute("registroExitoso", true);
            return "index.jsp";
        } else {
            modelo.addAttribute("login", new LoginUser());
            return "index.jsp";
        }
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("login") LoginUser loginuser,
            BindingResult resultado, Model modelo, HttpSession sesion) {

        if (resultado.hasErrors()) {
            modelo.addAttribute("registro", new User());
            return "index.jsp";
        }

        if (userService.autenticacionUser(loginuser.getEmail(), loginuser.getPassword(), resultado)) {
            User usuarioLog = userService.encontrarPorEmail(loginuser.getEmail());
            sesion.setAttribute("userID", usuarioLog.getId());
            return "redirect:/ideas";
        } else {
            modelo.addAttribute("registro", new User());
            return "index.jsp";
        }
    }

    @GetMapping("/nueva-idea")
    public String mostrarNuevoFormulario(Model modelo) {
        modelo.addAttribute("nuevaIdea", new Idea());
        return "new.jsp";
    }

    @PostMapping("/agregarIdea")
    public String agregarIdea(@Valid @ModelAttribute("nuevaIdea") Idea nuevaIdea,
                              BindingResult resultado, Model modelo, HttpSession sesion) {

        if (resultado.hasErrors()) {
            return "new.jsp";
        }

        Long userId = (Long) sesion.getAttribute("userID");
        User usuario = userService.encontrarPorId(userId);

        if (usuario != null) {
            nuevaIdea.setUsuarioAgregador(usuario);

            Idea ideaExistente = ideaRepository.findByNombreIdea(nuevaIdea.getNombreIdea());
            if (ideaExistente != null) {
                resultado.rejectValue("nombreIdea", "error.nuevaIdea", "Esta idea ya ha sido agregada");
                return "new.jsp";
            }

            ideaRepository.save(nuevaIdea);

            // Corregir la redirección a /ideas
            return "redirect:/ideas";
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/";
    }
}
