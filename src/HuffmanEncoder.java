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
