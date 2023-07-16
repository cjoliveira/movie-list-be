package com.group4.movielist.controller;

import com.group4.movielist.dto.FilmeDTO;
import com.group4.movielist.model.entity.Categoria;
import com.group4.movielist.model.repository.CategoriaRepository;
import com.group4.movielist.model.repository.FilmeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = {"http://127.0.0.1:3000", "http://127.0.0.1:5500"})
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Categoria> buscarCategorias() {
        List<Categoria> lista = repository.findAll();
        return lista;
    }
}
