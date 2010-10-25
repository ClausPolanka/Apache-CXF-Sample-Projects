package bookstore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bookstore.services.util.DateLongAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "order")
public class Order {

	@XmlAttribute(name = "id")
	private String id;

	@XmlJavaTypeAdapter(DateLongAdapter.class)
	private Date orderDate;

	private Customer customer;

	@XmlElementWrapper(name = "items")
	@XmlElements(@XmlElement(name = "item"))
	private List<Item> items = new ArrayList<Item>();

	public Order() {
		// Needed by Apache CXF.
	}

	public Order(String id, Date orderDate, Customer customer) {
		this.id = id;
		this.orderDate = orderDate;
		this.customer = customer;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getCustomerId() {
		return customer.getId();
	}

	public List<Item> getItems() {
		return items;
	}

}
