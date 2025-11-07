package algorithms.navigator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphLoader {
    private final String filePath;

    public GraphLoader(String filePath) {
        this.filePath = filePath;
    }

    public Graph loadGraph() {
        Graph graph = new Graph();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] partsLine = line.split(",");
                String fromCity = partsLine[0].trim();
                String toCity = partsLine[1].trim();
                int distance = Integer.parseInt(partsLine[2].trim());
                graph.addEdge(fromCity, toCity, distance);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }
}
