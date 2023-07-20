package com.group4.movielist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group4.movielist.model.entity.Categoria;
import com.group4.movielist.model.entity.Filme;

import java.time.LocalDateTime;

public class FilmeDTO {

    public FilmeDTO() {
    }
    // Esse é o meu DTO
    public FilmeDTO(Filme filme) {
        this.id = filme.getId();
        this.titulo = filme.getTitulo();
        this.dataLancamento = filme.getDataLancamento();
        this.duracao = filme.getDuracao();
        this.classificacao = filme.getClassificacao();
        this.descricao = filme.getDescricao();
        this.urlFoto = filme.getUrlFoto();
        if (filme.getCategoria() != null) {
            this.categoriaId = filme.getCategoria().getId();
        } else {
            this.categoriaId = null;
        }
    }

    private Long id;

    private String titulo;

    @JsonProperty("data_lancamento")
    private LocalDateTime dataLancamento;

    private Long duracao;

    private Long classificacao;

    private String descricao;

    @JsonProperty("url_foto")
    private String urlFoto;

    @JsonProperty("categoria_id")
    private Long categoriaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDateTime dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }

    public Long getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Long classificacao) {
        this.classificacao = classificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Filme converter() {
        Filme filme = new Filme();
        filme.setTitulo(this.getTitulo());
        filme.setDataLancamento(this.getDataLancamento());
        filme.setDuracao(this.getDuracao());
        filme.setClassificacao(this.getClassificacao());
        filme.setDescricao(this.getDescricao());
        if (this.categoriaId != null) {
            filme.setCategoria(new Categoria(categoriaId));
        }
        if (this.getId() != null) {
            filme.setId(this.getId());
        }
        if (this.urlFoto != null) {
            filme.setUrlFoto(this.getUrlFoto());
        }
        return filme;
    }


}
