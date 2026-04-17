package com.carlosbarahona.tienda.controller;

import com.carlosbarahona.tienda.entity.Usuario;
import com.carlosbarahona.tienda.enumtypes.UserType;
import com.carlosbarahona.tienda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Metodo nuevo
    @GetMapping
    public String listar(Model model){
        model.addAttribute("usuarios",usuarioService.listar());
        return "usuarios";
    }

    //Metodo para agregar un usuario
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        Usuario usuario = new Usuario();
        usuario.setRolUsuario(UserType.USUARIO);
        model.addAttribute("usuarios",usuario);
        model.addAttribute("modoEdicion",false);
        return "usuario-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("usuarios") Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",false);
            usuario.setRolUsuario(UserType.USUARIO);
            return "usuario-form";
        }

        usuarioService.crear(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Integer id, Model model){
        model.addAttribute("usuarios", usuarioService.buscarPorId(id));
        model.addAttribute("modoEdicion",true);
        return "usuario-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("usuarios")Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",true);
        }
        usuarioService.actualizar(id, usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }


    /*Metodo anterior
    @GetMapping("/get")
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario){
        Usuario creado = usuarioService.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/getId/{id}")
    public Usuario buscar(@PathVariable  Integer id){
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/put/{id}")
    public Usuario actualizar(@PathVariable  Integer id, @Valid @RequestBody Usuario usuario){
        return usuarioService.actualizar(id,usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){
        usuarioService.eliminar(id);
    }*/
}
