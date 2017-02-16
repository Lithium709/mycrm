package com.enterprise.data;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.SQLServer2012Dialect;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.core.db.dialect.MsSQLDialect;

import com.enterprise.data.dto.ProductDTO;
import com.enterprise.data.entities.Product;
import com.enterprise.data.repositories.ProductRepository;
import com.enterprise.data.services.ProductService;
import com.querydsl.jpa.EclipseLinkTemplates;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.sql.JPASQLQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnterprisedApplicationTests {

	
	@Autowired
	private ProductService productService;

	
	@Test
	public void contextLoads() {
		
	}
	
	

	@Test
	public void fethesHierarchy() throws Exception {
	
		
		System.out.println(productService.findAllHierarchy());
		
	}	

}
