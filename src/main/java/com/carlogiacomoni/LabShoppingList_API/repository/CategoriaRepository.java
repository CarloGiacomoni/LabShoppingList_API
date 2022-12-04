package com.carlogiacomoni.LabShoppingList_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlogiacomoni.LabShoppingList_API.model.categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<categoria, Long> {
    boolean existsByNome(String nome);
}