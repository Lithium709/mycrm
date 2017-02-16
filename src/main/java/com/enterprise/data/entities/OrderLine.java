package com.enterprise.data.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Embeddable
public class OrderLine implements Serializable {

	@ManyToOne
	private Order order;

	private Long lineNo;
	
	@OneToOne
	private Product product;
	
	private Float quantity;
	
	private Float price;
	
	private Float vat;
	
	private Float total;
	
}

