package com.platzi.market.persistence;

import com.platzi.market.persistence.crud.ProductoCrudRespository;
import com.platzi.market.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    private ProductoCrudRespository productoCrudRespository;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRespository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria)  {
        return productoCrudRespository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad) {
        return productoCrudRespository.finByCantidadStockLessThanAndEstado(cantidad, true);
    }
}
