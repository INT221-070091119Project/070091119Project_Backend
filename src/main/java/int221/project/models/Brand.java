package int221.project.models;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class Brand {
	@Id
	@Column(name = "bid", nullable = false, length = 5)
	private String brandId;
	
	@Column(name = "bname", nullable = false, length = 30)
	private String brandName;
	
}
