import java.util.*;

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

	public String DijkstraSSSP(String startVertexStr) {
		if (!name2Int.containsKey(startVertexStr)){
			return "error\n";
		}

		int startVertexValue = name2Int.get(startVertexStr);
		int[] distance = new int[arr.length];
		int[] previous = new int[arr.length];
		boolean[] visited = new boolean[arr.length];

		for (int i = 0; i < arr.length; i++) {
			distance[i] = Integer.MAX_VALUE;
			previous[i] = -1;
		}

		distance[startVertexValue] = 0;

		for (int i = 0; i < arr.length; i++) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int j = 0; j < arr.length; j++) {
				if (distance[j] < min && !visited[j]) {
					min = distance[j];
					minIndex = j;
				}
			}

			if (minIndex == -1){
				break;
			}

			visited[minIndex] = true;

			for (int j = 0; j < arr.length; j++) {
				if (arr[minIndex][j] > 0 && !visited[j]) {
					int newDistance = distance[minIndex] + arr[minIndex][j];
					if (newDistance < distance[j]) {
						distance[j] = newDistance;
						previous[j] = minIndex;
					}
				}
			}
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				builder.append("no path to ").append(int2Name.get(i)).append("\n");
			} else {

				int current = i;
				ArrayList<Integer> path = new ArrayList<>();

				while (current != -1) {
					path.add(current);
					current = previous[current];
				}

				for (int j = path.size() - 1; j >= 0; j--) {
					builder.append(int2Name.get(path.get(j)));
					if (j != 0){
						builder.append("->");
					}
				}

				builder.append("=").append(distance[i]).append("\n");
			}
		}
		return builder.toString();
	}
}