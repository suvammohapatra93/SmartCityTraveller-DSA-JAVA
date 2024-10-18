// File: MinimumSpanningTree.java

import java.util.*;

public class MinimumSpanningTree {
    private City city;

    public MinimumSpanningTree(City city) {
        this.city = city;
    }

    // Function to find the Minimum Spanning Tree using Kruskal's algorithm
    public int kruskalMST() {
        int V = city.getV();
        List<Edge> edges = new ArrayList<>();

        // Convert adjacency list to a list of edges
        for (int i = 0; i < V; i++) {
            for (City.Edge edge : city.getAdj(i)) {
                edges.add(new Edge(i, edge.dest, edge.weight));
            }
        }

        // Sort edges by weight
        Collections.sort(edges);

        // Initialize disjoint sets
        DSU dsu = new DSU(V);
        int mstWeight = 0;

        for (Edge edge : edges) {
            if (dsu.find(edge.src) != dsu.find(edge.dest)) {
                mstWeight += edge.weight;
                dsu.union(edge.src, edge.dest);
            }
        }

        return mstWeight; // Total weight of the Minimum Spanning Tree
    }

    // Helper class to represent an edge
    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    // Disjoint Set Union (DSU) class for Kruskal's algorithm
    class DSU {
        int[] parent, rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }
}
