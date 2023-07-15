package com.group4.movielist.controller;

import com.group4.movielist.dto.FilmeDTO;
import com.group4.movielist.model.entity.Filme;
import com.group4.movielist.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public List<FilmeDTO> listarFilmes() {
        return filmeService.listarFilmes();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity adicionarFilme(@RequestBody FilmeDTO filmeRequest) {
        try {
            Filme filme = filmeRequest.converter();
            return ResponseEntity.ok(filmeService.salvar(filme));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar-filme/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FilmeDTO filmeRequest) {
        try {
            Filme filme = filmeRequest.converter();
            return ResponseEntity.ok(filmeService.atualizar(id, filme));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar-filme/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        try {
            filmeService.deletar(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
