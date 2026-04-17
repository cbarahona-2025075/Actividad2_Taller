package com.carlosbarahona.tienda.service;

import com.carlosbarahona.tienda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioService{
    List<Usuario> listar();
    Usuario crear(Usuario usuario);
    Usuario actualizar(Integer id, Usuario usuario);
    Usuario buscarPorId(Integer id);
    void eliminar(Integer id);
}
