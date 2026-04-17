package com.carlosbarahona.tienda.repository;

import com.carlosbarahona.tienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
