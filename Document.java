
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Document{
    public String name;
    public OneWayLinkedList<Link> links;
    public Document(String name, Scanner scan) {
        // TODO
        links = new OneWayLinkedList<>();
        load(scan);
    }
    public void load(Scanner scan) {
        //TODO
        String line = scan.nextLine();
        while (!line.equals("eod")){
            String[] tokens = line.split("\\s+");
            for (String writtenLink : tokens) {
                if(correctLink(writtenLink)){
                    String [] readyToTrim = writtenLink.split("=");
                    System.out.println(readyToTrim[1]);
                    links.add(new Link(readyToTrim[1]));
                }

            }
            line = scan.nextLine();
        }

    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {
        boolean isCorrect = true;
        String [] allowed = {"q", "w", "e","r", "t", "y","u", "i", "o","p", "l", "k","j", "h", "g",
                "f", "d", "s","a", "z", "x","c", "v", "b","n", "m", "1","2", "3", "4","5", "6", "7","8", "9", "0","_"};
        ArrayList<String> allowedInArrayList= new ArrayList<>(List.of(allowed));
        if (link.charAt(0) == 'l' && link.charAt(1) == 'i' && link.charAt(2) == 'n'
                && link.charAt(3) == 'k' && link.charAt(4) == '=' && link.charAt(5) != '_' && link.charAt(5) != '0'
                && link.charAt(5) != '1' && link.charAt(5) != '2' && link.charAt(5) != '3' && link.charAt(5) != '4'
                && link.charAt(5) != '5' && link.charAt(5) != '6' && link.charAt(5) != '7' && link.charAt(5) != '8'
                && link.charAt(5) != '9') {
            for (int i = 5; i < link.length(); i++){
                String atIndex = String.valueOf(link.charAt(i));
                if (!allowedInArrayList.contains(atIndex)) {
                    isCorrect = false;
                    break;
                }
            }
        }
        else{
            isCorrect = false;
        }
        return isCorrect;
    }

    @Override
    public String toString() {
        return null;
    }

}