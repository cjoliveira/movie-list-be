package com.group4.movielist.service;

import com.group4.movielist.dto.FilmeDTO;
import com.group4.movielist.model.entity.Filme;

import java.util.List;
import java.util.Optional;

public interface FilmeService {

    FilmeDTO salvar (Filme filme);

    FilmeDTO atualizar(Long id, Filme filmeAtualizado);

    void deletar(Long id);

    List<FilmeDTO> listarFilmes();

    Optional<Filme> consultarPorId(Long id);

}

