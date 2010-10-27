package test.bookstore.services;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import java.math.BigDecimal;

import org.junit.Test;

import bookstore.Product;
import bookstore.Warehouse;
import bookstore.services.WarehouseJaxWS;

public class WarehouseJaxWSTest {
	private static final BigDecimal SINGLE_UNIT_PRICE = new BigDecimal(2);
	private static final int AMOUNT_1 = 1;
	private Warehouse warehouseService = new WarehouseJaxWS();

	@Test
	public void orderExactlyOneProduct() {
		Product aProdcut = aProduct().withSingleUnitPrice(SINGLE_UNIT_PRICE).build();

		BigDecimal totalPrice = warehouseService.order(aProdcut, AMOUNT_1);

		assertThat("Total price", totalPrice, is(equalTo(new BigDecimal(2))));
	}
}
