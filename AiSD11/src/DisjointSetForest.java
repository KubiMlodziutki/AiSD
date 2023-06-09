import java.util.ArrayList;

public class DisjointSetForest implements DisjointSetDataStructure {

	private class Element {
		int rank;
		int parent;
	}

	Element[] arr;

	public DisjointSetForest(int size) {
		arr = new Element[size];
		for (int i = 0; i < size; i++) {
			arr[i] = new Element();
			arr[i].rank = 0;
			arr[i].parent = i;
		}
	}

	@Override
	public void makeSet(int item) {
		arr[item].rank = 0;
		arr[item].parent = item;
	}

	@Override
	public int findSet(int item) {
		if (arr[item].parent != item)
			arr[item].parent = findSet(arr[item].parent);
		return arr[item].parent;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		Element rootA = arr[findSet(itemA)];
		Element rootB = arr[findSet(itemB)];

		if (rootA == rootB){
			return false;
		}

		if (rootA.rank > rootB.rank){
			rootB.parent = rootA.parent;
		}
		else if (rootA.rank < rootB.rank){
			rootA.parent = rootB.parent;
		}
		else {
			rootA.parent = rootB.parent;
			rootB.rank++;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Disjoint sets as forest:\n");
		for (int i = 0; i < arr.length; i++){
			builder.append(i).append(" -> ").append(arr[i].parent).append("\n");
		}

		builder = new StringBuilder(builder.substring(0, builder.length() - 1));
		return builder.toString();
	}

	@Override
	public int countSets() {
		ArrayList<Integer> representants = new ArrayList<>();
		int setsCounter = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!representants.contains(findSet(i))) {
				representants.add(findSet(i));
				setsCounter += 1;
			}
		}
		return setsCounter;
	}
}