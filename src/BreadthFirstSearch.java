import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {
    private Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();

    public BreadthFirstSearch(Vertex<V> start, Vertex<V> end) {
        super(start, end);
    }

    @Override
    public List<Vertex<V>> getPath() {
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            if (current.equals(end)) break;

            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return reconstructPath();
    }

    private List<Vertex<V>> reconstructPath() {
        List<Vertex<V>> path = new LinkedList<>();
        Vertex<V> current = end;

        while (current != null && !current.equals(start)) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        if (current == null) return Collections.emptyList();
        path.add(0, start);
        return path;
    }
}
