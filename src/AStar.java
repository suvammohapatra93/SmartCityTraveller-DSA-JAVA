import java.util.*;

public class AStar {
    private City city;
    private int src;

    public AStar(City city, int src) {
        this.city = city;
        this.src = src;
    }

    public int[] shortestPath() {
        int V = city.getV();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
        pq.add(new Node(src, 0, heuristic(src)));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.location;

            for (City.Edge edge : city.getAdj(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                // Update distance using A* formula
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v], heuristic(v)));
                }
            }
        }
        return dist;
    }

    private int heuristic(int location) {
        // Simple heuristic for demonstration: return the location itself as a heuristic
        // value
        return location;
    }

    class Node {
        int location, g, f;

        public Node(int location, int g, int h) {
            this.location = location;
            this.g = g; // Cost from start to current node
            this.f = g + h; // Total cost
        }
    }
}
