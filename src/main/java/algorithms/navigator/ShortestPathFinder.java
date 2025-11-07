package algorithms.navigator;

import java.util.*;

public class ShortestPathFinder {
    private final Graph graph;

    public ShortestPathFinder(Graph graph) {
        this.graph = graph;
    }

    public void findShortestPath(String sourceCity, String targetCity) {
        if (sourceCity == null || targetCity == null) {
            System.out.println("Пути нет");
            return;
        }

        if (!graph.containsCity(sourceCity) || !graph.containsCity(targetCity)) {
            System.out.println("Пути нет");
            return;
        }

        int infinity = Integer.MAX_VALUE;
        Map<String, Integer> distanceByCity = new HashMap<>();
        for (String city : graph.getCities()) {
            distanceByCity.put(city, infinity);
        }
        
        distanceByCity.put(sourceCity, 0);

        Map<String, String> previousCity = new HashMap<>();
        Set<String> visitedCities = new HashSet<>();
        PriorityQueue<DistanceToCity> distanceToCityQueue = new PriorityQueue<>();
        distanceToCityQueue.add(new DistanceToCity(sourceCity, 0));

        while (!distanceToCityQueue.isEmpty()) {
            DistanceToCity current = distanceToCityQueue.poll();
            String currentCity = current.city;

            if (visitedCities.contains(currentCity)) {
                continue;
            }
            visitedCities.add(currentCity);

            if (currentCity.equals(targetCity)) {
                System.out.println("Кратчайший путь до цели найден!");
                break;
            }

            List<Graph.Edge> neighbors = graph.getNeighbors(currentCity);
            if (neighbors == null) {
                System.out.println("Пути нет!");
                return;
            }
            for (Graph.Edge outgoingEdge: neighbors) {
                relaxEdge(outgoingEdge, distanceByCity, currentCity, infinity, previousCity, distanceToCityQueue);
            }
        }

        getTotalDistance(targetCity, distanceByCity, infinity);
        restoringPath(sourceCity, targetCity, previousCity);
    }

    private static void restoringPath(String sourceCity, String targetCity, Map<String, String> previousCity) {
        List<String> reversedPath = new ArrayList<>();
        String step = targetCity;
        reversedPath.add(step);
        while (!step.equals(sourceCity)) {
            step = previousCity.get(step);
            reversedPath.add(step);
        }
        Collections.reverse(reversedPath);
        System.out.println("Маршрут: " + reversedPath);
    }

    private static void getTotalDistance(String targetCity, Map<String, Integer> distanceByCity, int infinity) {
        int totalDistance = distanceByCity.getOrDefault(targetCity, infinity);
        if (totalDistance == infinity) {
            System.out.println("Пути нет!");
            return;
        }
        System.out.println("Расстояние: " + totalDistance + " км.");
    }

    private static void relaxEdge(Graph.Edge outgoingEdge, Map<String, Integer> distanceByCity,
                                  String currentCity, int infinity, Map<String, String> previousCity,
                                  PriorityQueue<DistanceToCity> distanceToCityQueue) {
        int currentDistance = distanceByCity.get(currentCity);
        String neighbor = outgoingEdge.getTargetCity();
        int weightKm = outgoingEdge.getDistance();
        int minDistanceCandidate = currentDistance + weightKm;
        int bestKnownDistance = distanceByCity.getOrDefault(neighbor, infinity);

        if (minDistanceCandidate < bestKnownDistance) {
            distanceByCity.put(neighbor, minDistanceCandidate);
            previousCity.put(neighbor, currentCity);
            distanceToCityQueue.add(new DistanceToCity(neighbor, minDistanceCandidate));
        }
    }

    private static class DistanceToCity implements Comparable<DistanceToCity> {
        String city;
        int distanceFromSource;

        public DistanceToCity(String city, int distanceFromSource) {
            this.city = city;
            this.distanceFromSource = distanceFromSource;
        }

        @Override
        public int compareTo(DistanceToCity other) {
            return Integer.compare(this.distanceFromSource, other.distanceFromSource);
        }
    }
}