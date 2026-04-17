package com.carlosbarahona.tienda.controller;


import com.carlosbarahona.tienda.entity.Producto;
import com.carlosbarahona.tienda.service.ProductoService;
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
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping
    public String listar(Model model){
        model.addAttribute("producto",productoService.listar());
        return "producto";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        Producto producto = new Producto();
        model.addAttribute("producto",producto);
        model.addAttribute("modoEdicion",false);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",false);
            return "producto-form";
        }

        productoService.crear(producto);
        return "redirect:/producto";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Integer id, Model model){
        model.addAttribute("producto",productoService.buscarPorId(id));
        model.addAttribute("modoEdicion",true);
        return "producto-form";
    }

    @PostMapping("actualizar/{id}")
    public String actualizar(@PathVariable Integer id,@Valid @ModelAttribute("producto")Producto producto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",true);
            return "producto-form";
        }

        productoService.actualizar(id,producto);
        return "redirect:/producto";
    }

    @GetMapping("eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        productoService.eliminar(id);
        return "redirect:/producto";
    }


    /*
    @GetMapping("/get")
    public List<Producto> lista(){
        return productoService.listar();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto){
        Producto creado = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

    @GetMapping("/getId/{id}")
    public Producto buscar(Integer id){
        return productoService.buscarPorId(id);
    }

    @PutMapping("/put/{id}")
    public Producto actualizar(@PathVariable Integer id, @Valid @RequestBody Producto producto){
        return productoService.actualizar(id,producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){
        productoService.eliminar(id);
    }*/
}
