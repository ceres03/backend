package com.generation.ceres.repository;

import com.generation.ceres.model.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria , Long> {
	List<Categoria> findByTituloContainingIgnoreCase(String titulo);

}
