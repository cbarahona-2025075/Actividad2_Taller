package com.carlosbarahona.tienda.entity;

import com.carlosbarahona.tienda.enumtypes.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "correo_usuario")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;

    @NotBlank(message = "El nombre del usuario no puede ir vacio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 60 caracteres.")
    @Column(name="nombre_usuario")
    private String nombreUsuario;

    @NotBlank(message = "El apellido del usuario no puede ir vacio")
    @Size(min = 2, max = 60, message = "El apellido debe tener entre 2 y 60 caracteres.")
    @Column(name="apellido_usuario")
    private String apellidoUsuario;

    @NotNull(message = "La edad no puede ir vacia")
    @Min(value = 1, message = "La edad debe de ser mayor o igual a 1")
    @Max(value = 120, message = "La edad maxima es de 120 caracteres")
    @Column(name="edad_usuario")
    private Integer edadUsuario;

    @Size(min = 6, max = 150, message = "El correo debe tener entre 6 y 150 caracteres")
    @NotBlank(message = "El correo del usuario no puede ir vacio.")
    @Column(name="correo_usuario")
    private String correoUsuario;

    @Size(min = 8, max = 255, message = "La contraseña debe tener entre 8 a 255 caracteres")
    @NotBlank(message = "La contraseña del usuario no puede ir vacio.")
    @Column(name="password")
    private String passwordUsuario;

    @Enumerated(EnumType.STRING)
   // @NotNull(message = "El rol del usuario no puede ser nulo")
    @Column(name = "rol_usuario")
    private UserType rolUsuario;

    //Metodos getters and setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public Integer getEdadUsuario() {
        return edadUsuario;
    }

    public void setEdadUsuario(Integer edadUsuario) {
        this.edadUsuario = edadUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public UserType getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(UserType rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
