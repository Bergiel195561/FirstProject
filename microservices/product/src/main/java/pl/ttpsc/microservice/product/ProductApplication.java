package pl.ttpsc.microservice.product;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//http://localhost:8082/getProduct?id=2

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}

@Data
class Product implements Serializable {

	private int id;
	private String name;
	private Double price;

	public Product(int id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
}

@Controller
class ProductService {

	static List<Product> productList = new ArrayList<>();

	static {
		productList.add(new Product(0, "apples", 4.5));
		productList.add(new Product(1, "oranges", 3.5));
		productList.add(new Product(2, "lemon", 1.5));
		productList.add(new Product(3, "banana", 1.0));
	}

	@RequestMapping("getProducts")
	public @ResponseBody  List<Product> getProducts() {
		return productList;
	}

	@RequestMapping("getProduct")
	public @ResponseBody  Product getProduct(@QueryParam("id") int id) {
		return productList.get(id);
	}
}
