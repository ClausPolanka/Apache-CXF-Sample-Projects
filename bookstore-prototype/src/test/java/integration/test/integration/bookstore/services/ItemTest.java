package test.integration.bookstore.services;

import javax.jws.WebService;

import bookstore.Item;

@WebService
public interface ItemTest {

	Item testItem(Item anItem);

}
