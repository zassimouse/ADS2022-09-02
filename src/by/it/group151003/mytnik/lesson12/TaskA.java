package by.it.group151003.mytnik.lesson12;

import java.util.*;

public class TaskA {
    public static final int AMOUNT_OF_VERTICES = 8;
    private static final char START_VERTEX = 'A';
    public List<List<Node>> graph = new ArrayList<>();
    public int[] dist;
    public boolean[] visited;

    public static class Node {
        public int dest;
        public int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public void dijkstra(int start) {
        dist = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            dist[i] = 100;
        }
        visited = new boolean[graph.size()];
        dist[start] = 0;

        PriorityQueue<Node> H = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

        H.offer(new Node(start, 0));

        while (!H.isEmpty()) {
            Node node = H.poll();
            int curr = node.dest;
            if (visited[curr])
                continue;

            visited[curr] = true;
            for (int i = 0; i < graph.get(curr).size(); i++) {
                int next = graph.get(curr).get(i).dest;
                int cost = graph.get(curr).get(i).cost;

                if (dist[next] > dist[curr] + cost) {
                    dist[next] = dist[curr] + cost;
                    H.offer(new Node(next, dist[next]));
                }
            }
        }
    }

    public static void main(String[] args) {
        TaskA taskA = new TaskA();
        for (int i = 0; i < AMOUNT_OF_VERTICES; i++) {
            taskA.graph.add(new ArrayList<>());
        }

        taskA.graph.get(0).add(new Node(1, 1));
        taskA.graph.get(0).add(new Node(4, 4));
        taskA.graph.get(0).add(new Node(5, 8));

        taskA.graph.get(1).add(new Node(5, 6));
        taskA.graph.get(1).add(new Node(6, 6));
        taskA.graph.get(1).add(new Node(2, 2));

        taskA.graph.get(2).add(new Node(6, 2));
        taskA.graph.get(2).add(new Node(3, 1));

        taskA.graph.get(3).add(new Node(6, 1));
        taskA.graph.get(3).add(new Node(7, 4));

        taskA.graph.get(4).add(new Node(5, 5));

        taskA.graph.get(6).add(new Node(5, 1));
        taskA.graph.get(6).add(new Node(7, 1));

        taskA.dijkstra(0);

        for (int i = 0; i < taskA.dist.length; i++) {
            System.out.println("The shortest path from " + START_VERTEX + " to " + (char) (START_VERTEX + i) + " = " + taskA.dist[i]);
        }
    }
}