package com.enterprise.data.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.enterprise.data.services.ProductService;

@Controller
public class ProductController {

	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/products")
	public String products(Model model, @PageableDefault(sort = {"productNumber"}, value =50) Pageable pageable) {
		model.addAttribute("groups", productService.findAllHierarchy());
		model.addAttribute("products", productService.findAll(pageable));
		return "products";
	}
}
