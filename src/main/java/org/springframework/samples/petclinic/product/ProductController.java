/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.product;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
class ProductController {

	private final ProductRepository products;

	public ProductController(ProductRepository clinicService) {
		this.products = clinicService;
	}

	@GetMapping("/productsList")
	public String showVetList(Map<String, Object> model) {
		model.put("catalog", this.products.findAll());
		return "products/productsList";
	}

	@GetMapping("/productsForm")
	public String showForm(Product product) {
		return "products/form";
	}

	@PostMapping("/productsForm")
	public String checkProductInfo(@Valid Product product, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "products/form";
		} else {
			this.products.save(product);
			return "redirect:/productsForm";
		}
	}

}
