package int221.project.controllers;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import int221.project.models.*;
import int221.project.repositories.*;
import int221.project.service.*;
import net.bytebuddy.implementation.bytecode.Throw;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ProductColorRepository productColorRepository;
	
	private FileService fileService = new FileService();
	
	@GetMapping("")
	public List<Product> product() {
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Product product(@PathVariable int id) {
		return productRepository.findById(id).orElse(null);
	}
	
	@GetMapping("/image/{name}")
	public Path image(@PathVariable String name) {
		return fileService.load(name);
	}

	@PostMapping("")
	public Product create(@RequestParam(value = "image", required = false) MultipartFile productImage,@RequestPart Product newProduct) throws Exception {
		if(productRepository.findById(newProduct.getProductId()).orElse(null) != null) {
			throw new Exception("Product already exist"); 
		}
		if(productImage != null) {
			fileService.store(productImage);
		}
		List<Color> colors = newProduct.getColors();
		for(Color c : colors){
			Color color = colorRepository.findById(c.getColorId()).orElse(null);
			color.getProducts().add(newProduct);
		}
		productRepository.save(newProduct);
		return newProduct;
	}
	
	@PutMapping("/{id}")
	public Product update(@RequestParam(value = "image", required = false) MultipartFile productImage,@PathVariable int id,@RequestPart Product newProduct) {
		Product p = productRepository.findById(id).orElse(null);
		p.setProductCost(newProduct.getProductCost());
		p.setProductDate(newProduct.getProductDate());
		p.setProductName(newProduct.getProductName());
		p.setProductDescription(newProduct.getProductDescription());
		List<Color> oldColor = p.getColors();
		for(Color oc : oldColor){
			Color o = colorRepository.findById(oc.getColorId()).orElse(null);
			o.getProducts().remove(p);
		}
		List<Color> newColor = newProduct.getColors();
		for(Color nc : newColor){
			Color n = colorRepository.findById(nc.getColorId()).orElse(null);
			n.getProducts().add(p);
		}
		if(productImage != null) {
			fileService.delete(p.getImage());
			p.setImage(newProduct.getImage());
			fileService.store(productImage);
		}
		productRepository.saveAndFlush(p);
		return newProduct;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		Product p = productRepository.findById(id).orElse(null);
		fileService.delete(p.getImage());
		p.getColors().clear();
		productRepository.deleteById(id);
		productRepository.flush();
	}
	
	@GetMapping("/colors")
	public List<Color> colors() {
		return colorRepository.findAll();
	}
	
	@GetMapping("/colors/{id}")
	public Color color(@PathVariable int id) {
		return colorRepository.findById(id).orElse(null);
	}
	
	@GetMapping("/brands")
	public List<Brand> brands() {
		return brandRepository.findAll();
	}
	
	@GetMapping("/productcolors")
	public List<ProductColor> productColors() {
		return productColorRepository.findAll();
	}
	
}
