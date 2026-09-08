package com.pedidos360.ms_carrito.repository;

import com.pedidos360.ms_carrito.entity.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoItem, Long> {
    List<CarritoItem> findByUsuarioId(String usuarioId);
    Optional<CarritoItem> findByUsuarioIdAndProductoId(String usuarioId, Long productoId);
    void deleteByUsuarioId(String usuarioId);
}