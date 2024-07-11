package com.generation.ceres.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.generation.ceres.model.Categoria;
import com.generation.ceres.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
    private CategoriaRepository categoriaRepository;
	
    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(categorias);
    }
    
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Categoria>> getByTitulo(@PathVariable String titulo) {
        List<Categoria> categorias = categoriaRepository.findByTituloContainingIgnoreCase(titulo);
        if (categorias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(categorias);
    }

    @PutMapping
    public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria) {
        return categoriaRepository.findById(categoria.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        categoriaRepository.deleteById(id);
    }
}