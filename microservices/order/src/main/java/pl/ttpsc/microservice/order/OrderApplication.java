package pl.ttpsc.microservice.order;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.Date;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}

//http://localhost:8083/order?idCustomer=2&idProduct=2&amount=4
@Controller
class OrderService {
	private long id = 1;

	@RequestMapping("order")
	public @ResponseBody Order submitOrder(@QueryParam("idCustomer") long idCustomer,
											@QueryParam("idProduct") long idProduct,
											@QueryParam("amount") long amount) {
		Order order = new Order();

		RestTemplate restTemplate = new RestTemplate();
		Customer customer = restTemplate.getForObject(
				"http://localhost:8081/getCustomer?id={idCustomer}", Customer.class,
		idCustomer);
		Product product = restTemplate.getForObject(
				"http://localhost:8082/getProduct?id={idProduct}", Product.class,
		idProduct);
		order.setCustomer(customer);
		order.setProduct(product);
		order.setId(id);
		order.setAmount(amount);
		order.setDateOrder(new Date());
		id++;
		return order;
	}
}

class Order implements Serializable {
	private long id;
	private long amount;
	private Date dateOrder;
	private Customer customer;
	private Product product;

	public Order(){}

	public Order(long id, long amount, Date dateOrder, Customer customer, Product product) {
		this.id = id;
		this.amount = amount;
		this.dateOrder = dateOrder;
		this.customer = customer;
		this.product = product;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;}
	public Date getDateOrder() {
		return dateOrder;
	}
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}

@Data
class Product implements Serializable {

	private int id;
	private String name;
	private Double price;

	public Product(){}
	public Product(int id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
}

@Data
class Customer implements Serializable {
	private int id;
	private String name;
	private String email;

	public Customer(){}
	public Customer(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
}