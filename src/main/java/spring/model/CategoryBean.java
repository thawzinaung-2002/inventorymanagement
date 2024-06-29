package spring.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryBean {
	
	private String id;
	private String name;
	private String description;

}
