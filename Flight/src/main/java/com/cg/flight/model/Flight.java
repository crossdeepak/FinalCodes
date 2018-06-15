package com.cg.flight.model;

public class Flight {
	int id,price,emptySeats;
	String code,departureDate,origin,destination;
	Plane plane;
	public Flight() {}
	public Flight(int id, int price, int emptySeats, String code, String departureDate, String origin,
			String destination, Plane plane) {
		super();
		this.id = id;
		this.price = price;
		this.emptySeats = emptySeats;
		this.code = code;
		this.departureDate = departureDate;
		this.origin = origin;
		this.destination = destination;
		this.plane = plane;
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", price=" + price + ", emptySeats=" + emptySeats + ", code=" + code
				+ ", departureDate=" + departureDate + ", origin=" + origin + ", destination=" + destination
				+ ", plane=" + plane + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + emptySeats;
		result = prime * result + id;
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((plane == null) ? 0 : plane.hashCode());
		result = prime * result + price;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (emptySeats != other.emptySeats)
			return false;
		if (id != other.id)
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (plane == null) {
			if (other.plane != null)
				return false;
		} else if (!plane.equals(other.plane))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEmptySeats() {
		return emptySeats;
	}
	public void setEmptySeats(int emptySeats) {
		this.emptySeats = emptySeats;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}
	
}





/*ID: 1
code: ER38sd
price: 400
departureDate: 2017/07/26
origin: CLE
destination: SFO
emptySeats: 0
plane:
  type: Boeing 737
  totalSeats: 150*/