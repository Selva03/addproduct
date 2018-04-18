package com.product.Addproduct.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.product.Addproduct.model.Addproduct;
import com.product.Addproduct.repository.AddproductRepository;


@RestController
@RequestMapping("/api")
public class AddproductController {

	 @Autowired
	 AddproductRepository addproductRepository;
	 
	 @GetMapping("/getproduct")
	    public List<Addproduct> getAllAddproduct() {
	        return addproductRepository.findAll();
	    }
	 
	  @GetMapping("/getproduct/{id}")
	    public ResponseEntity<Addproduct> getAddproductById(@PathVariable(value = "id") Long productId) {
		  Addproduct addproduct = addproductRepository.findOne(productId);
	        if(addproduct == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(addproduct);
	    }

	
	  @PostMapping("/addproduct")
	    public Addproduct createProduct(@Valid @RequestBody Addproduct addproduct) {
	        return addproductRepository.save(addproduct);
	    }

	    @PutMapping("/addproduct/{id}")
	    public ResponseEntity<Addproduct> updateProduct(@PathVariable(value = "id") Long productId,
	                                           @Valid @RequestBody Addproduct productDetails) {
	    	Addproduct addproduct = addproductRepository.findOne(productId);
	        if(addproduct == null) {
	            return ResponseEntity.notFound().build();
	        }
	        addproduct.setProductName(productDetails.getProductName());
	        addproduct.setProductType(productDetails.getProductType());

	        Addproduct updatedProduct = addproductRepository.save(addproduct);
	        return ResponseEntity.ok(updatedProduct);
	    }
	
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Addproduct> deleteProduct(@PathVariable(value = "id") Long productId) {
	    	Addproduct addproduct = addproductRepository.findOne(productId);
	        if(addproduct == null) {
	            return ResponseEntity.notFound().build();
	        }

	        addproductRepository.delete(addproduct);
	        return ResponseEntity.ok().build();
	    }
}
