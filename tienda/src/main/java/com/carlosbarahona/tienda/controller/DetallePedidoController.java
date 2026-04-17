package com.carlosbarahona.tienda.controller;

import com.carlosbarahona.tienda.entity.DetallePedido;
import com.carlosbarahona.tienda.service.DetallePedidoService;
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
@RequestMapping("/detalle")
public class DetallePedidoController {
    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("detallePedido",detallePedidoService.listar());
        return "detallePedido";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        DetallePedido detallePedido = new DetallePedido();
        model.addAttribute("detallePedido",detallePedido);
        model.addAttribute("modoEdicion", false);
        return "detallePedido-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("detallePedido") DetallePedido detallePedido, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",false);
            return "detallePedido-form";
        }

        detallePedidoService.crear(detallePedido);
        return "redirect:/detalle";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Integer id, Model model){
        model.addAttribute("detallePedido",detallePedidoService.buscarPorId(id));
        model.addAttribute("modoEdicion",true);
        return "detallePedido-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("detallePedido") DetallePedido detallePedido, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",true);
        }

        detallePedidoService.actualizar(id,detallePedido);
        return "redirect:/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        detallePedidoService.eliminar(id);
        return "redirect:/detalle";
    }

    /*
    @GetMapping("/get")
    public List<DetallePedido> listar(){
        return detallePedidoService.listar();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DetallePedido> crear(@Valid @RequestBody DetallePedido detallePedido){
        DetallePedido creado = detallePedidoService.crear(detallePedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/getId/{id}")
    public DetallePedido buscarPorId(Integer id){
        return detallePedidoService.buscarPorId(id);
    }

    @PutMapping("put/{id}")
    public DetallePedido actualizar(@PathVariable Integer id, @Valid @RequestBody DetallePedido detallePedido){
        return detallePedidoService.actualizar(id,detallePedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){
        detallePedidoService.eliminar(id);
    }*/
}
