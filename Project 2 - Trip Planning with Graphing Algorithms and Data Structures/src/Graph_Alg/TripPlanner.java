package Graph_Alg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.Spring;

public class TripPlanner {

	// Initialize the symbol table used to map an Integer to the city Name from
	// reading file
	public static SequentialSearchST<String, Integer> initSymboTable() {

		File f = new File("2XB3_A2_DataSets\\USCities.csv");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SequentialSearchST<String, Integer> UsCityData = new SequentialSearchST<String, Integer>();

		// Skip Labels
		String labels = s.nextLine();

		int CityCount = 0;

		// Get count of cities to initialize Graph and put into symbolTable
		while (s.hasNextLine()) {
			String CityData = s.nextLine();
			String[] CityLst = CityData.split(",");
			UsCityData.put(CityLst[3], CityCount);
			CityCount++;
		}
		s.close();
		return UsCityData;
	}

	// Directed graph with size number of nodes
	public static Digraph initGraph(SequentialSearchST<String, Integer> UsCityData, int size) {
		Digraph CityConnect = new Digraph(size);

		// Connect the Cities
		File connectedCities = new File("2XB3_A2_DataSets\\connectedCities.txt");
		Scanner s1 = null;
		try {
			s1 = new Scanner(connectedCities);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// add edges
		while (s1.hasNextLine()) {
			String connection = s1.nextLine();
			String[] connectionLst = connection.split(",");
			int v = UsCityData.get(connectionLst[0].toUpperCase());
			int w = UsCityData.get((connectionLst[1].toString().substring(1)).toUpperCase());
			CityConnect.addEdge(v, w);
		}
		s1.close();
		return CityConnect;
	}

	// Directed Depth-First Search Algorithm Output Path
	public static void DirectedDFS(String start, String destination, Digraph CityConnect,
			SequentialSearchST<String, Integer> UsCityData) {

		int startCity = UsCityData.get(start.toUpperCase());
		DirectedDepthFirstPaths dfs = new DirectedDepthFirstPaths(CityConnect, startCity);

		for (int v = 0; v < CityConnect.V(); v++) {
			int target = UsCityData.get(destination.toUpperCase());
			if (v == target) {
				if (dfs.hasPathTo(v)) {
					System.out.printf("DFS - %s to %s: ", UsCityData.getKey(startCity), UsCityData.getKey(target));
					for (int x : dfs.pathTo(v)) {
						if (x == startCity) {
							System.out.print(UsCityData.getKey(x));
						} else {
							System.out.print(", " + UsCityData.getKey(x));
						}
					}
					System.out.println();
				} else {
					System.out.println("No Path");
				}
			}
		}
	}

	// Directed Breadth-First Search Algorithm Output Path
	public static void DirectedBFS(String start, String destination, Digraph CityConnect,
			SequentialSearchST<String, Integer> UsCityData) {
		
		int startCity = UsCityData.get(start.toUpperCase());
		BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(CityConnect, startCity);

		for (int v = 0; v < CityConnect.V(); v++) {
			int target = UsCityData.get(destination.toUpperCase());
			if (v == target) {
				if (bfs.hasPathTo(v)) {
					System.out.printf("BFS - %s to %s: ", UsCityData.getKey(startCity), UsCityData.getKey(target));
					for (int x : bfs.pathTo(v)) {
						if (x == startCity) {
							System.out.print(UsCityData.getKey(x));
						} else {
							System.out.print(", " + UsCityData.getKey(x));
						}
					}
					System.out.println();
				} else {
					System.out.println("No Path");
				}
			}
		}
	}

	// Map type of food locations available in city to city Name based on +0.5 and
	// -0.5 of city longitude and latitude
	public static SequentialSearchST<String, List<String>> FoodPlaces(SequentialSearchST<String, Integer> UsCityData) {
		// Read files
		File f = new File("2XB3_A2_DataSets\\USCities.csv");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File burgerKing = new File("2XB3_A2_DataSets\\burgerking.csv");
		Scanner bk = null;
		try {
			bk = new Scanner(burgerKing);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File MacDonalds = new File("2XB3_A2_DataSets\\mcdonalds.csv");
		Scanner md = null;
		try {
			md = new Scanner(MacDonalds);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File Wendy = new File("2XB3_A2_DataSets\\wendys.csv");
		Scanner wd = null;
		try {
			wd = new Scanner(Wendy);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Skip Labels
		String labels = s.nextLine();

		SequentialSearchST<String, List<String>> UsCityFood = new SequentialSearchST<String, List<String>>();

		int CityCount = 0;

		// Get count of cities to initialize Graph and put into symbolTable
		while (s.hasNextLine()) {
			try {
				md = new Scanner(MacDonalds);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bk = new Scanner(burgerKing);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				wd = new Scanner(Wendy);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String Mlabels = md.nextLine();
			String Blabels = bk.nextLine();
			String Wlabels = wd.nextLine();

			boolean MacCity = false;
			boolean WenCity = false;
			boolean BurgerCity = false;

			String CityData = s.nextLine();
			String[] CityLst = CityData.split(",");
			UsCityData.put(CityLst[3], CityCount);
			Double CLong = Double.parseDouble(CityLst[5]);
			Double CLat = Double.parseDouble(CityLst[4]);
			CityCount++;

			// Check if MacDonalds is in City Range
			while (md.hasNextLine()) {
				String mdData = md.nextLine();
				String[] mdLst = mdData.split(",");
				Double mLong = Double.parseDouble(mdLst[0]);
				Double mLat = Double.parseDouble(mdLst[1]);
				if ((CLong - 0.5 < mLong && mLong < CLong + 0.5) && (CLat - 0.5 < mLat && mLat < CLat + 0.5)) {
					MacCity = true;
					break;
				}
			}

			// Check if Burger King is in City Range
			while (bk.hasNextLine()) {
				String bkData = bk.nextLine();
				String[] bkLst = bkData.split(",");
				Double bLong = Double.parseDouble(bkLst[0]);
				Double bLat = Double.parseDouble(bkLst[1]);
				if ((CLong - 0.5 < bLong && bLong < CLong + 0.5) && (CLat - 0.5 < bLat && bLat < CLat + 0.5)) {
					BurgerCity = true;
					break;
				}
			}

			// Check if Wendys is in City Range
			while (wd.hasNextLine()) {
				String wdData = wd.nextLine();
				String[] wdLst = wdData.split(",");
				Double wLong = Double.parseDouble(wdLst[0]);
				Double wLat = Double.parseDouble(wdLst[1]);
				if ((CLong - 0.5 < wLong && wLong < CLong + 0.5) && (CLat - 0.5 < wLat && wLat < CLat + 0.5)) {
					WenCity = true;
					break;
				}
			}

			// Adding corresponding restaurants to City value if in range
			if (MacCity == true && BurgerCity == true && WenCity == true) {
				UsCityFood.put(CityLst[3], new ArrayList<String>(Arrays.asList("McDonalds", "Wendys", "Burger King")));
			} else if (MacCity == true && BurgerCity == false && WenCity == false) {
				UsCityFood.put(CityLst[3], new ArrayList<String>(Arrays.asList("McDonalds")));
			} else if (MacCity == false && BurgerCity == true && WenCity == false) {
				UsCityFood.put(CityLst[3], new ArrayList<String>(Arrays.asList("Burger King")));
			} else if (MacCity == false && BurgerCity == false && WenCity == true) {
				UsCityFood.put(CityLst[3], new ArrayList<String>(Arrays.asList("Wendys")));
			} else if (MacCity == true && BurgerCity == true && WenCity == false) {
				UsCityFood.put(CityLst[3], new ArrayList<String>(Arrays.asList("McDonalds", "Burger King")));
			} else if (MacCity == true && BurgerCity == false && WenCity == true) {
				UsCityFood.put(CityLst[3], new ArrayList<String>(Arrays.asList("McDonalds", "Wendys")));
			} else if (MacCity == false && BurgerCity == true && WenCity == true) {
				UsCityFood.put(CityLst[3], new ArrayList<String>(Arrays.asList("Burger King", "Wendys")));
			}
		}
		
		s.close();
		bk.close();
		md.close();
		wd.close();

		return UsCityFood;
	}

	// Implementing Dijkstra Shortest Path, adding weights based on minimum costs,
	// and initializing graphs
	static void DijkstraShortPath(SequentialSearchST<String, Integer> UsCityData,
			SequentialSearchST<String, List<String>> UsCityFood, String start, String destinition) {

		// Store restaurant information
		ArrayList<String> MacMeals = new ArrayList<String>();
		ArrayList<Double> MacPrice = new ArrayList<Double>();
		ArrayList<String> BurgerMeals = new ArrayList<String>();
		ArrayList<Double> BurgerPrice = new ArrayList<Double>();
		ArrayList<String> WendyMeals = new ArrayList<String>();
		ArrayList<Double> WendyPrice = new ArrayList<Double>();

		// Get info from menu file
		Scanner menu = null;
		try {
			menu = new Scanner(new File("2XB3_A2_DataSets\\menu.csv"), "UTF-8");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String SkipLine = menu.nextLine();

		while (menu.hasNextLine()) {
			String fMenu = menu.nextLine();
			String[] menuLst = fMenu.split(",");

			// add meal name and price to corresponding list
			if (menuLst[0].equals("McDonald’s")) {
				MacMeals.add(menuLst[1]);
				MacPrice.add(Double.parseDouble(menuLst[2].substring(1)));
			} else if (menuLst[0].equals("Burger King")) {
				BurgerMeals.add(menuLst[1]);
				BurgerPrice.add(Double.parseDouble(menuLst[2].substring(1)));
			} else if (menuLst[0].equals("Wendy’s")) {
				WendyMeals.add(menuLst[1]);
				WendyPrice.add(Double.parseDouble(menuLst[2].substring(1)));
			}
		}

		// make edge weighted graph
		EdgeWeightedDigraph TripGraph = new EdgeWeightedDigraph(UsCityData.Size());

		// connect cities
		File connectedCities = new File("2XB3_A2_DataSets\\connectedCities.txt");
		Scanner s1 = null;
		try {
			s1 = new Scanner(connectedCities);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int previous = 0;
		double finalmin = 0;
		double finalmin2 = 0;

		// find the minimum cost of the meal and assign it as the weight
		while (s1.hasNextLine()) {
			String connection = s1.nextLine();
			String[] connectionLst = connection.split(",");
			int v = UsCityData.get(connectionLst[0].toUpperCase());
			int w = UsCityData.get((connectionLst[1].toString().substring(1)).toUpperCase());
			String targeted = UsCityData.getKey(w);
			double min = 1000;
			double min2 = 1000;
			// check if restaurant is in city. All cities have McDonalds based on Symbol
			// Table
			// McDonalds also has the two cheapest prices
			if ((UsCityFood.get(targeted).get(0)).equals("McDonalds")) {
				for (int i = 0; i < MacPrice.size(); i++) {
					if (MacPrice.get(i) < min) {
						min2 = min;
						min = MacPrice.get(i);
					} else if (min2 > MacPrice.get(i)) {
						min2 = MacPrice.get(i);
					}
				}
			}

			// assign weights
			Double weight = (double) 0;
			
			finalmin = min;
			finalmin2 = min2;
			
			if (previous % 2 == 0) {
				weight = min;
				previous++;
			} else {
				weight = min2;
				previous++;
			}
			
			DirectedEdge e = new DirectedEdge(v, w, weight);
			TripGraph.addEdge(e);
		}
		
		s1.close();

		int startCity = UsCityData.get(start.toUpperCase());
		int endCity = UsCityData.get(destinition.toUpperCase());

		DijkstraSP sp = new DijkstraSP(TripGraph, startCity);

		// Overwrite weight for our specific path for cheap meal plan
		int counter = 0;
		for (DirectedEdge e : sp.pathTo(endCity)) {
			if (counter % 2 == 0) {
				e.weightOverride(finalmin);
				counter++;
			} else {
				e.weightOverride(finalmin2);
				counter++;
			}
		}
		
		sp = new DijkstraSP(TripGraph, startCity);

		// Out put the shortest path with the weights
		for (int t = 0; t < TripGraph.V(); t++) {
			if (t == endCity) {
				if (sp.hasPathTo(t)) {
					System.out.printf("%s to %s (%.2f):  ", UsCityData.getKey(startCity), UsCityData.getKey(t),
							sp.distTo(t));
					for (DirectedEdge e : sp.pathTo(t)) {
						System.out.printf("%s -> %s  (%3.2f)   ", UsCityData.getKey(e.from()),
								UsCityData.getKey(e.to()), e.weight());
					}

					System.out.println();
				} else {
					System.out.printf("%s to %s no path\n", UsCityData.getKey(startCity), UsCityData.getKey(t));
				}
			}
		}
	}

	public static void main(String[] args) {
		// Symbol table that maps Integer to City Name
		SequentialSearchST<String, Integer> UsCityData = initSymboTable();
		// Size of Symbol Table
		int size = UsCityData.Size();
		// Directional Graph with nodes connected unidirectional
		Digraph CityConnect = initGraph(UsCityData, size);

		// Start to final Destination
		String start = "BOSTON";
		String destinition = "MINNEAPOLIS";

		// Output path of Depth-First
		System.out.println("Depth-First DirectedPaths:");
		DirectedDFS(start, destinition, CityConnect, UsCityData);
		System.out.println("");

		// Output path of Breadth-First
		System.out.println("Breadth-First DirectedPaths:");
		DirectedBFS(start, destinition, CityConnect, UsCityData);
		System.out.println("");

		// Symbol table that maps available food places to City String
		SequentialSearchST<String, List<String>> FoodCityData = FoodPlaces(UsCityData);
		// Output total cost/weight and path of Dijkstra's shortest path
		System.out.println("Using Dijkstra's Shortest Path to get Cheapest Trip:");
		DijkstraShortPath(UsCityData, FoodCityData, start, destinition);

	}

}
