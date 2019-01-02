package com.mlk.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.mlk.stream.work.Product;
import com.mlk.stream.work.ProductRepository;
import com.mlk.stream.work.Tests;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class Java8StreamTestUApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	Tests tests;

	@Before
	public void setUp() {
		// productRepository.findAll().stream().forEach(p -> System.out.println(p));
	}

	@Test
	public void filterSalaryGreaterThan25000_test() {
		List<Float> productPriceList = tests.filterSalaryGreaterThan25000();
		assertNotNull(productPriceList);
		assertEquals(productPriceList.size(), 4);
		productPriceList.forEach(f -> System.out.println(f));

	}

	@Test
	public void sumPrices_test() {
		Float totalPrice3 = tests.sumPrices();
		assertNotNull(totalPrice3);
		assertEquals(totalPrice3, new Float(201000.0f));
	}

	@Test
	public void maxProductPrice_test() {
		// max() method to get max Product price
		Product maxProductPrice = tests.maxProductPrice();
		Product apple = productRepository.findByName("Apple Laptop");
		assertEquals(maxProductPrice, apple);
		System.out.println(maxProductPrice);
	}
	
	@Test
	public void convertProductListIntoSet_test() {
		// Converting product List into Set
		Set<Product> productsSet = tests.convertProductListIntoSet();
		assertTrue(productsSet instanceof Set);
	}
	
	@Test
	public void convertProductListIntoMap_test() {
		// Converting Product List into a Map
		Map<Integer, String> productPriceMap = tests.convertProductListIntoMap();
		assertTrue(productPriceMap instanceof Map);
	}
}
