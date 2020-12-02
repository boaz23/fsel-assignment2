package acptTests.bridge;

import java.util.LinkedList;
import java.util.List;

import hw2.data.OrderInfo;
import hw2.data.ShowInfo;
import hw2.bridge.Bridge;

public class ProxyBridge implements Bridge {
	private Bridge real;

	public ProxyBridge() {
		real = null;
	}

	public void setRealBridge(Bridge implementation) {
		if (real == null)
			real = implementation;
	}

	@Override
	public void addCity(String city) {
		if(real == null){
			return;
		}
		real.addCity(city);
	}

	@Override
	public void addHall(String city, String hall, int sits) {
		if(real == null){
			return;
		}
		real.addHall(city, hall, sits);
	}

	@Override
	public void addAdmin(String city, String user, String pass) {
		if(real == null){
			return;
		}
		real.addAdmin(city, user, pass);
	}

	@Override
	public int addNewShow(String user, String pass, ShowInfo showInfo) {
		if(real == null){
			return -1;
		}
		return real.addNewShow(user, pass, showInfo);

	}

	@Override
	public void reserveMemberChairs(int showID, int from, int to) {
		if(real == null){
			return;
		}
		real.reserveMemberChairs(showID, from, to);
	}

	@Override
	public int newOrder(OrderInfo order) {
		if(real == null){
			return -1;
		}
		return real.newOrder(order);
	}

	@Override
	public List<OrderInfo> getWaitings(int id) {
		if(real == null){
			return null;
		}
		return real.getWaitings(id);
	}
}