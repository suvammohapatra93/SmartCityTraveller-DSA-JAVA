// File: Dijkstra.java

import java.util.*;

public class Dijkstra {
    private City city;

    public Dijkstra(City city) {
        this.city = city;
    }

    // Function to find the shortest path from a source location to all other
    // locations
    public int[] findShortestPath(int src) {
        int V = city.getV();
        int[] dist = new int[V]; // Shortest distance array
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances to infinity
        dist[src] = 0; // Distance from the source to itself is 0

        PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingInt(a -> a.distance));
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            for (City.Edge edge : city.getAdj(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                // Relaxation step
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }

    // Helper class to represent a node in the priority queue
    class Node {
        int vertex, distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
