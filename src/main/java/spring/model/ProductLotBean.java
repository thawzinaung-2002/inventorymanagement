package spring.model;

import lombok.Data;

@Data
public class ProductLotBean {

	private String name;
	private Integer quantity;
	private String uom;
	private Double price;
	private String category;
	private String expired;
	private String lot;
	
}
