import java.io.*;
import java.util.Scanner;

public class HuffmanEncoder {
    private final String inputFileName = "./WarAndPeace.txt";
    private final String outputFileName = "./WarAndPeace-compressed.bin";
    private final String codesFileName = "./WarAndPeace-codes.txt";
    private BookReader book;
    private MyOrderedList<FrequencyNode> frequencies;
    private HuffmanNode huffmanTree;
    private MyOrderedList<CodeNode> codes;
    private byte[] encodedText;

    /*
     * HuffmanEncoder - The constructor should call the helper methods in the correct
     * order to carry out Huffman’s algorithm.
     */
    public HuffmanEncoder() throws IOException {
        frequencies = new MyOrderedList<>();
        codes = new MyOrderedList<>();
        book = new BookReader(inputFileName);
        countFrequencies();
        buildTree();
        encode();
        writeFiles();
    }
    private class FrequencyNode implements Comparable<FrequencyNode> {
        public Character character;
        public Integer count;
        public FrequencyNode(Character character, Integer count) {
            this.character = character;
            this.count = count;
        }
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

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }
    }

    private class CodeNode implements Comparable<CodeNode> {
        public Character character;
        public String code;
        public CodeNode(Character character, String code) {
            this.character = character;
            this.code = code;
        }

        public int compareTo(CodeNode other) {
            return this.character.compareTo(other.character);
        }
        public String toString() {
            return character + ":" + code;
        }
    }


    /**
     * countFrequency - This method counts the frequency of each character in the book
     * and stores it in frequencies.
     * ○ Iterate through the text character by character maintaining counts in
     * frequencies.
     * ○ The counts are stored in FrequencyNodes.
     * ○ It should output the time it takes to count the frequencies.
     */
    private void countFrequencies() {
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(book.book);
        scanner.useDelimiter("");
        while (scanner.hasNext()) {
            String word = scanner.next();
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                if (c != ' ') {
                    FrequencyNode node = frequencies.binarySearch(new FrequencyNode(c, 0));
                    if (node == null) {
                        frequencies.add(new FrequencyNode(c, 1));
                    } else {
                        node.count++;
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to count frequencies: " + (endTime - startTime) + "ms");
        scanner.close();
    }

    /**
     * buildTree - This method builds the Huffman tree and extracts the codes from it,
     * storing them in codes. It does so by carrying out these steps:
     * ○ Create a single Huffman node for each character weighted by its count.
     * ○ Add all the nodes to a priority queue.
     * ○ Merge Huffman nodes until only a single tree remains.
     * ○ Store the root of the remaining tree in huffmanTree.
     * ○ Extract the codes from the tree and store them in codes using the recursive
     * helper function like this:
     * extractCodes(huffmanTree,"");
     * ○ It should output the time it takes to build the tree and extract the codes.
     */
    private void buildTree() {
        long startTime = System.currentTimeMillis();
        MyPriorityQueue<HuffmanNode> pq = new MyPriorityQueue<>();
        for (int i = 0; i < frequencies.size(); i++) {
            FrequencyNode node = frequencies.get(i);
            pq.insert(new HuffmanNode(node.character, node.count));
        }
        while (pq.size() > 1) {
            HuffmanNode left = pq.removeMin();
            HuffmanNode right = pq.removeMin();
            HuffmanNode parent = new HuffmanNode(left, right);
            pq.insert(parent);
        }
        huffmanTree = pq.removeMin();
        extractCodes(huffmanTree, "");
        long endTime = System.currentTimeMillis();
        System.out.println("Time to build tree and extract codes: " + (endTime - startTime) + "ms");
    }

    /**
     * extractCodes - A recursive method that traverses the Huffman tree to extract the
     * codes stored in it.
     * ○ This method will conduct a recursive depth-first traversal of the Huffman tree.
     * ○ The path of left and right moves is stored in the code parameter by adding “0” for
     * left traversals and “1” for right traversals.
     * ○ When a leaf is reached the code is stored in the codes list.
     */
    private void extractCodes(HuffmanNode root, String code) {
        if (root.isLeaf()) {
            codes.add(new CodeNode(root.character, code));
        } else {
            extractCodes(root.left, code + "0");
            extractCodes(root.right, code + "1");
        }
    }

    /**
     * encode - Uses the book and codes to create encodedText.
     * ○ For each character in book.book, append the code to an intermediate string.
     * ○ Convert the string of character into a list of bytes and store it in encodedText.
     * ○ You can convert a string of ‘0’s and ‘1’s to a byte with this line:
     * byte b = (byte)Integer.parseInt(str,2);
     * ○ It should output the time it takes to encode the text.
     */
    private void encode() {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (char c : book.words.toString().toCharArray()) {
            for (int j = 0; j < codes.size(); j++) {
                if (codes.get(j).character == c) {
                    sb.append(codes.get(j).code);
                    break;
                }
            }
        }
        String str = sb.toString();
        encodedText = new byte[str.length() / 8];
        for (int i = 0; i < encodedText.length; i++) {
            String sub = str.substring(i * 8, i * 8 + 8);
            encodedText[i] = (byte) Integer.parseInt(sub, 2);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to encode text: " + (endTime - startTime) + "ms");
    }

    /**
     * writeFiles() - Writes the contents of encodedText to the outputFileName
     * and the contents of codes to codesFileName.
     * ○ It should output the time it takes to write the files.
     */
    private void writeFiles() {
        long startTime = System.currentTimeMillis();
        try {
            FileOutputStream fos = new FileOutputStream(outputFileName);
            fos.write(encodedText);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = new FileOutputStream(codesFileName);
            for (int i = 0; i < codes.size(); i++) {
                fos.write((codes.get(i).character + " " + codes.get(i).code + "\n").getBytes());
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Writing compressed file... " + encodedText.length +
                           " bytes written in " + (endTime - startTime) + "ms");


    }
}
