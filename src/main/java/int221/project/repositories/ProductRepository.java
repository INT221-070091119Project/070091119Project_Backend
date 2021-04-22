package int221.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int221.project.models.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
}
