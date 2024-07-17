package com.generation.ceres.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ceres.model.Produto;
import com.generation.ceres.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		List<Produto> produtos = produtoRepository.findAll();
		if (produtos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok().body(produtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok().body(produto.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping ("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));

	}


	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		return produtoRepository.findById(produto.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
