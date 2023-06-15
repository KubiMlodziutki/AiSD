package dsaa.lab12;

import java.util.Scanner;

public class LinesReader {
	String concatLines(int howMany, Scanner scanner) {
		StringBuffer bufferBuilder = new StringBuffer();
		for (int i = howMany; i > 0; i--) {
			bufferBuilder.append(scanner.nextLine());
		}
		return bufferBuilder.toString();
	}

}
