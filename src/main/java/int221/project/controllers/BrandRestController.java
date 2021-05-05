package int221.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import int221.project.models.Brand;
import int221.project.models.Color;
import int221.project.repositories.BrandRepository;

@RestController
public class BrandRestController {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping("/brands")
	public List<Brand> brands() {
		return brandRepository.findAll();
	}
}
