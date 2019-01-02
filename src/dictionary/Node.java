package dictionary;

/**
 * Created by Pavel on 11-Jul-16.
 */
public class Node {
    private static final int ABC_LENGTH = 27; //Length of alphabet + '\0'
    private static final int ASCII_A = 'a';

    private Character value = null;
    private Node[] next = new Node[Node.ABC_LENGTH];

    public Node(Character value){
        this.value = value;
    }

    public Character getValue() {
        return value;
    }

    public Node[] getNext() {
        return next;
    }

    public void addNextChar(char ch){
        int index = indexOf(ch);

        if (!existsInNext(index)){
            next[index] = new Node(ch);
        }
    }

    public Node getNextWithChar(char ch){
        return next[indexOf(ch)];
    }

    public Node findNodeByPrefix(char[] chars) {
        Node current = this;

        for (char ch : chars){
            current = current.getNextWithChar(ch);
            if (current == null)
                break;
        }
        return current;
    }

    private int indexOf(char ch){
        return ch == '\0' ? (ABC_LENGTH-1) : (ch-ASCII_A);
    }

    private boolean existsInNext(int index) {
        return next[index] != null;
    }
}
