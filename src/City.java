// File: City.java

import java.util.*;

public class City {
    private int V; // Number of locations (vertices)
    private List<LinkedList<Edge>> adj; // Using ArrayList for adjacency list

    // Constructor to initialize the city with V locations
    public City(int V) {
        this.V = V;
        adj = new ArrayList<>(V); // Initialize the ArrayList with capacity V

        // Initialize each adjacency list as an empty LinkedList
        for (int i = 0; i < V; i++) {
            adj.add(new LinkedList<Edge>());
        }
    }

    // Class to represent a road between two locations with a weight (distance or
    // traffic)
    class Edge {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Function to add a road between two locations (src and dest)
    public void addRoad(int src, int dest, int weight) {
        adj.get(src).add(new Edge(dest, weight));
        adj.get(dest).add(new Edge(src, weight)); // Since it's an undirected road
    }

    // Get the adjacency list of a location
    public LinkedList<Edge> getAdj(int node) {
        return adj.get(node);
    }

    // Get the number of locations
    public int getV() {
        return V;
    }
}
