package com.carlosbarahona.tienda.controller;

import com.carlosbarahona.tienda.entity.Categoria;
import com.carlosbarahona.tienda.service.CategoriaService;
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
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;


    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("categoria", categoriaService.listar());
        return "categoria";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        model.addAttribute("modoEdicion",false);
        return "categoria-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",false);
            return "categoria-form";
        }

        categoriaService.crear(categoria);
        return "redirect:/categoria";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Integer id, Model model){
        model.addAttribute("categoria",categoriaService.buscarPorId(id));
        model.addAttribute("modoEdicion",true);
        return "categoria-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",true);
        }

        categoriaService.actualizar(id,categoria);
        return "redirect:/categoria";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        categoriaService.eliminar(id);
        return "redirect:/categoria";
    }

    /*
    @GetMapping("/get")
    public List<Categoria> listar(){
        return categoriaService.listar();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria){
        Categoria creado = categoriaService.crear(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("/getId/{id}")
    public Categoria buscar(@PathVariable Integer id){
        return categoriaService.buscarPorId(id);
    }

    @PutMapping("/put/{id}")
    public Categoria actualizar(@PathVariable Integer id, @Valid @RequestBody Categoria categoria){
        return categoriaService.actualizar(id,categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){
        categoriaService.eliminar(id);
    }*/


}
