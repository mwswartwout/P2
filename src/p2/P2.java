package p2;

import java.io.FileNotFoundException;
import java.io.IOException;

public class P2
{
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception
    {
        //Creates encoded text file. Huffman_coder contains all relevant method calls
        HuffmanLibrary.Huffman_coder("pg20776.txt", null);
    }
}
