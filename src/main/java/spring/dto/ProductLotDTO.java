package spring.dto;

import lombok.Data;

@Data
public class ProductLotDTO {

	private String name;
	private Integer quantity;
	private String uom;
	private Double price;
	private String category;
	private String expired;
	private String lot;
	
}
