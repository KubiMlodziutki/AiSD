package dsaa.lab12;

import java.util.LinkedList;

public class Automaton implements IStringMatcher {

	public final static int limit = 256;

	@Override
	public LinkedList<Integer> validShifts(String pattern, String text) {
		int patternLength = pattern.length();
		int textLength = text.length();

		LinkedList<Integer> found = new LinkedList<>();

		int[][] stateArr = new int[patternLength + 1][limit];

		for (int i = 0; i <= patternLength; ++i) {
			for (int j = 0; j < limit; ++j) {
				stateArr[i][j] = nextState(pattern, i, j);
			}
		}

		int nextState = 0;
		for (int i = 0; i < textLength; i++) {
			nextState = stateArr[nextState][text.charAt(i)];
			if (nextState == patternLength) {
				found.add(i - patternLength + 1);
			}
		}
		return found;
	}

	public int nextState(String pattern, int patternIndex, int character) {
		int patternLength = pattern.length();

		if (patternIndex < patternLength && character == pattern.charAt(patternIndex)) {
			return patternIndex + 1;
		}
		int i;
		for (int next = patternIndex; next > 0; next--) {
			if (pattern.charAt(next - 1) == character) {
				for (i = 0; i < next - 1; i++) {
					if (pattern.charAt(i) != pattern.charAt(patternIndex - next + 1 + i)) {
						break;
					}
				}

				if (i == next - 1) {
					return next;
				}
			}
		}
		return 0;
	}
}
