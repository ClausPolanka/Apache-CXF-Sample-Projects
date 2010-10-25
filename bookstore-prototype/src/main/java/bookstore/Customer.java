package bookstore;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customer")
public class Customer {

	@XmlAttribute(name = "id")
	private String id;
	private String name;
	private List<Address> addresses = new ArrayList<Address>();
	@XmlTransient
	private List<Order> orders = new ArrayList<Order>();

	private Customer() {
		// Needed by Apache CXF.
	}

	public Customer(String id) {
		this.id = id;
	}

	public void addAddress(Address address) {
		addresses.add(address);
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
