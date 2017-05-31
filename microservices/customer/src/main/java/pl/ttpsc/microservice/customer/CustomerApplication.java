package pl.ttpsc.microservice.customer;

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

//http://localhost:8081/getCustomer?id=1

@SpringBootApplication
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}

@Data
class Customer implements Serializable {
    private int id;
    private String name;
    private String email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

@Controller
class CustomerService {

    static List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer(0, "Chris", "chris@localhost"));
        customers.add(new Customer(1, "Alan", "alan@localhost"));
        customers.add(new Customer(2, "Pawel", "pablo15@localhost"));
    }

    @RequestMapping("/getCustomers")
    public @ResponseBody
    List<Customer> getCustomers() {
        return customers;
    }

    @RequestMapping("/getCustomer")
    public @ResponseBody
    Customer getCustomer(@QueryParam("id") int id) {
        return customers.get(id);
    }
}