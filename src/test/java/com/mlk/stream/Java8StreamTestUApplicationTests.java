package com.mlk.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		// Converting product List into Set :
		// the hash process on hashCode() method is based on product ID
		// so we will get the SET of products ordered by ID
		// this is what we need : because list.equal(otherList) is based on
		// equality between objects in the index i
		Set<Product> productsSet = tests.convertProductListIntoSet();
		// just convert SET to LIST to compare equality
		List<Product> productsSetToList = new ArrayList<>(productsSet);
		assertTrue(productsSetToList.equals(productRepository.findAll()));
	}

	@Test
	public void convertProductListIntoMap_test() {
		// Converting Product List into a Map
		Map<Integer, String> productPriceMap = tests.convertProductListIntoMap();
		assertTrue(productPriceMap instanceof Map);
	}
}
