package com.example.product;

import com.example.product.controller.ProductController;
import com.example.product.entities.Product;
import com.example.product.repositories.ProductRepository;
import com.example.product.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductApplicationControllerTests {

//	private static final String PRODUCT_ID = "1";
	private ProductController productController;
	@Mock
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void setUp(){
		this.productService = new ProductService(productRepository);
		this.productController = new ProductController(productService);
	}

	@Test
	public void testGetAllProducts_WithContent(){
		List<Product> expectedProducts = Arrays.asList(new Product());

		Mockito.when(productRepository.findAll()).thenReturn(expectedProducts);
		ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(responseEntity.getBody(), expectedProducts);
		Mockito.verify(productRepository, times(1)).findAll();
	}

	@Test
	public void testGetAllProducts_NoContent(){
		Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());
		ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		Mockito.verify(productRepository, times(1)).findAll();
	}

	@Test
	public void testGetProductById_ExistingProducts() {
		long id = 1;
		Product expectedProduct = new Product();
		expectedProduct.setId(1);
		expectedProduct.setName("Test Product");

		Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(expectedProduct));
		ResponseEntity<Product> responseEntity = productController.getProductById(id);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(responseEntity.getBody(), expectedProduct);
		Mockito.verify(productRepository, times(1)).findById(id);
	}

	@Test
	public void testGetProductById_NonExistentProduct(){
		long id = 1;

		Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());
		ResponseEntity<Product> responseEntity = productController.getProductById(id);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
		Mockito.verify(productRepository, times(1)).findById(id);
	}

//	@Test
//	public void testGetProductsGroupedByBrand_WithContent(){
//		String brandA = "brandA";
//		String brandB = "brandB";
//		Map<String, List<Product>> expectedGroupedProducts = new HashMap<>();
//		List<Product> productsBrandA = Arrays.asList(new Product(), new Product());
//		List<Product> productsBrandB = Collections.singletonList(new Product());
//		expectedGroupedProducts.put(brandA, productsBrandA);
//		expectedGroupedProducts.put(brandB, productsBrandB);
//
//		Mockito.when(productService.getGroupedProductsByBrand()).thenReturn(expectedGroupedProducts);
//
//		ResponseEntity responseEntity = productController.getProductsGroupedByBrand();
//
//		assertNotNull(responseEntity);
//		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//		assertEquals(responseEntity.getBody(), expectedGroupedProducts);
//		Mockito.verify(productService, times(1)).getGroupedProductsByBrand();
//	}

	@Test
	public void testGetProductByBrand_ExistingBrand(){
		String brand = "Brand";
		List<Product> expectedProducts = Arrays.asList(new Product(), new Product());

		Mockito.when(productRepository.findByBrand(brand)).thenReturn(expectedProducts);
		ResponseEntity<List<Product>> responseEntity = productController.getProductsByBrand(brand);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(responseEntity.getBody(), expectedProducts);
		Mockito.verify(productRepository, times(1)).findByBrand(brand);
	}

	@Test
	public void testGetProductByBrand_NonExistentBrand(){
		String brand = "BrandX";

		Mockito.when(productRepository.findByBrand(brand)).thenReturn(Collections.emptyList());
		ResponseEntity<List<Product>> responseEntity = productController.getProductsByBrand(brand);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		Mockito.verify(productRepository, times(1)).findByBrand(brand);
	}

	@Test
	public void TestGetProductsByPriceRange(){
		double minPrice = 300.00;
		double maxPrice = 1000.00;
		List<Product> expectedProducts = Arrays.asList(new Product(), new Product());

		Mockito.when(productRepository.findByPriceRange(minPrice, maxPrice)).thenReturn(expectedProducts);
		ResponseEntity<List<Product>> responseEntity = productController.getProductsByPriceRange(minPrice, maxPrice);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(responseEntity.getBody(), expectedProducts);
		Mockito.verify(productRepository, times(1)).findByPriceRange(minPrice, maxPrice);
	}
}
