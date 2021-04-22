package int221.project.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private String proId;
	private String proName;
	private double proCost;
	private String proDescription;
	@Basic
    private java.sql.Date proDate;
	private String bId;
	
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public double getProCost() {
		return proCost;
	}
	public void setProCost(double proCost) {
		this.proCost = proCost;
	}
	public String getProDescription() {
		return proDescription;
	}
	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}
	public java.sql.Date getProDate() {
		return proDate;
	}
	public void setProDate(java.sql.Date proDate) {
		this.proDate = proDate;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	
}
