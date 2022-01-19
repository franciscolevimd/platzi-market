package com.platzi.market.persistence;

import com.platzi.market.persistence.crud.ProductoCrudRespository;
import com.platzi.market.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRespository productoCrudRespository;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRespository.findAll();
    }
}
