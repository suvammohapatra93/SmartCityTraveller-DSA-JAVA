/*
SmartCityTraveller.java:

The main class where the execution starts.
This file is the entry point of the project. It interacts with the user, initializes the city with locations and roads, and handles queries like finding the shortest path and optimizing the road network.
*/

import java.util.Scanner;

public class SmartCityTraveller {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input the number of locations in the city
        System.out.println("Enter number of locations in the city:");
        int V = sc.nextInt();

        City city = new City(V);

        // Step 2: Input the number of roads in the city
        System.out.println("Enter the number of roads in the city:");
        int roads = sc.nextInt();

        // Step 3: Input each road between valid locations
        System.out
                .println("Enter the roads in format: <source> <destination> <weight> (Indices should be between 0 and "
                        + (V - 1) + ")");
        for (int i = 0; i < roads; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();

            // Step 4: Validate if the source and destination are within bounds
            if (src >= V || dest >= V || src < 0 || dest < 0) {
                System.out.println("Error: Invalid location indices. Please enter values between 0 and " + (V - 1));
                i--; // Decrement the counter to ask for input again for this road
                continue; // Ask for valid input
            }

            // Add the road to the city
            city.addRoad(src, dest, weight);
        }

        // Step 5: Dijkstra's algorithm for shortest paths
        Dijkstra dijkstra = new Dijkstra(city);
        System.out.println("Enter source location for shortest path:");
        int src = sc.nextInt();

        // Validate source location
        if (src >= V || src < 0) {
            System.out.println("Error: Invalid source location. Please enter a value between 0 and " + (V - 1));
            sc.close();
            return;
        }

        int[] shortestPaths = dijkstra.findShortestPath(src);

        // Output shortest paths
        System.out.println("Shortest paths from location " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To location " + i + ": " + shortestPaths[i]);
        }

        // Step 6: Minimum Spanning Tree calculation
        MinimumSpanningTree mst = new MinimumSpanningTree(city);
        int totalMSTWeight = mst.kruskalMST();
        System.out.println("Total weight of the Minimum Spanning Tree: " + totalMSTWeight);
        sc.close();
    }
}
