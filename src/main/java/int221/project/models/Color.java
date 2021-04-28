package int221.project.models;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"products"})
public class Color {
	@Id
	@Column(name = "cid", nullable = false, length = 5)
	private String colorId;
	
	@Column(name = "cname", nullable = false, length = 10)
	private String colorName;
	
	@OneToMany(mappedBy = "color")
	private Set<ProductColor> products = new TreeSet<>();
}
