package com.carlosbarahona.tienda.repository;

import com.carlosbarahona.tienda.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
