package com.carlosbarahona.tienda.controller;


import com.carlosbarahona.tienda.entity.Pedido;
import com.carlosbarahona.tienda.service.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("pedido",pedidoService.listar());
        return "pedido";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        Pedido pedido = new Pedido();
        model.addAttribute("pedido",pedido);
        model.addAttribute("modoEdicion",false);
        return "pedido-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("pedido") Pedido pedido, BindingResult result, Model model){
        System.out.println("ya inicio");
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",false);
            System.out.println(pedido);
            return "pedido-form";
        }

        pedidoService.crear(pedido);
        return "redirect:/pedido";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Integer id,Model model){
       model.addAttribute("pedido",pedidoService.buscarPorId(id));
       model.addAttribute("modoEdicion",true);
       return "pedido-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("pedido") Pedido pedido, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion",true);
        }

        pedidoService.actualizar(id,pedido);
        return "redirect:/pedido";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        pedidoService.eliminar(id);
        return "redirect:/pedido";
    }


    /*
    @GetMapping("/get")
    public List<Pedido> listar(){
        return pedidoService.listar();
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pedido> crear(@Valid @RequestBody Pedido pedido){
        Pedido creado = pedidoService.crear(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/getId/{id}")
    public Pedido buscar(@PathVariable Integer id){
        return pedidoService.buscarPorId(id);
    }

    @PutMapping("/put/{id}")
    public Pedido actualizar(@PathVariable Integer id,@Valid @RequestBody Pedido pedido){
        return pedidoService.actualizar(id,pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){
        pedidoService.eliminar(id);
    }*/
}
