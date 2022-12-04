package com.carlogiacomoni.LabShoppingList_API.service;

import com.carlogiacomoni.LabShoppingList_API.model.categoria;
import com.carlogiacomoni.LabShoppingList_API.model.produto;
import com.carlogiacomoni.LabShoppingList_API.repository.CategoriaRepository;
import com.carlogiacomoni.LabShoppingList_API.repository.ProdutoRepository;
import com.carlogiacomoni.LabShoppingList_API.service.interfaces.CategoriaService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaServiceImpl<Produto> implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // O C.R.U.D.

    @Override
    public categoria salvar(categoria categoria) {
        validaNome(categoria.getNome());
        return this.categoriaRepository.save(categoria);
    }

    @Override
    public categoria atualizar(categoria categoria) {
        com.carlogiacomoni.LabShoppingList_API.model.categoria categoriaPesquisada = buscar(categoria.getId());

        if (Objects.nonNull(categoria)) {
            BeanUtils.copyProperties(categoria, categoriaPesquisada, "id");
            validaNome(categoriaPesquisada.getNome().toLowerCase());
            this.categoriaRepository.save(categoria);
        }
        return null;
    }

    @Override
    public categoria buscar(Long id) {
        Optional<categoria> categoriaPesquisada = this.categoriaRepository.findById(id);

        if (categoriaPesquisada.isEmpty()) {
            throw new EntityNotFoundException("Categoria com o Id não encontrada: " + id);
        }

        return categoriaPesquisada.get();
    }

    @Override
    public List<categoria> listar() {
        return this.categoriaRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        categoriaEmUso(id);
        this.categoriaRepository.deleteById(id);
    }

    // Os Métodos

    private void validaNome(String nome) {
        if (this.categoriaRepository.existsByNome(nome)) {
            throw new EntityExistsException("Categoria com o nome já existe: " + nome);
        }
    }

    private void categoriaEmUso(Long id) {
        List<produto> produtos = this.produtoRepository.findAll();

        for (com.carlogiacomoni.LabShoppingList_API.model.produto produto : produtos) {
            if (produto.getCategoria().getId() == id) {
                throw new EntityExistsException("Categoria em uso, não pode ser deletada.");
            }
        }

    }
}