package com.cg.flight.model;

public class Plane {
	String type;
	int totalSeats;
	public Plane() {}
	public Plane(String type, int totalSeats) {
		super();
		this.type = type;
		this.totalSeats = totalSeats;
	}
	@Override
	public String toString() {
		return "Plane [type=" + type + ", totalSeats=" + totalSeats + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + totalSeats;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Plane other = (Plane) obj;
		if (totalSeats != other.totalSeats)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
}
