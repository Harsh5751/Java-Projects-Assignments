import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import geocoding.GeocodingException;
import geocoding.GeocodingImpl;

public class ReadAllocation {
	public static ArrayList<String> read() throws IOException {
		ArrayList<String> incidents = new ArrayList<>();
		try {
			Scanner input = new Scanner(new File("data\\test_read_300_rows.csv"), "UTF-8");
			String previousString = input.nextLine(); // This reads and skips the first line of the file which is just
														// naming for the data

			while (input.hasNextLine()) {
				String line = input.nextLine();
				if (line.equals("")) {

					String newLine = input.nextLine();
					while (newLine.equals(""))
						newLine = input.nextLine();
					continue;
				}
				previousString = line;
				incidents.add(line);
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return incidents;
	}

	public static ArrayList<Incidents> buildIncidents(ArrayList<String> data) throws IOException {
		ArrayList<Incidents> incidents = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			String incident = data.get(i);
			String[] columns = data.get(i).split(",");
			if (columns[0].equals("") || columns[4].equals("") || columns[2].equals("") || columns[3].equals("")
					|| columns[8].equals("") || columns[7].equals("") || columns[5].equals("")
					|| columns[6].equals("")) {
				continue;
			}
			Incidents temp = new Incidents(columns[0], columns[4], columns[2], columns[3],
					Double.parseDouble(columns[8]), Double.parseDouble(columns[7]), Integer.parseInt(columns[5]),
					Integer.parseInt(columns[6]));
			incidents.add(temp);
		}
		return incidents;
	}

	public static RedBlackTree<String, States> buildBST(ArrayList<Incidents> incidents) {
		RedBlackTree<String, States> StateTree = new RedBlackTree<String, States>();
		int i = 0;
		while (i != incidents.size()) {
			if (StateTree.contains(incidents.get(i).getState())) {
				States state = StateTree.get(incidents.get(i).getState());
				state.addIncidents(incidents.get(i));
				StateTree.put(incidents.get(i).getState(), state);
			} else {
				States state = new States(incidents.get(i).getState());
				state.addIncidents(incidents.get(i));
				StateTree.put(incidents.get(i).getState(), state);
			}
			i++;
		}
		return StateTree;
	}

	public static ArrayList<Incidents> SortIncidents(States state, double userLong, double userLat) {
		ArrayList<Incidents> SortIncidents = state.getIncidentsList();
		for (int i = 0; i < SortIncidents.size(); i++) {
			Incidents incident = SortIncidents.get(i);
			incident.setUserLatitude(userLat);
			incident.setUserLongitude(userLong);
		}
		QuickSort sortRange = new QuickSort(SortIncidents);
		sortRange.sortBasicQuick();

		return SortIncidents;
	}

	public static ArrayList<Incidents> filterIncidents(ArrayList<Incidents> InRangeIncidents, double Range) {
		int sizeofInci = InRangeIncidents.size();
		ArrayList<Incidents> InRangeIncidents2 = new ArrayList<Incidents>();
		System.out.println(sizeofInci);
		for (int i = 0; i < sizeofInci; i++) {
			if (InRangeIncidents.get(i).getDisToIncident() <= Range) {
				InRangeIncidents2.add(InRangeIncidents.get(i));
			}
		}
		return InRangeIncidents2;
	}

	public static void main(String args[]) throws IOException, GeocodingException {
		ArrayList<String> strIncidents = read();
		ArrayList<Incidents> Incidents = buildIncidents(strIncidents);
		RedBlackTree<String, States> StateTree = buildBST(Incidents);

		Scanner user_input = new Scanner(System.in);
		String address;
		System.out.print("Enter an address: ");
		address = user_input.nextLine();
		System.out.println(address);

		Scanner user_input1 = new Scanner(System.in);
		String UserState;
		System.out.print("Enter a State: ");
		UserState = user_input1.next();

		Scanner user_input2 = new Scanner(System.in);
		String Range;
		System.out.print("Enter a range between 0 to 8 km: ");
		Range = (user_input2.next());
		Double range = Double.parseDouble(Range);

		States userState = StateTree.get(UserState);
		GeocodingImpl geocode = new GeocodingImpl();
		JsonObject Userlatlong = geocode.getLatLng(address);
		Double userLong = Double.parseDouble(Userlatlong.get("lng").toString());
		Double userLat = Double.parseDouble(Userlatlong.get("lat").toString());

		ArrayList<Incidents> stateIncidents = SortIncidents(userState, userLong, userLat);
		ArrayList<Incidents> InRangeIncidents = filterIncidents(stateIncidents, range);

		System.out.println("Gun violence incidents near " + address + " in range " + range + "km");
		for (int i = 0; i < InRangeIncidents.size(); i++) {
			System.out.println(InRangeIncidents.get(i).toString());
		}
	}
}
