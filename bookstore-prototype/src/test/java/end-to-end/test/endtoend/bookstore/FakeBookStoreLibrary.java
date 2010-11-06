package test.endtoend.bookstore;

import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomerWithAddressesAndOpenBalanceOfFive;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;
import static test.endtoend.bookstore.builder.ProductBuilder.aProductProvidedByAustriaSupplier;
import static test.endtoend.bookstore.builder.ProductBuilder.aProductProvidedByGermanSupplier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bookstore.BookstoreLibrary;
import bookstore.Customer;
import bookstore.Product;

public class FakeBookStoreLibrary implements BookstoreLibrary {

	private static final String GERMAN_SUPPLIER_ADDRESS = "http://localhost:9000/germansupplier";
	private static final String AUSTRIA_SUPPLIER_ADDRESS = "http://localhost:9000/austriasupplier";

	private class ProductInformation {
		ProductInformation(String id, boolean isAvailable, int deliveryTimeInDays) {
			this.id = id;
			this.isAvailable = isAvailable;
			this.deliveryTimeInDays = deliveryTimeInDays;
		}

		String id;
		boolean isAvailable;
		int deliveryTimeInDays;
	}

	private Map<String, Customer> customers = new HashMap<String, Customer>();
	private Map<String, List<Product>> warehouseProducts = new HashMap<String, List<Product>>();
	private Map<String, ProductInformation> warehouseProductInformation = new HashMap<String, ProductInformation>();
	private Map<String, String> supplierAddressesForProducts = new HashMap<String, String>();
	private Map<String, List<Product>> supplierAustria = new HashMap<String, List<Product>>();
	private Map<String, List<Product>> supplierGermany = new HashMap<String, List<Product>>();

	public FakeBookStoreLibrary() {
		createTestCustomers();
		createTestProdcutsForWarehouse();
		createProductsForSuppliers();
	}

	void createTestCustomers() {
		Customer aCustomer = aCustomerWithAddressesAndOpenBalanceOfFive();
		customers.put(aCustomer.getId(), aCustomer);
	}

	private void createTestProdcutsForWarehouse() {
		ArrayList<Product> products = new ArrayList<Product>();
		Product aProduct = aProduct().build();
		products.add(aProduct);
		products.add(aProduct);
		warehouseProducts.put(aProduct.getId(), products);
		warehouseProductInformation.put(aProduct.getId(), new ProductInformation(aProduct.getId(), true, 1));
	}

	private void createProductsForSuppliers() {
		supplierAddressesForProducts.put("productId", AUSTRIA_SUPPLIER_ADDRESS);
		supplierAddressesForProducts.put("xyz", AUSTRIA_SUPPLIER_ADDRESS);
		supplierAddressesForProducts.put("abc", GERMAN_SUPPLIER_ADDRESS);
		supplierAddressesForProducts.put("not available", AUSTRIA_SUPPLIER_ADDRESS);

		ArrayList<Product> productsGermany = new ArrayList<Product>();
		Product aProduct = aProductProvidedByGermanSupplier();
		productsGermany.add(aProduct);
		productsGermany.add(aProduct);
		supplierGermany.put(aProduct.getId(), productsGermany);

		ArrayList<Product> productsAustria = new ArrayList<Product>();
		aProduct = aProductProvidedByAustriaSupplier();
		productsAustria.add(aProduct);
		productsAustria.add(aProduct);
		supplierAustria.put(aProduct.getId(), productsAustria);

		productsAustria = new ArrayList<Product>();
		aProduct = aProduct().withProductId("productId").build();
		productsAustria.add(aProduct);
		productsAustria.add(aProduct);
		supplierAustria.put(aProduct.getId(), productsAustria);
	}

	@Override
	public void addCustomer(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	@Override
	public void deleteCustomer(String id) {
		Customer c = customers.get(id);
		if (c != null) {
			customers.remove(id);
		}
	}

	@Override
	public Customer getCustomer(String id) {
		return customers.get(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		Customer c = customers.get(customer.getId());
		c.setAddresses(customer.getAddresses());
		c.setName(customer.getName());
		c.setOpenBalance(customer.getOpenBalance());
		c.setOrders(customer.getOrders());
		if (c != null) {
			customers.put(c.getId(), c);
		}
	}

	@Override
	public void updateAccount(Customer customer, BigDecimal balance) {
		Customer c = customers.get(customer.getId());
		c.setOpenBalance(balance);
		if (c != null) {
			customers.put(c.getId(), c);
		}

	}

	@Override
	public void deleteProduct(Product aProduct) {
		List<Product> products = warehouseProducts.get(aProduct.getId());
		// TODO Implement delete Product
	}

	@Override
	public int countProducts(String id) {
		List<Product> products = warehouseProducts.get(id);
		if (products == null) {
			return 0;
		}
		return products.size();
	}

	@Override
	public String getSupplierAddressFor(String productId) {
		return supplierAddressesForProducts.get(productId);
	}

	@Override
	public void getFromAustriaSupplier(String productId) {
		List<Product> products = supplierAustria.get(productId);
		if (products == null) {
			return;
		}
		products.remove(0);
	}

	@Override
	public void getFromGermanSupplier(String productId) {
		List<Product> products = supplierGermany.get(productId);
		if (products == null) {
			return;
		}
		products.remove(0);
	}

	@Override
	public boolean isAvailableInAustria(Product aProduct, int amount) {
		List<Product> products = supplierAustria.get(aProduct.getId());
		if (products == null || products.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isAvailableInGermany(Product aProduct, int amount) {
		List<Product> products = supplierGermany.get(aProduct.getId());
		if (products == null || products.size() < amount) {
			return false;
		}
		return true;
	}
}