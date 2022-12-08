package mypackage.model;

import javax.persistence.*;

@Entity
@Table(name="FOODITEMS")
public class Fooditem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false, unique=true)
	private Integer id;
	
	@Column(name="name", length=200, nullable=false)
	private String name;
	
	@Column(name="category_id", nullable=false)
	private Integer category_id;
	
	@Column(name="active", nullable=false)
	private Boolean active;
	
	@Column(name="description", length=100, nullable=true)
	private String description;
	
	@Column(name="price", precision=10, scale=2, nullable=false)
	private Double price;
	
	public Fooditem() {
		
	}
	
	public Fooditem(String name, Integer category_id, Boolean active, Double price, String description) {
		this.name = name;
		this.category_id = category_id;
		this.active = active;
		this.price = price;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCategoryId() {
		return category_id;
	}
	
	public void setCategoryId(Integer category_id) {
		this.category_id = category_id;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
