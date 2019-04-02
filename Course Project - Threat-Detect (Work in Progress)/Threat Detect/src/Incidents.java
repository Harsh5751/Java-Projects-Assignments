//Function toDistance() from https://www.geodatasource.com/developers/java

//Harsh Patel;
public class Incidents implements Comparable<Incidents> {

	private String UId;
	private double longitude;
	private double latitude;
	private String address;
	private String State;
	private String City;
	private int numKilled;
	private int numInjured;
	private double dangerLevel;
	private double userLongitude = 0;
	private double userLatitude = 0;
	private double disToIncident = 0;

	public Incidents(String UId, String address, String State, String City, double longitude, double latitude,
			int numKilled, int numInjured) {
		this.UId = UId;
		this.address = address;
		this.State = State;
		this.City = City;
		this.longitude = longitude;
		this.latitude = latitude;
		this.numKilled = numKilled;
		this.numInjured = numInjured;
	}

	public String getUId() {
		return UId;
	}

	public String getAddress() {
		return address;
	}

	public String getState() {
		return State;
	}

	public String getCity() {
		return City;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setUserLatitude(double userLatitude) {
		this.userLatitude = userLatitude;
	}

	public void setUserLongitude(double userLongitude) {
		this.userLongitude = userLongitude;
	}

	public int getnumKilled() {
		return numKilled;
	}

	public int getnumInjured() {
		return numInjured;
	}

	public int dangerLevel(int numKilled, int numInjured) {
		int dangerFormula = 0;
		return dangerFormula;
	}

	// Finding distance between two sets of longitude latitude points from :
	// https://www.geodatasource.com/developers/java
	public double toDistance(double latitude, double longitude, double userLatitude, double userLongitude) {
		if ((latitude == userLatitude) && (longitude == userLongitude)) {
			return 0;
		} else {
			double theta = longitude - userLongitude;
			double dist = Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(userLatitude))
					+ Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(userLatitude)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			disToIncident = dist;
			return (dist);
		}
	}
	
	public double getDisToIncident() {
		return disToIncident;
	}

	public String toString() {
		return String.format("id : %s, city : %s, state : %s, address : %s, Killed : %d, Injured : %d, Distance to You: %.2f", UId, City, State, address, numKilled, numInjured, disToIncident);
	}
	
	/*public double getDistance() {
		return toDistance(latitude, longitude, userLatitude, userLongitude);
	}*/
	
	@Override
	public int compareTo(Incidents that) {
		if (this.toDistance(latitude, longitude, userLatitude, userLongitude) > that.toDistance(latitude, longitude, userLatitude, userLongitude)) {
			return 1;
		} 
		else if (this.toDistance(latitude, longitude, userLatitude, userLongitude) < that.toDistance(latitude, longitude, userLatitude, userLongitude)) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
