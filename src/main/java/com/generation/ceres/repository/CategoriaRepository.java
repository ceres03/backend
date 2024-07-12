package com.generation.ceres.repository;

import com.generation.ceres.model.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria , Long> {
	
	List<Categoria> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
