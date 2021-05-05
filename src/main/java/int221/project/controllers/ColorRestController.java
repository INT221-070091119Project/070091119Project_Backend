package int221.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import int221.project.models.Color;
import int221.project.repositories.ColorRepository;

@RestController
public class ColorRestController {
	
	@Autowired
	private ColorRepository colorRepository;
	
	@GetMapping("/colors")
	public List<Color> colors() {
		return colorRepository.findAll();
	}
	
	@GetMapping("/colors/{id}")
	public Color color(@PathVariable int id) {
		return colorRepository.findById(id).orElse(null);
	}
	
}
