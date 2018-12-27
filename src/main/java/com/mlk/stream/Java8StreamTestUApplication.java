package com.mlk.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mlk.stream.work.Product;
import com.mlk.stream.work.ProductRepository;

@SpringBootApplication
public class Java8StreamTestUApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Java8StreamTestUApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Adding Products
		productRepository.save(new Product(1, "HP Laptop", 25000f));
		productRepository.save(new Product(2, "Dell Laptop", 30000f));
		productRepository.save(new Product(3, "Lenevo Laptop", 28000f));
		productRepository.save(new Product(4, "Sony Laptop", 28000f));
		productRepository.save(new Product(5, "Apple Laptop", 90000f));

		productRepository.findAll().stream().forEach(p -> System.out.println(p));
	}

}
