package com.carlosbarahona.tienda.controller;

import com.carlosbarahona.tienda.enumtypes.UserType;
import com.carlosbarahona.tienda.repository.UsuarioRepository;
import com.carlosbarahona.tienda.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.carlosbarahona.tienda.entity.Usuario;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {
    private final AuthService authService;
    private final UsuarioRepository usuarioRepository;

    public HomeController(AuthService authService, UsuarioRepository usuarioRepository) {
        this.authService = authService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        Usuario usuario = usuarioRepository.findByCorreoUsuario(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        model.addAttribute("nombreUsuario", usuario.getNombreUsuario());
        model.addAttribute("apellidoUsuario", usuario.getApellidoUsuario());
        return "home";
    }

    @GetMapping("/")
    public String redirectToHome(){
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerFormulario(@Valid @ModelAttribute("usuarios") Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("errores",result.getAllErrors());
            return "register";
        }

        authService.register(usuario.getNombreUsuario(),usuario.getApellidoUsuario(),usuario.getEdadUsuario(), usuario.getPasswordUsuario(), usuario.getCorreoUsuario());
        return "redirect:/login";

    }




}
