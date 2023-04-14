import java.util.ListIterator;
import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }
    public void load(Scanner scan) {
        String line;
        while (!(line = scan.nextLine()).equals("eod")) {
            char[] chars = new char[5];
            for (int i = 0; i < line.length(); i++) {
                chars[0] = chars[1];
                chars[1] = chars[2];
                chars[2] = chars[3];
                chars[3] = chars[4];
                chars[4] = line.charAt(i);
                String charSTR = new String(chars);
                if (charSTR.equalsIgnoreCase("link=")) {
                    StringBuilder builder = new StringBuilder();
                    i += 1;
                    while(i < line.length() && !Character.isWhitespace(line.charAt(i)) && line.charAt(i) != '(') {
                        builder.append(line.charAt(i));
                        i++;
                    }
                    int weight = -1;
                    if (i < line.length() && line.charAt(i) == '(') {
                        StringBuilder weightBuilder = new StringBuilder();
                        i++;

                        while (i < line.length() && Character.isDigit(line.charAt(i))) {
                            weightBuilder.append(line.charAt(i));
                            i++;
                        }
                        if (line.charAt(i) != ')') {
                            weight = -2;
                        }
                        if (line.charAt(i) == ')') {
                            String weightString = weightBuilder.toString();
                            if (weightString.length() == 0) {
                                weight = -2;
                            }
                            boolean onlyDigits = true;
                            for (int j = 0; j < weightString.length(); j++) {
                                if (!Character.isDigit(weightString.charAt(j))) {
                                    onlyDigits = false;
                                }
                            }
                            if (weightString.length() > 0 && onlyDigits) {
                                weight = Integer.parseInt(weightString);
                            }
                        }
                    }
                    String linkString = builder.toString();
                    if (correctLink(linkString)) {
                        linkString = linkString.toLowerCase();
                        if (weight == -1) {
                            link.add(createLink(linkString));
                        } else if (weight >= 0)
                            link.add(createLink(linkString, weight));
                    }
                }
            }
        }
    }

    public static boolean isCorrectId(String id) {
        if (!Character.isLetter(id.charAt(0))){
            return false;
        }
        return true;
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the beginning)
    private static boolean correctLink(String link) {
        if (link.length() == 0 || !Character.isLetter(link.charAt(0)) || link.charAt(0) == '_') {
            return false;
        }
        for (int i = 1; i < link.length(); i++) {
            boolean isCorrect = Character.isLetterOrDigit(link.charAt(i));
            if (!isCorrect) {
                return false;
            }
        }
        return true;
    }
    public static Link createLink(String link) {
        return new Link(link);
    }
    public static Link createLink(String link, int weight) {
        return new Link(link, weight);
    }

    @Override
    public String toString() {
        String retStr="Document: "+name;
        if (link.size == 0){
            return retStr;
        }
        StringBuilder builder = new StringBuilder(retStr);
        ListIterator<Link> iterate = link.listIterator();
        int i = 0;
        while(iterate.hasNext()) {
            if (i % 10 == 0) {
                builder.append("\n").append(iterate.next()).append(" ");
            } else {
                builder.append(iterate.next()).append(" ");
            }
            i++;
        }
        return builder.substring(0, builder.length()-1);
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        if (link.size == 0){
            return retStr;
        }
        ListIterator<Link> iter=link.listIterator();
        while(iter.hasNext())
            iter.next();
        StringBuilder builder = new StringBuilder(retStr);
        int i = 0;
        while(iter.hasPrevious()){
            if (i % 10 == 0) {
                builder.append("\n").append(iter.previous()).append(" ");
            } else {
                builder.append(iter.previous()).append(" ");
            }
            i++;
        }
        return builder.substring(0, builder.length()-1);
    }

    public int[] getWeights(){
        int[] weightArr = new int[link.size];
        for (int i = 0; i < link.size; i++){
            weightArr[i] = link.get(i).getWeight();
        }
        return  weightArr;
    }

    public static void showArray(int[] weightArray) {
        for (int i = 0; i < weightArray.length; i++) {
            if (i < weightArray.length - 1){
                System.out.print(weightArray[i] + " ");
            }
            else {
                System.out.println(weightArray[i]);
            }
        }
        System.out.println();
    }

    private void swap(int[] arr, int toSwap1, int toSwap2) {
        int temp = arr[toSwap1];
        arr[toSwap1] = arr[toSwap2];
        arr[toSwap2] = temp;
    }

    public void insertSort(int[] weightArray) {
        showArray(weightArray);
        for (int i = weightArray.length - 2; i >= 0; i--) {
            int j = i;

            while (j < weightArray.length - 1 && weightArray[j] > weightArray[j + 1]) {
                swap(weightArray, j, j + 1);
                j++;
            }
            showArray(weightArray);
        }
    }

    public void selectSort(int[] weightArray) {
        showArray(weightArray);
        for (int i = weightArray.length - 1; i > 0; i--) {
            int maxValuePos = i;

            for (int j = 0; j < i; j++) {
                if (weightArray[j] > weightArray[maxValuePos]) {
                    maxValuePos = j;
                }
            }

            swap(weightArray, i, maxValuePos);
            showArray(weightArray);
        }
    }

    public void bubbleSort(int[] weightArray) {
        showArray(weightArray);
        for (int i = 0; i < weightArray.length - 1; i++) {
            for (int j = weightArray.length - 1; j > i; j--) {
                if (weightArray[j - 1] > weightArray[j]) {
                    swap(weightArray, j - 1, j);
                }
            }
            showArray(weightArray);
        }
    }
}