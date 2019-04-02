import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAllocation{
	public static ArrayList<String> read() throws IOException {
		ArrayList<String> incidents = new ArrayList<>();
		try {
			Scanner input = new Scanner(new File("data\\test_read_300_rows.csv"), "UTF-8");
			String previousString = input.nextLine(); //This reads and skips the first line of the file which is just naming for the data
			
			while(input.hasNextLine()) {
				String line = input.nextLine();
				if (line.equals("")) {
					
					String newLine = input.nextLine();
					while(newLine.equals("")) newLine=input.nextLine();
					//String full = previousString + newLine;
					//incidents.add(full);
					//String[] columns = full.split(",");
					//System.out.println(full);
					continue;
				}
				previousString = line; //Dealing with spaces in our dataset
				//String[] columns = line.split(",");
				//System.out.println(line);
				incidents.add(line);
			}
			input.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return incidents;
	}
	
	public static ArrayList<Incidents> buildIncidents(ArrayList<String> data) throws IOException {
		ArrayList<Incidents> incidents = new ArrayList<>();
		//Writer wr = null;
		//File f = new File("Data\\demo.txt");
		//wr = new BufferedWriter(new FileWriter(f));
		for (int i = 0; i < data.size(); i++) {
			//System.out.println(i);
			//System.out.println(data.get(i));
			//wr.write(data.get(i));
			//wr.write("\n");
			String incident = data.get(i);
			String[] columns = data.get(i).split(",");
			if (columns[0].equals("") || columns[4].equals("") || columns[2].equals("") || columns[3].equals("") || columns[8].equals("") || columns[7].equals("") || columns[5].equals("") || columns[6].equals("")) {
				continue;
			}
			Incidents temp = new Incidents(columns[0], columns[4], columns[2], columns[3], Double.parseDouble(columns[8]), Double.parseDouble(columns[7]), Integer.parseInt(columns[5]), Integer.parseInt(columns[6]));
			incidents.add(temp);
		}
		return incidents;
	}
	
	public static RedBlackTree<String, States> buildBST(ArrayList<Incidents> incidents){
		RedBlackTree<String, States> StateTree = new RedBlackTree<String, States>();
		int i = 0;
		while(i != incidents.size()) {
			if(StateTree.contains(incidents.get(i).getState())) {
				States state = StateTree.get(incidents.get(i).getState());
				state.addIncidents(incidents.get(i));
				StateTree.put(incidents.get(i).getState(), state);
			}
			else {
				States state = new States(incidents.get(i).getState());
				state.addIncidents(incidents.get(i));
				StateTree.put(incidents.get(i).getState(), state);
			}
			i++;
		}
		/*
		States state = StateTree.get("California");
		ArrayList<Incidents> inciLst = state.getIncidentsList();
		for(int j = 0; j < inciLst.size(); j++) {
			System.out.println(inciLst.get(j).getAddress());
		}*/
		return StateTree;
	}
	
	
	
	public static ArrayList<Incidents> SortIncidents(States state, double userLong, double userLat){
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
	
	public static ArrayList<Incidents> filterIncidents(ArrayList<Incidents> InRangeIncidents, double Range){
		for (int i = 0; i < InRangeIncidents.size(); i++) {
			if (InRangeIncidents.get(i).getDisToIncident() > Range) {
				InRangeIncidents.remove(i);
			}
		}
		return InRangeIncidents;
		
	}
	
	
	public static void main(String args[]) throws IOException {
		ArrayList<String> strIncidents = read();
		ArrayList<Incidents> Incidents = buildIncidents(strIncidents);
		RedBlackTree<String, States> StateTree = buildBST(Incidents);
	}
}

