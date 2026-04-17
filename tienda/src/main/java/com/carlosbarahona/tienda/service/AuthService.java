package com.carlosbarahona.tienda.service;


import com.carlosbarahona.tienda.entity.Usuario;
import com.carlosbarahona.tienda.enumtypes.UserType;
import com.carlosbarahona.tienda.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo " + username));
        return User.builder()
                .username(usuario.getCorreoUsuario())
                .password(usuario.getPasswordUsuario())
                .roles(usuario.getRolUsuario().name())
                .build();
    }

    public boolean register(String nombreUsuario, String apellidoUsuario, Integer edad, String password,String correo) {
        if (usuarioRepository.findByCorreoUsuario(correo).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(nombreUsuario);
        nuevoUsuario.setApellidoUsuario(apellidoUsuario);
        nuevoUsuario.setEdadUsuario(edad);
        nuevoUsuario.setCorreoUsuario(correo);
        nuevoUsuario.setPasswordUsuario(passwordEncoder.encode(password));
        nuevoUsuario.setRolUsuario(UserType.USUARIO);
        usuarioRepository.save(nuevoUsuario);
        return true;

    }

}
