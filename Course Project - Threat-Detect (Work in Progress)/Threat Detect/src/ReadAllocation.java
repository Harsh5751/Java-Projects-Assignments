import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAllocation{
	public static void read() {
		try {
			Scanner input = new Scanner(new File("data\\gun-violence-data_01-2013_03-2018.csv"), "UTF-8");
			String previousString = input.nextLine(); //This reads and skips the first line of the file which is just naming for the data
			
			ArrayList<String> incidents = new ArrayList<>();;
			while(input.hasNextLine()) {
				String line = input.nextLine();
				if (line.equals("")) {
					String newLine = input.nextLine();
					while(newLine.equals("")) newLine=input.nextLine();
					String full = previousString + newLine;
					incidents.add(full);
					//String[] columns = full.split(",");
					//System.out.println(full);
					continue;
				}
				previousString = line; //Dealing with spaces in our dataset
				//String[] columns = line.split(",");
				//System.out.println(line);
				incidents.add(line);
			}
			buildIncidents(incidents);
			input.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch (Exception ex) {
			System.out.println("Exception occured");
		}
	}
	
	public static void buildIncidents(ArrayList<String> data) {
		for (int i = 0; i < data.size(); i++) {
			String[] columns = data.get(i).split(",");
			Incidents temp = new Incidents(columns[0], columns[4], columns[2], columns[3], Double.parseDouble(columns[16]), Double.parseDouble(columns[14]), Integer.parseInt(columns[5]), Integer.parseInt(columns[6]));
			
		}
		
		
		
	}
	public static void main(String args[]) {
		read();
	}
}

