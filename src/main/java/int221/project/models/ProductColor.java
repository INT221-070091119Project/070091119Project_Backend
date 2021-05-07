package int221.project.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "productcolor")
@JsonIgnoreProperties(value = {"productColorId"})
public class ProductColor {
	
	@EmbeddedId
	private ProductColorPK productColorId;
	
	@ManyToOne(optional = false)
	@MapsId("productId")
	@JoinColumn(name = "proid")
	@JsonBackReference
	private Product product;
	
	@ManyToOne(optional = false)
	@MapsId("colorId")
	@JoinColumn(name = "cid")
	private Color color;
}
