package test.integration.bookstore.services;

import javax.jws.WebService;

import bookstore.Product;

@WebService
public interface ProductTest {

	Product testProduct(Product product);

}
