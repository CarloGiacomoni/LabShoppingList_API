package com.carlogiacomoni.LabShoppingList_API.api.controller.controller;

import com.carlogiacomoni.LabShoppingList_API.model.categoria;
import com.carlogiacomoni.LabShoppingList_API.service.interfaces.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<categoria> salvar(@RequestBody @Valid categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaService.salvar(categoria));
    }

    @GetMapping
    public ResponseEntity<List<categoria>> listar() {
        return ResponseEntity.ok(this.categoriaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<categoria> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoriaService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<categoria> atualizar(@RequestBody @Valid categoria categoria) {
        return ResponseEntity.ok(this.categoriaService.atualizar(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.categoriaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}