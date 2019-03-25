package com.mlk.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
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
import com.mlk.stream.work.ServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class Java8StreamTestUApplicationServiceTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ServiceTest serviceTest;

	@Before
	public void setUp() {
		// productRepository.findAll().stream().forEach(p -> System.out.println(p));
	}

	@Test
	public void filterSalaryGreaterThan25000_test() {
		List<Float> productPriceList = serviceTest.filterSalaryGreaterThan25000();
		assertNotNull(productPriceList);
		assertEquals(productPriceList.size(), 2);
		productPriceList.forEach(f -> System.out.println(f));

	}

	@Test
	public void sumPrices_test() {
		Float totalPrice3 = serviceTest.sumPrices();
		assertNotNull(totalPrice3);
		assertEquals(totalPrice3, new Float(130000.0f));
	}

	@Test
	public void maxProductPrice_test() {
		// max() method to get max Product price
		Product maxProductPrice = serviceTest.maxProductPrice();
		Product apple = productRepository.findByName("APPLE LAPTOP");
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
		Set<Product> productsSet = serviceTest.convertProductListIntoSet();
		// just convert SET to LIST to compare equality
		List<Product> productsSetToList = new ArrayList<>(productsSet);
		assertTrue(productsSetToList.equals(productRepository.findAll()));
	}

	@Test
	public void convertProductListIntoMap_test() {
		// Converting Product List into a Map
		Map<Integer, String> productPriceMap = serviceTest.convertProductListIntoMap();
		assertTrue(productPriceMap instanceof Map);
	}
	
	@Test
	public void findAnySonyOrElseNull_test() {
		// findAny (also findFirst) OrElse : get Sony Laptop
		Product productSony = serviceTest.findAnySonyOrElseNull();
		assertTrue(productSony.equals(productRepository.findByName("SONY LAPTOP")));
	}
	
	@Test
	public void convertProductNamesToLowercase_test() {
		// Convert Product Names To Lowercase
		Set<Product> productsSet = serviceTest.convertProductNamesToLowercase();
		productsSet.stream().forEach(p-> {
			final String productNameUppercase = p.getName().toUpperCase();
			final Product product = productRepository.findByName(productNameUppercase);
			assertTrue(product.getName().equals(productNameUppercase));
		});
	}
	
	@Test
	public void countingProductsGroupingByPrice_test() {
		// Counting Products Grouping By Price
		Map<Float, Long> countingGroupingByPrice = serviceTest.countingProductsGroupingByPrice();
		OptionalLong count = countingGroupingByPrice.entrySet()
			.stream()
			.filter(e -> e.getKey().equals(25000.0f))
			.mapToLong(e -> e.getValue())
			.findFirst();
		assertTrue(count.getAsLong() == 2);
	}
}
