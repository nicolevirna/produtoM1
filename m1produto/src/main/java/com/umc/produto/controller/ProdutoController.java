package com.umc.produto.controller;



import com.umc.produto.model.Produto;
import com.umc.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repo;

    // Lista todos os produtos cadastrados
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", repo.findAll());
        return "list";
    }

    // Abre o formulário para criação de novo produto
    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "form";
    }

    // Salva ou atualiza um produto no banco de dados
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto) {
        repo.save(produto); // Persiste no banco
        return "redirect:/produtos"; // Redireciona para lista
    }

    // Carrega os dados do produto no formulário para edição
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Produto produto = repo.findById(id).orElseThrow();
        model.addAttribute("produto", produto);
        return "form";
    }

    // Exclui o produto com o ID informado
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/produtos";
    }
}
