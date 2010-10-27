package bookstore;

import java.util.UUID;

import javax.jws.WebService;

@WebService
public interface ShippingService {

	UUID shipItems(Item[] items, String customerShippingAddress);

}
