package dataStructure;

/*
 * Every Node have 4 multitude of nodes;
 * 1. Parent
 * 2.next -> it next letter of the word;
 * 3. Inner multitude ->  it multitude that go on from its letter.
 * 4. external multitude ->it is multitude that this Node belong as part of multitude same other node.
 * ------------------------------------------------------------------
 * numSimblinng-> siblings , number of nodes belong to this none.
 * Current work together with NumberNode in method nextNode and simulate Iterator
 * getNextSibling will return next Node in his collection of Nodes not external.
 *  */

public class Symbol {

	private Symbol next;
	private Symbol parent;
	private Symbol externalLink;
	private Symbol internalLink;
	private Character letter;
	private int current;
	private int numSiblinng;

	//-------------------------------------------------------------------------------------------------------------------------------------------------------
	public Symbol(String word) {
		if (word != null && word.length() > 0) {
			this.letter = word.charAt(0);
			if (word.length() > 1) {
				this.next = new Symbol(word.substring(1), this);
			}
		}
	}

	public Symbol(Character ch) {
		if (ch != null) {
			this.letter = ch;
		}
	}

	public Symbol(String word, Symbol parent) {
		if (word != null && word.length() > 0) {
			this.letter = word.charAt(0);
			this.parent = parent;
			if (word.length() > 1) {
				this.next = new Symbol(word.substring(1), this);
			}
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void addExternalSimbling(Symbol newSym) {
		final Symbol sym = getLastExternal();
		sym.setExternalLink(newSym);

	}

	public void addInternal(Symbol newSym) {
		final Symbol sym = this.getLastInternal();
		if (sym != null) {
			sym.setExternalLink(newSym);
			newSym.setParent(this);
		} else {
			this.internalLink = newSym;
			newSym.setParent(this);

		}
		this.numSiblinng++;
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public Symbol getLastExternal() {
		Symbol symTmp = this;
		while (symTmp.getExternalLink() != null) {
			symTmp = symTmp.getExternalLink();
		}
		return symTmp;
	}

	public Symbol getLastInternal() {
		Symbol symTmp = this.internalLink;
		while (symTmp != null && symTmp.getExternalLink() != null) {
			symTmp = symTmp.getExternalLink();
		}
		return symTmp;
	}

	//-----------------------------------------------------------------------------------------------------------------------
	public Symbol getSymbol(Character ch) {
		if (ch == letter) {
			return this;
		}
		Symbol sym = this.internalLink;
		while (sym != null) {
			if (sym.getLetter() == ch) {
				return sym;
			}
			sym = sym.getExternalLink();
		}
		return null;
	}

	public Symbol getSiblinng(Character ch) {
		/* if (ch == letter) { return null; } */
		Symbol sym = this.internalLink;
		while (sym != null) {
			if (sym.getLetter() == ch) {
				return sym;
			}
			sym = sym.getExternalLink();
		}
		return null;
	}

	//----------------------------------------------------------------------------------------------------------------------

	public void addWord(String word) {
		Symbol before = this;
		Symbol tmpSymbol = this;;

		for (int i = 0; i < word.length(); i++) {
			if (tmpSymbol != null && (word.charAt(i) == tmpSymbol.getLetter())) {
				before = tmpSymbol;
				tmpSymbol = tmpSymbol.getNext();
			} else if (before.getSymbol(word.charAt(i)) != null) {
				tmpSymbol = before.getSymbol(word.charAt(i--));
			} else {
				final Symbol sym = new Symbol(word.charAt(i));
				before.addInternal(sym);
				if (word.length() > i + 1) {
					sym.setNext(new Symbol(word.substring(i + 1), sym));
				}
				break;
			}
		}
	}

	//----------------------------------------------------------------------------------------------------------------------

	public Symbol getNext() {
		return next;
	}

	public void setNext(Symbol next) {
		this.next = next;
	}

	public Symbol getParent() {
		return parent;
	}

	public void setParent(Symbol parent) {
		this.parent = parent;
	}

	public Symbol getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(Symbol externalLink) {
		this.externalLink = externalLink;
	}

	public Symbol getInnerLink() {
		return internalLink;
	}

	public void setInnerLink(Symbol innerLink) {
		this.internalLink = innerLink;
	}

	public Character getLetter() {
		return letter;
	}

	public void setLetter(Character letter) {
		this.letter = letter;
	}

	//------------------------------------------------------------------------------------------------------------------------------------------------------
	public void printWordByPrefix(String prefix) {
		Symbol sym = this;
		Symbol lastSym = this;
		int i = 0;
		for (; i < prefix.length(); i++) {
			if (sym != null && sym.getLetter() == prefix.charAt(i)) {
				lastSym = sym;
				sym = sym.getNext();
			} else {
				sym = lastSym.getSiblinng(prefix.charAt(i));
				if (sym != null) {
					i--;
				} else {
					if (i < prefix.length()) {
						System.out.println("No prefix " + "\"" + prefix + "\"");
						return;
					}
				}
			}
		}
		final StringBuffer sb = new StringBuffer();

		String res = lastSym.pringTree(sb);
		if (res != null && res.length() > 0) {
			res = res.substring(0, res.length() - 1);
		}
		System.out.println(res);
		this.current = 0;
	}

	private String getAncestorString() {
		String before = "";
		Symbol sym = this.getParent();
		while (sym != null) {
			before = sym.getLetter() + before;
			sym = sym.getParent();
		}
		return before;
	}

	/* Print All tree from current Node. It is recursive method that check every
	 * next Node and every Siblings Nodes of of current Node. */
	private String pringTree(StringBuffer sb) {
		String tmp = "";
		Symbol sym = this.getNextSibling();
		String ansestorStr = getAncestorString();
		/* find ancestor letters, and, if it not in, add it to result
		 * collection; */
		if (sb.indexOf(ansestorStr + getWord()) == -1) {
			sb.append(ansestorStr + getWord() + ",");
		}

		// If Sibling not null - call printTree to sibling.
		// get word from node and add it, if it not in collection already.
		while (sym != null) {
			ansestorStr = sym.getAncestorString();
			tmp = ansestorStr + sym.getWord();
			if (sb.indexOf(tmp) == -1) {
				sb.append(tmp + ",");
			}
			tmp = sym.pringTree(sb);
			if (sb.indexOf(tmp) == -1) {
				sb.append(tmp + ",");
			}
			sym = this.getNextSibling();
		}

		sym = this.getNext();
		while (sym != null) {
			tmp = sym.pringTree(sb);
			if (sb.indexOf(tmp) == -1) {
				sb.append(tmp + ",");
			}
			sym = sym.getNext();
		}
		this.current = 0;
		return sb.toString();
	}

	private Symbol getNextSibling() {
		Symbol sym = this.getInnerLink();
		if (sym != null && current < numSiblinng) {
			for (int i = 0; i < current && current < numSiblinng; i++) {
				sym = sym.externalLink;
			}
			current++;
			return sym;
		} else {
			return null;
		}

	}

	public String getWord() {
		Symbol sym = this;
		String strTmp = "";
		while (sym != null) {
			strTmp = strTmp + sym.getLetter();
			sym = sym.getNext();
		}
		return strTmp;
	}

}
