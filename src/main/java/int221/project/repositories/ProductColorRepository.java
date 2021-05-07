package int221.project.repositories;

import int221.project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import int221.project.models.ProductColor;
import int221.project.models.ProductColorPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ProductColorRepository extends JpaRepository<ProductColor, ProductColorPK> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ProductColor pc WHERE pc.product.productId = :id")
    void deleteByProductId(@Param("id") Integer id);
}

