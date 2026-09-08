package com.pedidos360.ms_carrito.controller;

import com.pedidos360.ms_carrito.entity.CarritoItem;
import com.pedidos360.ms_carrito.service.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<CarritoItem> getByUsuario(@PathVariable String usuarioId) {
        return service.obtenerPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<CarritoItem> agregarItem(@RequestBody CarritoItem item) {
        CarritoItem guardado = service.agregarOActualizar(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long id) {
        service.eliminarItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable String usuarioId) {
        service.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}