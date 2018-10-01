package t04; 

public class Driver {
	private final long id; 
	private final String name;
	private long availableSeating; 
	private long stops; 
	private long price; 
    //previous huge constructor 
	// public Driver (long id, String name, long availableSeating, long stops, long price) {
	// 	this.id = id; 
	// 	this.name   = name;
	// 	this.availableSeating = availableSeating; 
	// 	this.stops = stops; 
	// 	this.price = price;
	// }
	public Driver (long id, String name) {
		this.id = id; 
		this.name   = name;
		this.availableSeating = -1000; 
		this.stops = -1000; 
		this.price = -1000;
	}

	public long getID() {
		return id; 
	}

	public String getName() {
		return name; 
	}

	public void setSeating(long numberofSeating) {
		this.availableSeating = numberofSeating; 
	}
	public void setStops(long numberofStops) {
		this.stops = numberofStops; 
	}
	public void setPrice(long individualPrice) {
		this.price = individualPrice; 
	}
	public String getSeating() {
		long a = this.availableSeating; 
		return Long.toString(a); 
	}
	public String getStops() {
		long s = this.stops; 
		return Long.toString(s); 
	}
	public String getPrice() {
		long p = this.price; 
		return Long.toString(p); 
	}
}

