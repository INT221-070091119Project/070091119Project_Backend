package int221.project.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ProductColorPK implements Serializable {
	
	static final long serialVersionUID = 1L;

	@Column(name = "proid")
	private int productId;
	
	@Column(name = "cid")
	private int colorId;
}
