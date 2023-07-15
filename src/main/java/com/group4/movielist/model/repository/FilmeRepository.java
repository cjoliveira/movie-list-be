package com.group4.movielist.model.repository;

import com.group4.movielist.model.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findAll();
}
