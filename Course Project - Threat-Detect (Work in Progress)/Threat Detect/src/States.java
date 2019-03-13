import java.util.ArrayList;

//Harsh Patel;
public class States implements Comparable<States> {
	private String StateName;
	private ArrayList<Incidents> IncidentsList = new ArrayList<>();
	
	public States(String StateName) {
		this.StateName = StateName;
	}
	
	public void addIncidents(Incidents incident) {
		IncidentsList.add(incident);
	}
	
	public String getStateName() {
		return StateName;
	}
	
	public ArrayList<Incidents> getIncidentsList(){
		return IncidentsList;
	}
	
	public String toString() {
		return String.format("State Name : %s", StateName);
	}
	
	@Override
	public int compareTo(States that) {
		if ((this.StateName).compareTo(that.StateName) > 0) {
			return 1;
		}
		else if ((this.StateName).compareTo(that.StateName) < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
