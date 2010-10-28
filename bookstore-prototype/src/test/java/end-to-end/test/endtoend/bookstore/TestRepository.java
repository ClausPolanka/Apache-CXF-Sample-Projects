package test.endtoend.bookstore;

import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomerWithAddressesAndOpenBalanceOfFive;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bookstore.BookstoreLibrary;
import bookstore.Customer;
import bookstore.Product;

public class TestRepository implements BookstoreLibrary {

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

	public TestRepository() {
		createTestCustomers();
		createTestProdcutsForWarehouse();
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
		return warehouseProducts.get(id).size();
	}
}