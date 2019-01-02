package dataStructure;

import interfaces.PrefixToy;

public class Dictionery implements PrefixToy {
	Words root;

	@Override
	public void addWord(String word) {
		Words wordsTmp = root;
		Words lastWord = root;
		boolean foundProperWords = false;
		if (word != null && !word.isEmpty()) {
			if (root == null) {
				root = new Words(word);
				return;
			}
			while (!foundProperWords) {
				if (wordsTmp != null && Math.abs(wordsTmp.getFirst().getLetter() - word.charAt(0))  != 0  ) {
					lastWord = wordsTmp;
					wordsTmp = wordsTmp.getNextWord();
				} else if (wordsTmp != null) {
					wordsTmp.addWord(word);
					foundProperWords = true;
				} else {
					lastWord.setNextWord(new Words(word));
					foundProperWords = true;
				}
			}
		}

	}

	@Override
	public void printWordByPrefix(String prefix) {
		Words wordsTmp = root;
		boolean foundProperWords = false;
		if (prefix != null && !prefix.isEmpty()) {
			while (!foundProperWords) {
				if (wordsTmp != null && wordsTmp.getFirst().getLetter() != prefix.charAt(0)) {
					wordsTmp = wordsTmp.getNextWord();
				} else if (wordsTmp != null) {
					foundProperWords = true;
					wordsTmp.getFirst().printWordByPrefix(prefix);
				}else{
					System.out.println(" There no such prefix");
					break;
				}
			}
		} else {
			System.out.println(" No Empty prefix");
		}

	}

	public Words getRoot() {
		return root;
	}




}
