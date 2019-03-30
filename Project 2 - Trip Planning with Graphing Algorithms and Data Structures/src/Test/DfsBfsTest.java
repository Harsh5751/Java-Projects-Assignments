package Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Graph_Alg.BreadthFirstDirectedPaths;
import Graph_Alg.Digraph;
import Graph_Alg.DirectedDepthFirstPaths;
import Graph_Alg.SequentialSearchST;
import Graph_Alg.TripPlanner;

class DfsBfsTest {

	TripPlanner trip = new TripPlanner();
	SequentialSearchST<String, Integer> UsCityData = trip.initSymboTable();
	int size = UsCityData.Size();
	Digraph CityConnect = trip.initGraph(UsCityData, size);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void BfsCorrectPathstest() {
		List<String> ProgramPath = new ArrayList<String>();
		List<String> ProgramPath1 = new ArrayList<String>();

		String start = "BOSTON";
		String finish = "MINNEAPOLIS";

		String start1 = "NEW YORK CITY";
		String finish1 = "BOSTON";

		//Test Normal Path if Correct Path and City Visited
		BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(CityConnect, UsCityData.get(start));

		if (bfs.hasPathTo(UsCityData.get(finish))) {
			for (int x : bfs.pathTo(UsCityData.get(finish))) {
				ProgramPath.add(UsCityData.getKey(x));
			}
		} else {
			ProgramPath.add("Not Connected");
		}

		//Test When there is no path should return "Not Connected"
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(CityConnect, UsCityData.get(start1));
		
		if (bfs1.hasPathTo(UsCityData.get(finish1))) {
			for (int x : bfs1.pathTo(UsCityData.get(finish1))) {
				ProgramPath1.add(UsCityData.getKey(x));
			}
		} else {
			ProgramPath1.add("Not Connected");
		}
		
		assert ((ProgramPath.get(1).equals("NEW YORK CITY")) && (ProgramPath.get(2).equals("PITTSBURGH"))
				&& (ProgramPath.get(3).equals("COLUMBUS")) && (ProgramPath.get(5).equals(finish)));
		assert((ProgramPath1.get(0)).equals("Not Connected"));
	}
	
	@Test
	void DfsCorrectPathstest() {
		List<String> ProgramPath = new ArrayList<String>();
		List<String> ProgramPath1 = new ArrayList<String>();

		String start = "BOSTON";
		String finish = "MINNEAPOLIS";

		String start1 = "NEW YORK CITY";
		String finish1 = "BOSTON";

		//Test Normal Path if Correct Path and City Visited
		DirectedDepthFirstPaths dfs = new DirectedDepthFirstPaths(CityConnect, UsCityData.get(start));

		if (dfs.hasPathTo(UsCityData.get(finish))) {
			for (int x : dfs.pathTo(UsCityData.get(finish))) {
				ProgramPath.add(UsCityData.getKey(x));
			}
		} else {
			ProgramPath.add("Not Connected");
		}

		//Test When there is no path should return "Not Connected"
		DirectedDepthFirstPaths dfs1 = new DirectedDepthFirstPaths(CityConnect, UsCityData.get(start1));
		
		if (dfs1.hasPathTo(UsCityData.get(finish1))) {
			for (int x : dfs1.pathTo(UsCityData.get(finish1))) {
				ProgramPath1.add(UsCityData.getKey(x));
			}
		} else {
			ProgramPath1.add("Not Connected");
		}
		
		assert ((ProgramPath.get(1).equals("NEW YORK CITY")) && (ProgramPath.get(2).equals("PITTSBURGH"))
				&& (ProgramPath.get(3).equals("COLUMBUS")) && (ProgramPath.get(7).equals(finish)));
		assert((ProgramPath1.get(0)).equals("Not Connected"));
	}

	
}

