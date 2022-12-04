package com.carlogiacomoni.LabShoppingList_API.api.controller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlogiacomoni.LabShoppingList_API.model.produto;
import com.carlogiacomoni.LabShoppingList_API.service.interfaces.ProdutoService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<produto> save(@RequestBody @Valid produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.produtoService.salvar(produto));
    }

    @GetMapping
    public ResponseEntity<List<produto>> listar() {
        return ResponseEntity.ok(this.produtoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.produtoService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<produto> atualizar(@RequestBody produto produto) {
        return ResponseEntity.ok(this.produtoService.atualizar(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarById(@PathVariable Long id) {
        this.produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<produto>> buscarByCategoriaId(@PathParam("id") Long id) {
        return ResponseEntity.ok(this.produtoService.listarByCategoriaId(id));
    }

    @GetMapping("/total")
    public ResponseEntity<String> totalCompra() {
        return ResponseEntity.ok(this.produtoService.totalCompra());
    }
}
