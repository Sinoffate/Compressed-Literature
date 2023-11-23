# Compressed Literature

## Overview
This data structures project implements the Huffman coding algorithm to compress text files, using the example of Leo Tolstoy’s "War and Peace". The implementation includes building and traversing a Huffman tree, encoding and decoding messages, and handling binary file operations.

## Learning Outcomes
- Utilized a priority queue data structure for Huffman coding.
- Developed a Huffman tree for encoding and decoding text.
- Implemented efficient methods for writing to and reading from binary files.

## Features
- **Character Frequency Counting**: Counts the frequency of characters in a text file.
- **Huffman Tree Construction**: Builds a Huffman tree based on character frequency.
- **Encoding and Decoding**: Encodes text into compressed format and optionally decodes it.
- **Efficient Binary File Operations**: Writes the compressed data to a binary file, ensuring efficient storage.

## Technologies Used
- Java
- Data Structures: Priority Queues, Trees
- Algorithms: Huffman Coding
- File I/O for reading from and writing to files

## Challenges and Solutions
- **Efficiency in Large Files**: Faced challenges in efficiently handling large text files. Optimized file reading and writing processes to minimize processing time.
- **Binary Data Handling**: Implementing binary file operations was initially challenging. Developed a deeper understanding of low-level I/O operations through this project.
- **Complex Data Structures**: Utilizing and manipulating complex data structures like trees and priority queues was crucial. This enhanced my skills in understanding and applying advanced data structures.

## Sample Output
*Note: Program runtimes will vary based on machine hardware*
```
Reading input file "./WarAndPeace.txt"... 3291642 characters in 66 milliseconds.
Finding words and adding them to a linked list... in 333 milliseconds.
The linked list has a length of 570240.
Counting frequencies of characters... 86 unique characters found in 229
milliseconds.
Building a Huffman tree and reading codes... in 2 milliseconds.
Encoding message... in 280 milliseconds.
Writing compressed file... 1875165 bytes written in 2 milliseconds.
```
