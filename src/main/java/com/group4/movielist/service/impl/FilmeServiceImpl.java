package com.group4.movielist.service.impl;

import com.group4.movielist.dto.FilmeDTO;
import com.group4.movielist.exceptions.BusinessRulesException;
import com.group4.movielist.model.entity.Filme;
import com.group4.movielist.model.repository.FilmeRepository;
import com.group4.movielist.service.FilmeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeServiceImpl implements FilmeService {

    private FilmeRepository repository;


    public FilmeServiceImpl(FilmeRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public FilmeDTO salvar(Filme filme) {

        // Garantindo idempotência
        Optional<Filme> optionalFilme = consultarPorId(filme.getId());
        if(optionalFilme.isPresent()) {
            throw new BusinessRulesException("Já existe filme cadastrado com esse id.");
        }
        return new FilmeDTO(repository.save(filme));
    }

    @Override
    @Transactional
    public FilmeDTO atualizar(Long id, Filme filmeAtualizado) {
        Optional<Filme> optionalFilme = consultarPorId(id);
        if(optionalFilme.isEmpty()){
            throw new BusinessRulesException("O filme não existe na base de dados.");
        }
        if(filmeAtualizado.getId() != null){
            throw new BusinessRulesException("Para essa função, não pode passar o id no body.");
        }
        return new FilmeDTO(repository.save(atualizarFilme(optionalFilme.get(), filmeAtualizado)));
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Optional<Filme> optionalFilme = consultarPorId(id);
        if(optionalFilme.isEmpty()){
            throw new BusinessRulesException("Não foi possível deletar o filme com esse id.");
        }
        Filme filme = optionalFilme.get();
        repository.delete(filme);
    }

    @Override
    @Transactional
    public List<FilmeDTO> listarFilmes() {
        return converterParaDTO(repository.findAll());
    }

    @Override
    @Transactional
    public Optional<Filme> consultarPorId(Long id) {
        return repository.findById(id);
    }

    private Filme atualizarFilme(Filme original, Filme atualizado) {
        if(atualizado.getTitulo() != null){
            original.setTitulo(atualizado.getTitulo());
        }
        if(atualizado.getDataLancamento() != null){
            original.setDataLancamento(atualizado.getDataLancamento());
        }
        if(atualizado.getDuracao() != null){
            original.setDuracao(atualizado.getDuracao());
        }
        if(atualizado.getClassificacao() != null){
            original.setClassificacao(atualizado.getClassificacao());
        }
        if(atualizado.getDescricao() != null){
            original.setDescricao(atualizado.getDescricao());
        }
        if(atualizado.getUrlFoto() != null){
            original.setUrlFoto(atualizado.getUrlFoto());
        }
        if(atualizado.getCategoria() != null){
            original.setCategoria(atualizado.getCategoria());
        }
        return original;
    }

    public List<FilmeDTO> converterParaDTO(List<Filme> filmes) {
        List<FilmeDTO> filmesDTO = new ArrayList<>();

        for (Filme filme : filmes) {
            FilmeDTO filmeDTO = new FilmeDTO(filme);
            filmesDTO.add(filmeDTO);
        }

        return filmesDTO;
    }

}
