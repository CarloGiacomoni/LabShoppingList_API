package com.carlogiacomoni.LabShoppingList_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlogiacomoni.LabShoppingList_API.model.produto;

public interface ProdutoRepository extends JpaRepository<produto, Long> {
    boolean existsByNome(String nome);
}
