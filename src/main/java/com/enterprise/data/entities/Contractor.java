package com.enterprise.data.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Contractor {

	@Id
	private UUID id;
	
	private String name;
	
}
