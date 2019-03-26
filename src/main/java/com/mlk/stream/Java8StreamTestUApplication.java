package com.mlk.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mlk.stream.work.Product;
import com.mlk.stream.work.ProductRepository;

@SpringBootApplication
public class Java8StreamTestUApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;
	
	@Value("${app.message}")
	String appMsg;

	public static void main(String[] args) {
		SpringApplication.run(Java8StreamTestUApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("appMsg = " + appMsg);
		// Adding Products
		productRepository.save(new Product(1, "HP LAPTOP", 25000f));
		productRepository.save(new Product(2, "DELL LAPTOP", 30000f));
		productRepository.save(new Product(3, "LENOVO LAPTOP", 25000f));
		productRepository.save(new Product(4, "SONY LAPTOP", 20000f));
		productRepository.save(new Product(5, "APPLE LAPTOP", 30000f));

		productRepository.findAll().stream().forEach(p -> System.out.println(p));
	}

}
