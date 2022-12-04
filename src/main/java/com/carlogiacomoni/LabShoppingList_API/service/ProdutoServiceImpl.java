package com.carlogiacomoni.LabShoppingList_API.service;

import com.carlogiacomoni.LabShoppingList_API.model.produto;
import com.carlogiacomoni.LabShoppingList_API.repository.ProdutoRepository;
import com.carlogiacomoni.LabShoppingList_API.service.interfaces.CategoriaService;
import com.carlogiacomoni.LabShoppingList_API.service.interfaces.ProdutoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    // O C.R.U.D.

    @Override
    public produto salvar(produto object) {
        validaNome(object.getNome());
        validaCategoria(object.getCategoria().getId());
        return this.produtoRepository.save(object);
    }

    @Override
    public produto atualizar(produto object) {
        produto produtoPesquisado = buscar(object.getId());

        if (Objects.nonNull(object)) {
            BeanUtils.copyProperties(object, produtoPesquisado, "id");
            this.produtoRepository.save(produtoPesquisado);
        }

        return null;
    }

    @Override
    public produto buscar(Long id) {
        Optional<produto> produtoPesquisado = this.produtoRepository.findById(id);

        if (produtoPesquisado.isEmpty()) {
            throw new EntityNotFoundException("Não foi possivel encontrar o produto com o Id: " + id);
        }

        return produtoPesquisado.get();
    }

    @Override
    public List<produto> listar() {
        return this.produtoRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        this.produtoRepository.deleteById(id);
    }

    // Os Métodos

    private void validaNome(String nome) {
        if (this.produtoRepository.existsByNome(nome)) {
            throw new EntityExistsException("Já existe um produto com o mesmo nome: " + nome);
        }

    }

    private void validaCategoria(Long id) {
        this.categoriaService.buscar(id);
    }

    @Override
    public List<produto> listarByCategoriaId(Long id) {
        List<produto> produtos = listar();
        List<produto> produtosDaCategoria = new ArrayList<produto>();

        for (com.carlogiacomoni.LabShoppingList_API.model.produto produto : produtos) {
            if (produto.getCategoria() == this.categoriaService.buscar(id)) {
                produtosDaCategoria.add(produto);
            }
        }

        if (produtosDaCategoria.size() < 1) {
            throw new EntityNotFoundException("Não existem produtos da categoria " + this.categoriaService.buscar(id));
        }

        return produtosDaCategoria;
    }

    @Override
    public List<produto> listarByPago() {
        List<produto> produtos = listar();
        List<produto> produtosPagos = new ArrayList<produto>();

        for (com.carlogiacomoni.LabShoppingList_API.model.produto produto : produtos) {
            if (produto.getPago() != null && produto.getPago() == true) {
                produtosPagos.add(produto);
            }
        }

        if (produtosPagos.size() < 1) {
            throw new EntityNotFoundException("Não existem produtos pagos");
        }

        return produtosPagos;
    }

    @Override
    public String totalCompra() {
        List<produto> produtosPagos = listarByPago();
        Double total = 0d;
        DecimalFormat df = new DecimalFormat("0.00");

        for (com.carlogiacomoni.LabShoppingList_API.model.produto produto : produtosPagos) {
            total += produto.getValor();
        }
        df.setRoundingMode(RoundingMode.HALF_UP);
        return "O total da compra foi de " + df.format(total);
    }

}