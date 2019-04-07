package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/products")

	public String getProducts(Model model) {

		List<Product> products = productService.getProducts();// products메소드를 호출해서 product를 가져옴 현재 list 형태
		model.addAttribute("products", products);
		return "products";

		// 이것을 모델에 넣어줌 (키값, value) view's logical name
	}

	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable int productId, Model model) { // productId에 해당되는걸 조회해야

		Product product = productService.getProductById(productId); // id를 주어서 새롭게 하나의 product를조회를 함.
		model.addAttribute("product", product);

		return "viewProduct"; // viewproduct는 36번줄 정보를 바탕으로 해서 화면을 구성해야함.
	}

}
