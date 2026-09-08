package com.pedidos360.ms_carrito.service;

import com.pedidos360.ms_carrito.entity.CarritoItem;
import com.pedidos360.ms_carrito.repository.CarritoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarritoService {

    private final CarritoRepository repository;

    public CarritoService(CarritoRepository repository) {
        this.repository = repository;
    }

    public List<CarritoItem> obtenerPorUsuario(String usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public CarritoItem agregarOActualizar(CarritoItem item) {
        // Si el usuario ya tiene este producto en el carrito, sumamos la cantidad
        return repository.findByUsuarioIdAndProductoId(item.getUsuarioId(), item.getProductoId())
                .map(existente -> {
                    existente.setCantidad(existente.getCantidad() + item.getCantidad());
                    return repository.save(existente);
                })
                .orElseGet(() -> repository.save(item));
    }

    public void eliminarItem(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void vaciarCarrito(String usuarioId) {
        repository.deleteByUsuarioId(usuarioId);
    }
}