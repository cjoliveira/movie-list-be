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
@CrossOrigin(origins = {"http://127.0.0.1:3000", "http://127.0.0.1:5500"})
public class FilmeController {

    private static final int QUANTIDADE_PREFERIDOS = 3;

    FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public List<FilmeDTO> listarFilmes() {
        return filmeService.listarFilmes();
    }

    @GetMapping("/buscar-populares")
    public List<FilmeDTO> buscarPopulares() {
        List<FilmeDTO> lista = filmeService.buscarPopulares(QUANTIDADE_PREFERIDOS);
        return lista;
    }

    @GetMapping("/buscar")
    public List<FilmeDTO> buscar(@RequestParam(value = "titulo", required = true) String titulo) {
        return filmeService.buscarFilme(titulo);
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

    @DeleteMapping("/deletar-filmes")
    public ResponseEntity deletarFilmes(@RequestParam("ids") Long[] ids) {
        try {
            filmeService.deletarFilmes(ids);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
