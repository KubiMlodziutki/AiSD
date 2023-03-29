import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayUnorderedListWithHeadAndTail<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name;
        link=new TwoWayUnorderedListWithHeadAndTail<Link>();
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

                String charStr = new String(chars);
                if (charStr.equalsIgnoreCase("link=")) {
                    StringBuilder builder = new StringBuilder();
                    i++;
                    while(i < line.length() && !Character.isWhitespace(line.charAt(i))) {
                        builder.append(line.charAt(i));
                        i++;
                    }
                    String linkString = builder.toString();
                    if (correctLink(linkString)) {
                        link.add(new Link(linkString));
                    }
                }
            }
        }
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    public static boolean correctLink(String link) {
        if (link.length() == 0 || !Character.isLetter(link.charAt(0)) || link.charAt(0) == '_') {
            return false;
        }
        for (int i = 1; i < link.length(); i++) {
            boolean isCorrect = Character.isLetterOrDigit(link.charAt(i)) || link.charAt(i) == '_';
            if (!isCorrect) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String retStr="Document: "+name;
        return retStr+link.toString();
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        return retStr+link.toStringReverse();
    }


}
