package com.carlogiacomoni.LabShoppingList_API.service.interfaces;

import java.util.List;

import com.carlogiacomoni.LabShoppingList_API.model.produto;

public interface ProdutoService extends DefaultCrud<produto> {
    List<produto> listarByCategoriaId(Long id);

    List<produto> listarByPago();

    String totalCompra();
}
