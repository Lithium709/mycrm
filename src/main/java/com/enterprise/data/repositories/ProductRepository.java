package com.enterprise.data.repositories;

import java.util.UUID;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.enterprise.data.entities.Product;
import com.enterprise.data.entities.QProduct;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;



public interface ProductRepository extends PagingAndSortingRepository<Product, UUID>,
QueryDslPredicateExecutor<Product>,QuerydslBinderCustomizer<QProduct>
{

	@Override
	default void customize(QuerydslBindings bindings, QProduct product) {

		bindings.bind(product.productNumber).first(new SingleValueBinding<StringPath, String>() {

			@Override
			public Predicate bind(StringPath path, String value) {
				return path.contains(value);
			}
		});
		
		
		bindings.bind(product.name).first(new SingleValueBinding<StringPath, String>() {

			@Override
			public Predicate bind(StringPath path, String value) {
				return path.toUpperCase().contains(value.toUpperCase());
			}
		});

	}
}
