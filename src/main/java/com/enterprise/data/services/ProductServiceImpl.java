package com.enterprise.data.services;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.data.dto.ProductDTO;
import com.enterprise.data.entities.Product;
import com.enterprise.data.repositories.ProductRepository;
import com.querydsl.jpa.EclipseLinkTemplates;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.sql.JPASQLQuery;

@Service
public class ProductServiceImpl implements ProductService{

	
	@Autowired
	ProductRepository productRepository;


	@Autowired
	private SessionFactory sessionFactory;

	private static UUID getGuidFromByteArray(byte[] bytes) {
		if(bytes == null){
			return null;
		}
	    ByteBuffer bb = ByteBuffer.wrap(bytes);
	    long high = bb.getLong();
	    long low = bb.getLong();
	    UUID uuid = new UUID(high, low);
	    return uuid;
	}
	
	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public List<ProductDTO> findAllHierarchy(){
	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		ProcedureCall storecall = session.createStoredProcedureCall("product_hierarchy");
		ProcedureOutputs procedureResult = storecall.getOutputs();
		ResultSetOutput currentOutput = (ResultSetOutput) procedureResult.getCurrent();
		List resultSet = currentOutput.getResultList();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Object object : resultSet) {
			UUID id = getGuidFromByteArray((byte[]) ( (Object[]) object )[0]);
			UUID parent_id = getGuidFromByteArray((byte[]) ( (Object[]) object )[1]);
			String name = (String) ( (Object[]) object )[2];
			int level = (Integer) ( (Object[]) object )[3];
			
			productDTOs.add(new ProductDTO(id, name, level, parent_id));
		}
		session.getTransaction().commit();
		session.close();
		
		
    	Map<UUID, Integer> map = new HashMap<UUID, Integer>();
    	List<ProductDTO> roots = new ArrayList<ProductDTO>();
    	for ( Integer i = 0; i < productDTOs.size(); i += 1) {
    		ProductDTO node = productDTOs.get(i);
    	    node.children = new ArrayList<ProductDTO>();
    	    map.put(node.getId(), i);
    	    if (node.getParent_id() != null) {
    	    	productDTOs.get(map.get(node.getParent_id())).children.add(node);
    	    } else {
    	        roots.add(node);
    	    }
    	}

		
		return roots;
		
	}
}
