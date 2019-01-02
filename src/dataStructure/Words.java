package dataStructure;

public class Words {
	private Words nextWord;
	private Symbol first;

	public Words(String word) {
		super();
		if (word != null && !word.isEmpty()) {
				this.first = new Symbol(word,null);		}
	}

	public void addWord(String word) {
		first.addWord(word);
	}

	public void printWord(String before,StringBuffer sb){
		Symbol tmp =first;
		sb.setLength(0);
		sb.append(before);
		while(tmp!=null){
			sb.append(tmp.getLetter());
			tmp =tmp.getNext();
		}
		System.out.println(sb.toString()+", ");
	}

	public Words getNextWord() {
		return nextWord;
	}

	public void setNextWord(Words nextWord) {
		this.nextWord = nextWord;
	}

	public Symbol getFirst() {
		return first;
	}

	public void setFirst(Symbol first) {
		this.first = first;
	}



}
