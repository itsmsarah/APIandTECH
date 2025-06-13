package com.techrep.apitechrep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techrep.apitechrep.controller.ProdutoController;

public interface ProdutoRepository extends JpaRepository<ProdutoController, Integer>{

}
