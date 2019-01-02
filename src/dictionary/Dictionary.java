package dictionary;

/**
 * Created by Pavel on 11-Jul-16.
 */
public class Dictionary {
    private Node root = new Node(null);

    public void addWord(String word){
        char[] chars = word.toCharArray();

        Node current = root;
        for (char ch : chars){
            current.addNextChar(ch);
            current = current.getNextWithChar(ch);
        }
        current.addNextChar('\0');
    }

    public void printWordsByPrefix(String prefix){
        char[] chars = prefix.toCharArray();

        Node start = root.findNodeByPrefix(chars);

        //Nothing will be printed if dictionary doesn't contain words with prefix
        if (start != null)
            appendAndPrintArray(start, chars);
    }

    private void appendAndPrintArray(Node current, char[] chars) {
        for (Node node : current.getNext()){
            if (node != null){
                if (node.getValue() == '\0') {
                    System.out.println(chars);
                    break;
                }
                char[] appended = appendAndGet(chars, node.getValue());
                appendAndPrintArray(node, appended);
            }
        }
    }

    private char[] appendAndGet(char[] chars, char value) {
        char[] result = new char[chars.length + 1];

        for (int i = 0; i < chars.length; ++i){
            result[i] = chars[i];
        }
        result[chars.length] = value;
        return result;
    }
}
