package com.generation.ceres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.ceres.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}
