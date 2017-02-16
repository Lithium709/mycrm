package com.enterprise.data.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity(name="CLIENT_ORDER")
public class Order {
	
	@Id
	private UUID id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Date date;
	
	private String number;
	
	private boolean deletionMark;
	
	private boolean posted;
	
	@OneToOne
	private Contractor buyer;
	
	@ElementCollection @OrderColumn
	private List<OrderLine> orderLines  = new ArrayList<OrderLine>();
	
}
