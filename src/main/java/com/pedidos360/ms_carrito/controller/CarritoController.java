package com.pedidos360.ms_carrito.controller;

import com.pedidos360.ms_carrito.entity.CarritoItem;
import com.pedidos360.ms_carrito.service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controladores REST para la gestión del carrito de compras - Pedidos360 (v1.1.0)
@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    // USER y ADMIN pueden consultar el carrito
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/usuario/{usuarioId}")
    public List<CarritoItem> getByUsuario(@PathVariable String usuarioId) {
        return service.obtenerPorUsuario(usuarioId);
    }

    // USER y ADMIN pueden agregar productos al carrito
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<CarritoItem> agregarItem(
            @RequestBody CarritoItem item) {

        CarritoItem guardado = service.agregarOActualizar(item);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardado);
    }

    // USER y ADMIN pueden eliminar productos del carrito
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long id) {

        service.eliminarItem(id);

        return ResponseEntity.noContent().build();
    }

    // USER y ADMIN pueden vaciar el carrito
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> vaciarCarrito(
            @PathVariable String usuarioId) {

        service.vaciarCarrito(usuarioId);

        return ResponseEntity.noContent().build();
    }
}