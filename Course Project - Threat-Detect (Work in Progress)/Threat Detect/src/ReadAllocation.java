import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;
import com.google.gson.JsonElement;
import java.util.Arrays;
import com.google.gson.JsonObject;
import com.google.maps.model.LatLng;

import geocoding.GeocodingException;
import geocoding.GeocodingImpl;

/**
 * @author Michael Barreiros, Harsh Patel
 *
 * 
 */
public class ReadAllocation {
	public static ArrayList<String> read(String file) throws IOException {
		ArrayList<String> incidents = new ArrayList<>();
		try {
			Scanner input = new Scanner(new File(file), "UTF-8");
			String previousString = input.nextLine(); // This reads and skips the first line of the file which is just
														// naming for the data
			int counter = 0;
			while(input.hasNextLine()) {
				String line = input.nextLine();
				if(counter == 0) {
					previousString = line;
					counter++;
					continue;
				}
				
				if(line.equals("")) {
					String newLine = input.nextLine();
					while(newLine.equals("")) {
						newLine = input.nextLine();
					}
					previousString = previousString + newLine;
				}
				
				if(line.equals("")) continue;
				incidents.add(previousString);
				
				if(!input.hasNext()) incidents.add(line);
				previousString = line;
				counter++;
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
			//System.out.println(data.get(i));
			String[] columns = data.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
			if (columns[0].equals("") || columns[4].equals("") || columns[2].equals("") || columns[3].equals("")
					|| columns[8].equals("") || columns[7].equals("") || columns[5].equals("")
					|| columns[6].equals("")) {
				continue;
			}
			/*
			if(columns[0].equals("92405")) {
				System.out.println("found");
				int n = 0;
				while(n != columns.length) {
					System.out.println(columns[n++]);
				}
			}
			if (columns[4].substring(0,1).equals("\"")) {
				System.out.println("in if");
				columns[4] = columns[4] + columns[5];
				columns[4] = columns[4].replace("\"", "");
				int removeIndex = 5;
				for(int j = removeIndex; j < columns.length -1; j++){
					columns[j] = columns[j + 1];
				 }
			}*/
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
		for (int i = 0; i < sizeofInci; i++) {
			if (InRangeIncidents.get(i).getDisToIncident() <= Range) {
				InRangeIncidents2.add(InRangeIncidents.get(i));
			}
		}
		return InRangeIncidents2;
	}

	public static void main(String args[]) throws IOException, GeocodingException {
		
		ArrayList<String> strIncidents = read("data\\gun-violence-data_01-2013_03-2018.csv");
		ArrayList<Incidents> Incidents = buildIncidents(strIncidents);
		RedBlackTree<String, States> StateTree = buildBST(Incidents);

		Scanner user_input = new Scanner(System.in);
		do {
			String address;
			System.out.print("Enter an address or enter 0 to end program: ");
			address = user_input.nextLine();
			
			if(address.equals("0")) break;
			
			//Scanner user_input1 = new Scanner(System.in);
			String UserState;
			System.out.print("Enter a State: ");
			UserState = user_input.nextLine();
			if(!(StateTree.contains(UserState))) {
				System.out.println("No Incidents in this area or Wrong Address");
				continue;
			}
	
			//Scanner user_input2 = new Scanner(System.in);
			String Range;
			System.out.print("Enter a Range between 0 to 8 km: ");
			Range = (user_input.next());
			Double range = Double.parseDouble(Range);
	
			States userState = StateTree.get(UserState);
			GeocodingImpl geocode = new GeocodingImpl();
			JsonObject Userlatlong = geocode.getLatLng(address);
			Double userLong = Double.parseDouble(Userlatlong.get("lng").toString());
			Double userLat = Double.parseDouble(Userlatlong.get("lat").toString());
	
			ArrayList<Incidents> stateIncidents = SortIncidents(userState, userLong, userLat);
			ArrayList<Incidents> InRangeIncidents = filterIncidents(stateIncidents, range);
			
			System.out.println("");
			if (InRangeIncidents.size() == 0) {
				System.out.println("No Incidents in this area or in range");
				continue;
			}
			System.out.println("Gun violence incidents near " + address + " in range " + range + " km: ");
			for (int i = 0; i < InRangeIncidents.size(); i++) {
				System.out.println(InRangeIncidents.get(i).toString());
			}
			String DangerLevel;
		
			if(InRangeIncidents.size() < 15) {
				DangerLevel = "LOW DANGER";
			}
			else if (15 <= InRangeIncidents.size() && InRangeIncidents.size() < 30 ) {
				DangerLevel = "MODERATE DANGER";
			}
			else {
				DangerLevel = "HIGH DANGER. Stay Cautious";
			}
			System.out.println("");
			System.out.println("THE DANGER LEVEL IN THIS AREA IS: " + DangerLevel);
			System.out.println("");
			user_input.nextLine();
		}while(true);
		user_input.close();
	}
}
