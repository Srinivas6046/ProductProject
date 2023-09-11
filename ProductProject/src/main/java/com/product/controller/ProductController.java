package com.product.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.entity.Products;
import com.product.repository.ProductRepository;


@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	public static Logger logger=null;
@GetMapping("/")
	public String home(Model m) {
		/*
		 * logger=Logger.getLogger(ProductController.class);
		 * logger.info("It went to controller class"); List<Products>
		 * list=productRepository.findAll(); m.addAttribute("all_products", list);
		 */
		return findPaginationAndSorting(0,"id","asc",m);
	}


@GetMapping("/page/{pageNo}")
public String findPaginationAndSorting(@PathVariable(value="pageNo") int pageNo, 
		@RequestParam(value= "sortField" ,required = false) String sortField,
		@RequestParam(value= "sortDir", required = false) String sortDir,
		Model m) {
	
	

Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
		: Sort.by(sortField).descending();
	
	
	
	Pageable pageable=PageRequest.of(pageNo, 3,sort);
	
	Page<Products> page=productRepository.findAll(pageable);
	 List<Products> list=page.getContent();
	m.addAttribute("pageNo", pageNo);
	m.addAttribute("totalElements", page.getTotalElements());
	m.addAttribute("totalPages", page.getTotalPages());
	m.addAttribute("all_products", list);
	
	
	m.addAttribute("sortField", sortField);
	m.addAttribute("sortDir", sortDir);
	m.addAttribute("revSortDir", sortDir.equals("asc") ? "desc" : "asc");
	System.out.println("pagenation controller");
	
	
	return "index";
}




@GetMapping("/save")
public String saveForm() {
	logger.info("Here we need to add products");
	return "addProduct";
}

@GetMapping("/edit/{id}")
public String editForm(@PathVariable(value="id") long id,Model m) {
	//logger.info("Here we need to edit products");
	Optional<Products> product=productRepository.findById(id);
	Products pro=product.get();
m.addAttribute("product", pro);
return "editProduct";
}
	
@PostMapping("/saveProducts")
public String saveProduct(@ModelAttribute Products products,HttpSession session) {
	//logger.info("it will going to create products");
	productRepository.save(products);
	session.setAttribute("message", "Product Added  '"+products.getId()+"' successully");
	logger.info("product '"+products.getId()+"' added successfully");
	return "redirect:/save";
	
}



@PostMapping("/updateProducts")
public String updateProduct(@ModelAttribute Products products,HttpSession session) {
//logger.info("it will going to create products");
productRepository.save(products);
session.setAttribute("message", "Product edited '"+products.getId()+"' successully");
//logger.info("product '"+products.getId()+"' edit successfully");
return "redirect:/";

}

@GetMapping("/delete/{id}")
public String deleteProduct(@PathVariable(value="id") long id,HttpSession session) {
	//logger.info("will plan to delete products here");
	productRepository.deleteById(id);
	session.setAttribute("message", "product deleted  '"+id+"' successfully");
	return "redirect:/";
}
}








