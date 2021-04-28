package int221.project.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"productColorId"})
public class ProductColor {
	
	@Id
	@Column(name = "pc_id", nullable = false, length = 5)
	private String productColorId;
	
	@ManyToOne
	@MapsId("productId")
    @JoinColumn(name = "proid", referencedColumnName = "proid")
	@JsonIgnore
	private Product product;
	
	@ManyToOne
	@MapsId("colorId")
    @JoinColumn(name = "cid", referencedColumnName = "cid")
	private Color color;

}
