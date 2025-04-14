package com.umc.produto.rest;


import com.umc.produto.model.Produto;
import com.umc.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoRestController {

    @Autowired
    private ProdutoRepository repo;

    // Retorna a lista de todos os produtos cadastrados
    @GetMapping
    public List<Produto> listarTodos() {
        return repo.findAll();
    }

    // Cria um novo produto a partir dos dados recebidos no corpo da requisição
    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return repo.save(produto);
    }

    // Atualiza um produto existente com base no ID e nos novos dados recebidos
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id); // Garante que está atualizando e não criando um novo
        return repo.save(produto);
    }

    // Remove o produto com o ID fornecido
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
