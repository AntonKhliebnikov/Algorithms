package algorithms.navigator;

public class NavigatorApp {
    public static void main(String[] args) {
        GraphLoader loader = new GraphLoader("src/main/resources/france.csv");
        Graph graph = loader.loadGraph();
        ShortestPathFinder shortestPathFinder = new ShortestPathFinder(graph);
        shortestPathFinder.findShortestPath("Нант", "Мец");
        graph.print();
        System.out.println(graph.getCities());
    }
}
