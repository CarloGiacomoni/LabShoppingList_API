package com.carlogiacomoni.LabShoppingList_API.service.interfaces;

import java.util.List;

public interface DefaultCrud <T> {

    T salvar(T object);
    T atualizar(T object);
    T buscar(Long id);
    List<T> listar();
    void deletar(Long id);
}
