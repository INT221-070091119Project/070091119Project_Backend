package int221.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int221.project.models.ProductColor;
import int221.project.models.ProductColorPK;

public interface ProductColorRepository extends JpaRepository<ProductColor, ProductColorPK> {
	
}

