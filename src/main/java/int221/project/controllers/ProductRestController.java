package int221.project.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int221.project.models.Color;
import int221.project.models.Product;
import int221.project.repositories.ColorRepository;
import int221.project.repositories.ProductRepository;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ColorRepository colorRepository;
	
	@GetMapping("")
	public List<Product> product() {
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Product product(@PathVariable int id) {
		return productRepository.findById(id).orElse(null);
	}

	@PostMapping("")
	public Product create(@RequestBody Product newProduct) {
		productRepository.save(newProduct);
		return newProduct;
	}
	
	@PutMapping("")
	public Product update(@RequestBody Product newProduct) {
		productRepository.save(newProduct);
		return newProduct;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		productRepository.deleteById(id);
	}
	
	@GetMapping("/colors")
	public List<Color> color() {
		return colorRepository.findAll();
	}
}
