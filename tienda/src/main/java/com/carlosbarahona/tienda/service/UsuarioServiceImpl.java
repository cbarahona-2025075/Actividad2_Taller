package com.carlosbarahona.tienda.service;

import com.carlosbarahona.tienda.entity.Usuario;
import com.carlosbarahona.tienda.exception.ResourceNotFoundException;
import com.carlosbarahona.tienda.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crear(Usuario usuario) {
        usuario.setIdUsuario(null);
        usuario.setPasswordUsuario(passwordEncoder.encode(usuario.getPasswordUsuario()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario con ID, no encontrado: " + id));
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(id);
        usuarioExistente.setNombreUsuario(usuario.getNombreUsuario());
        usuarioExistente.setApellidoUsuario(usuario.getApellidoUsuario());
        usuarioExistente.setEdadUsuario(usuario.getEdadUsuario());
        usuarioExistente.setCorreoUsuario(usuario.getCorreoUsuario());
        usuarioExistente.setPasswordUsuario(passwordEncoder.encode(usuario.getPasswordUsuario()));
        usuarioExistente.setRolUsuario(usuario.getRolUsuario());

        return usuarioRepository.save(usuarioExistente);
    }


    @Override
    public void eliminar(Integer id) {
        if(!usuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }

        usuarioRepository.deleteById(id);

    }
}
