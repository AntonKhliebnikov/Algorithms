package algorithms.navigator;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private final Map<String, List<Edge>> adjacencyList;

    public static class Edge {
        private final String targetCity;
        private final int distance;

        public Edge(String targetCity, int distance) {
            this.targetCity = targetCity;
            this.distance = distance;
        }

        public String getTargetCity() {
            return targetCity;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return "Расстояние до города " + targetCity + " = " + distance + "км";
        }
    }

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(String fromCity, String toCity, int distance) {
        adjacencyList.computeIfAbsent(fromCity, key -> new ArrayList<>())
                .add(new Edge(toCity, distance));
        adjacencyList.computeIfAbsent(toCity, key -> new ArrayList<>());
    }

    public List<Edge> getNeighbors(String city) {
        return adjacencyList.getOrDefault(city, Collections.emptyList());
    }

    public boolean containsCity(String city) {
        return adjacencyList.containsKey(city);
    }

    public List<String> getCities() {
        Set<String> cities = adjacencyList.keySet();
        return cities.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public void print() {
        adjacencyList.forEach((city, edges) -> {
            System.out.print(city + " -> ");
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                System.out.print(edge.getTargetCity() + " (" + edge.getDistance() + " км)");
                if (i < edges.size() - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println(".");
                }
            }
        });
    }
}