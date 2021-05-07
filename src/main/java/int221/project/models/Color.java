package int221.project.models;

import java.util.ArrayList;
import java.util.List;
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
@Table(name = "color")
public class Color implements Comparable<Color> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid", nullable = false)
	private Integer colorId;
	
	@Column(name = "cname", nullable = false, length = 10)
	private String colorName;
	
//	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
//    @JoinTable(name = "productcolor",
//            joinColumns = { @JoinColumn(name = "cid") },
//            inverseJoinColumns = { @JoinColumn(name = "proid") })
//	private List<Product> products;

	@Override
	public int compareTo(Color other) {
		return this.colorId-other.colorId;
	}
}
