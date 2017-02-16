package com.enterprise.data.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductDTO {

	private UUID id;
	private String name;
	private int level;
	private UUID parent_id;
	private String path;
	public List<ProductDTO> children;
	
	
	public ProductDTO(UUID id, String name, int level, UUID parent_id) {
		this.id = id;
		this.name = name;
		this.level = level;
		this.parent_id = parent_id;
	}
}
