public class DisjointSetLinkedList implements DisjointSetDataStructure {

    private class Element{
        int representant;
        int next;
        int length;
        int last;
    }

    private static final int NULL=-1;

    Element[] arr;

    public DisjointSetLinkedList(int size) {
        arr = new Element[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Element();
            arr[i].representant = i;
            arr[i].next = NULL;
            arr[i].length = 1;
            arr[i].last = i;
        }
    }

    @Override
    public void makeSet(int item) {
        arr[item].representant = item;
        arr[item].next = NULL;
        arr[item].length = 1;
        arr[item].last = item;
    }

    @Override
    public int findSet(int item) {
        return arr[item].representant;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        int rootA = findSet(itemA);
        int rootB = findSet(itemB);

        if (rootA == rootB){
            return false;
        }

        if (arr[rootA].length >= arr[rootB].length) {
            arr[arr[rootA].last].next = rootB;
            arr[rootA].last = arr[rootB].last;
            arr[rootA].length += arr[rootB].length;

            for (int i = rootB; i != NULL; i = arr[i].next){
                arr[i].representant = rootA;
            }

        } else {
            arr[arr[rootB].last].next = rootA;
            arr[rootB].last = arr[rootA].last;
            arr[rootB].length += arr[rootA].length;

            for (int i = rootA; i != NULL; i = arr[i].next){
                arr[i].representant = rootB;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder retStr = new StringBuilder("Disjoint sets as linked list:\n");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].representant == i) {
                retStr.append(i);
                int next = arr[i].next;
                while (next != NULL) {
                    retStr.append(", ").append(next);
                    next = arr[next].next;
                }
                if (i != arr.length - 2)
                    retStr.append("\n");
            }
        }
        return retStr.toString();
    }

}