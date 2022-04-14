import java.io.*;
import java.util.Scanner;

public class BookReader {
    public String book;
    public MyLinkedList<String> words;

    public BookReader(String filename) throws IOException {
        words = new MyLinkedList<>();
        readBook(filename);
        parseWords();
    }

    public void readBook(String filename) throws IOException {
        File file = new File(filename);
        FileReader fileRead = new FileReader(file);
        BufferedReader bookRead = new BufferedReader(fileRead);
        StringBuilder sBook = new StringBuilder();
        int c;

        long start = System.currentTimeMillis();
        while ((c = bookRead.read()) != -1) {
            sBook.append((char) c);
        }
        long finish = System.currentTimeMillis();

        bookRead.close();
        fileRead.close();

        book = sBook.toString();
        System.out.println();
        ;
        System.out.println("Time to read file: " + (finish - start) + " milliseconds");
        System.out.println("Number of characters in file: " + book.length());


    }

    public void parseWords() { // test file is 25 words
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(book);
        scan.useDelimiter("");

        long start = System.currentTimeMillis();
        while(scan.hasNext()) {
            String word = scan.next();
            Character ch = word.charAt(0);
            if ((ch.compareTo('A') >= 0 && ch.compareTo('Z') <= 0)
                    || (ch.compareTo('a') >= 0 && ch.compareTo('z') <= 0)
                    || (ch.compareTo('0') >= 0 && ch.compareTo('9') <= 0)
                    || ch.equals('\'')) {
                sb.append(ch);
            } else if (!sb.isEmpty()) {
                if (words.isEmpty()) {
                    words.addBefore(String.valueOf(sb));
                    words.first();
                    sb.delete(0, sb.length());
                } else {
                    words.addToLast(String.valueOf(sb));
                    sb.delete(0, sb.length());
                }
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("Number of words in file: " + words.size());
        System.out.println("Time to parse words: " + (finish - start) + " milliseconds");




    }
}
