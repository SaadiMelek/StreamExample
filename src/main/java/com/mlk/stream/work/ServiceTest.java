package com.mlk.stream.work;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mlk.stream.work.Product;;

@Component
public class ServiceTest {

	@Autowired
	ProductRepository productRepository;

	public List<Float> filterSalaryGreaterThan25000() {
		
		productRepository.findDistinctByIdIsNotNull().forEach(p -> System.err.println(p));
		
		// filtering data of list
		List<Float> productPriceList = productRepository.findAll()
				.stream()
				.filter((product) -> product.getPrice() > 25000)
				.map(product -> product.getPrice())
				.collect(Collectors.toList());
		// displaying data
		productPriceList.forEach(price -> System.out.println(price));
		return productPriceList;
	}

	public Float sumPrices() {
		// Using Collectors's method to sum the prices.
		Double totalPrice3 = productRepository.findAll()
				.stream()
				.collect(Collectors.summingDouble(product -> product.getPrice()));
		System.out.println(totalPrice3);
		return totalPrice3.floatValue();
	}

	public Product maxProductPrice() {
		// max() method to get max Product price
		Product productA = productRepository.findAll()
				.stream()
				.max((product1, product2) -> product1.getPrice() > product2.getPrice() ? 1 : -1)
				.get();
		System.out.println(productA.getPrice());
		return productA;
	}

	public Set<Product> convertProductListIntoSet() {
		// Converting product List into Set
		Set<Product> productsSet = productRepository.findAll()
				.stream()
				// .filter(product -> product.getPrice() < 30000)
				// .map(product -> product.getPrice())
				.collect(Collectors.toSet());
		System.out.println(productsSet);
		return productsSet;
	}

	public Map<Integer, String> convertProductListIntoMap() {
		// Converting Product List into a Map
		Map<Integer, String> productPriceMap = productRepository.findAll()
				.stream()
				.collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));
		System.out.println(productPriceMap);
		return productPriceMap;
	}
	
	public Product findAnySonyOrElseNull() {
		// findAny OrElse : get Sony Laptop
		Product productSony = productRepository.findAll()
				.stream()
				.filter(x -> x.getName().contains("SONY"))
				.findAny()
				.orElse(null);
		System.out.println(productSony.getPrice());
		return productSony;
	}
	
	public Set<Product> convertProductNamesToLowercase() {
		// Convert String Name To Lowercase
		Set<Product> productsSet = productRepository.findAll()
				.stream()
				.map(p-> {
					p.setName(p.getName().toLowerCase());
					return p;
				})
				.collect(Collectors.toSet());
				
		System.out.println(productsSet);
		return productsSet;
	}
	
	public Map<Float, Long> countingProductsGroupingByPrice() {
		Map<Float, Long> countingGroupingBy = productRepository.findAll()
				.stream()
				.collect(Collectors.groupingBy(Product::getPrice, Collectors.counting()));
		return countingGroupingBy;
	}

}
