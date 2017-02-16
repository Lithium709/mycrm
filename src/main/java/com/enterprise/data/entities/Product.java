package com.enterprise.data.entities;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Product {

	@Id 
	private UUID id;
	private String name;
	private String productNumber;
	private Boolean folder;
	
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	protected Product parent;
		
}
