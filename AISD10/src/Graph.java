import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;

public class Graph {
    int[][] arr;
    HashMap<String, Integer> name2Int;
    HashMap<Integer, String> int2Name;

    public Graph(SortedMap<String, Document> internet) {
        arr = new int[internet.size()][internet.size()];

        name2Int = new HashMap<>();
        int2Name = new HashMap<>();

        int i = 0;
        for (String internetStrValue : internet.keySet()) {
            name2Int.put(internetStrValue, i);
            int2Name.put(i, internetStrValue);
            i++;
        }

        for (i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                Link link = internet.get(int2Name.get(i)).link.get(int2Name.get(j));

                if (link != null) {
                    arr[i][j] = link.weight;
                } else if (i == j) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = -1;
                }
            }
        }
    }

    public String bfs(String start) {
        if (!name2Int.containsKey(start)){
            return null;
        }

        StringBuilder builder = new StringBuilder();

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> wasVisited = new HashSet<>();

        queue.add(name2Int.get(start));

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (!wasVisited.contains(current)) {
                wasVisited.add(current);
                builder.append(int2Name.get(current)).append(", ");
                for (int i = 0; i < arr.length; i++) {
                    if (arr[current][i] > 0 && !wasVisited.contains(i)) {
                        queue.add(i);
                    }
                }
            }
        }

        return builder.substring(0, builder.length() - 2);
    }

    public String dfs(String start) {
        if (!name2Int.containsKey(start)){
            return null;
        }

        Set<Integer> visited = new HashSet<>();
        String result = dfs(name2Int.get(start), visited);
        return result.substring(0, result.length() - 2);
    }

    private String dfs(int start, Set<Integer> visited) {
        StringBuilder builder = new StringBuilder();

        if (visited.contains(start)) {
            return builder.toString();
        }

        visited.add(start);
        builder.append(int2Name.get(start)).append(", ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[start][i] > 0 && !visited.contains(i)) {
                builder.append(dfs(i, visited));
            }
        }
        return builder.toString();
    }

    public int connectedComponents() {
        DisjointSetForest djsf = new DisjointSetForest(arr.length);

        for (int i = 0; i < arr.length; i++) {
            djsf.makeSet(i);
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 0) {
                    djsf.union(i, j);
                }
            }
        }
        return djsf.countSets();
    }
}