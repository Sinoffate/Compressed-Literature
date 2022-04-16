import java.io.IOException;

public class HuffmanEncoder {
    private String inputFileName = "./WarAndPeace.txt";
    private String outputFileName = "./WarAndPeace-compressed.bin";
    private String codesFileName = "./WarAndPeace-codes.txt";
    private BookReader book;
    private MyOrderedList<FrequencyNode> frequencies;
    private HuffmanNode huffmanTree;
    private MyOrderedList<CodeNode> codes;
    private byte[] encodedText;

    private class FrequencyNode implements Comparable<FrequencyNode> {
        public Character character;
        public Integer count;
        public int compareTo(FrequencyNode other) {
            return this.character.compareTo(other.character);
        }
        public String toString() {
            return character + ":" + count;
        }

    }

    private class HuffmanNode implements Comparable<HuffmanNode> {
        public Character character;
        public Integer weight;
        public HuffmanNode left;
        public HuffmanNode right;

        public HuffmanNode(Character character, Integer weight) {
            this.character = character;
            this.weight = weight;
        }

        public HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.left = left;
            this.right = right;
            this.weight = left.weight + right.weight;
        }
        public int compareTo(HuffmanNode other) {
            return this.weight.compareTo(other.weight);
        }

        public String toString() {
            return this.character + ":" + this.weight;
        }
    }

    private class CodeNode implements Comparable<CodeNode> {
        public Character character;
        public String code;

        public int compareTo(CodeNode other) {
            return this.character.compareTo(other.character);
        }
        public String toString() {
            return character + ":" + code;
        }
    }

    public HuffmanEncoder() throws IOException {
        frequencies = new MyOrderedList<>();
        codes = new MyOrderedList<>();
        book = new BookReader(inputFileName);
    }

    private void countFrequencies() {

    }

    private void buildTree() {

    }

    private void extractCodes(HuffmanNode root, String code) {

    }

    private void encode() {

    }

    private void writeFiles() {

    }




}
