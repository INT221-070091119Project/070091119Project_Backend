package int221.project.models;

import java.io.Serializable;
import java.util.*;
import lombok.*;
import lombok.ToString.Exclude;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proid", nullable = false)
	private int productId;
	
	@Column(name = "proname", nullable = false, length = 50)
	private String productName;
	
	@Column(name = "procost", precision = 7, scale = 2)
	private double productCost;
	
	@Column(name = "prodescription", length = 400)
	private String productDescription;
	
	@Basic
	@Column(name = "prodate", length = 400)
	@JsonFormat(pattern="yyyy-MM-dd")
    private java.sql.Date productDate;
	
	@Column(name = "proimage")
	private String productImageName;
	
	@ManyToOne
	@JoinColumn(name = "bid", referencedColumnName = "bid")
	private Brand brand;
	
	@OneToMany(mappedBy = "product")
	private Set<ProductColor> colors = new TreeSet<>();

}
