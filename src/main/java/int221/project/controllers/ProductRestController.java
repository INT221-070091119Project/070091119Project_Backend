package int221.project.controllers;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import int221.project.models.*;
import int221.project.repositories.*;
import int221.project.service.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepository;
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
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String name) {
		Resource file = (Resource) fileService.loadAsResource(name);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
	}

	@PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product create(@RequestParam(value = "image", required = false) MultipartFile productImage,@RequestPart Product newProduct) throws Exception {

		if(productImage != null) {
			newProduct.setImage(fileService.save(productImage,newProduct.getProductName()));
		}
		addProductColorPk(newProduct);
		return productRepository.saveAndFlush(newProduct);
	}
	
	@PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Product update(@RequestParam(value = "image", required = false) MultipartFile productImage,@PathVariable int id,@RequestPart Product newProduct) {
		Product p = productRepository.findById(id).orElse(null);
		editProduct(p,newProduct);
		if(productImage != null) {
			fileService.delete(p.getImage());
			p.setImage(fileService.save(productImage,p.getProductName()));
		}
		return productRepository.saveAndFlush(p);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		Product p = productRepository.findById(id).orElse(null);
		fileService.delete(p.getImage());
		productRepository.deleteById(id);
		productRepository.flush();
	}
	private void addProductColorPk(Product product){
		for(ProductColor pc : product.getProductColors()){
			pc.setProductColorId(new ProductColorPK(product.getProductId(),pc.getColor().getColorId()));
			pc.setProduct(product);
		}
	}

	private void editProduct(Product old,Product p){
		old.setProductCost(p.getProductCost());
		old.setProductDate(p.getProductDate());
		old.setProductName(p.getProductName());
		old.setProductDescription(p.getProductDescription());
		old.setBrand(p.getBrand());
		productColorRepository.deleteByProductId(old.getProductId());
		old.setProductColors(p.getProductColors());
		addProductColorPk(old);
	}

	
}
