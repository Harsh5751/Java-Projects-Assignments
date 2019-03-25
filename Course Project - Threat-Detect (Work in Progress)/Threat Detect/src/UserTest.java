import java.util.Scanner;

import com.google.gson.JsonObject;

import geocoding.*;

public class UserTest {
	public static void main (String [] args) throws GeocodingException {
		System.out.println("Enter address");
		Scanner sc = new Scanner (System.in);
		String userAddress = sc.nextLine();

        GeocodingImpl geocoding;
        geocoding = new GeocodingImpl();

        final JsonObject latlng = geocoding.getLatLng(userAddress);
        final double latitude = latlng.get("lat").getAsDouble();
        final double longitude = latlng.get("lng").getAsDouble();
        System.out.println(latitude);
        System.out.println(longitude);

	
	}
}
