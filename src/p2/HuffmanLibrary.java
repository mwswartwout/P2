package p2;

import java.io.*;

public class HuffmanLibrary
{
    static HLinkedList dictionary = new HLinkedList(); //Dictionary is an easily referencable list of Huffman Codes and characters
    static String book = ""; //Starts the book string as an empty string
    
    //Reads a file and converts the contents to a string
    public static String readFileAsString(String fileName) throws FileNotFoundException, IOException
    {
        char nextChar;
       
        //Creates a file reader to read the file
        FileReader scanner;
        scanner = new FileReader(fileName);
        
        //Reads each char of the file and adds it to the book string
        while(scanner.ready())
        {   
            nextChar = (char)scanner.read();
            book+= nextChar;
        }
        
        scanner.close();
        return book;
    }
    
    //Creates a linked list with a node to represent each char in the string that was read from the .txt file
    public static HLinkedList createHList(String fileContents)
    {
        HLinkedList list = new HLinkedList();
        HTreeNode found;
        
        //Loop runs for each individual char in the string fileContents
        for(int i = 1; i < fileContents.length(); i++)
        {
            HTreeNode temp = new HTreeNode();
            
            //Checks to see if a node has already been created with the character being read
            found = list.find(fileContents.charAt(i));
            
            //Creates a new node if no existing node contains that character
            if (found == null)
            {
                temp.setCharacter(fileContents.charAt(i));
                list.insertIntoPosition(temp, 0);
            }
            
            //Increases the frequency of the existing node if it already exists
            else
            {
                found.increaseFrequency();
            }
        }
        
        return list;
    }
    
    //Sorts a linked list by frequency
    public static HLinkedList getSortedLinkedList(HLinkedList list)
    {
        HLinkedList sorted = new HLinkedList(); //Creates a new list, sorted, that will have entries from the unsorted list inserted into it in the correct order
        HTreeNode trav = list.getHeadNode().next;
        
        //Inserts the first node from list into sorted
        HTreeNode insert = new HTreeNode();
        insert.setCharacter(trav.getCharacter());
        insert.setFrequency(trav.getFrequency());
        insert.left = trav.left;
        insert.right = trav.right;
        
        sorted.insertIntoPosition(insert, 0);
        
        int pos;
        trav = trav.next;
        
        //Goes through all of the unsorted list
        while(trav != null)
        {   
            pos = 0;
            
            HTreeNode temp = sorted.getHeadNode().next;
            
            //Sets the data of insert1 to the same values of trav
            HTreeNode insert1 = new HTreeNode();
            insert1.setCharacter(trav.getCharacter());
            insert1.setFrequency(trav.getFrequency());
            insert1.setCode(trav.getCode());
            insert1.left = trav.left;
            insert1.right = trav.right;
            
            //Finds the proper spot to insert the node
            while( insert1.getFrequency() > temp.getFrequency() && temp.next != null)
            {
                pos++;
                temp = temp.next;
            }
            
            //Adjusts position if the new node needs to be inserted at the end of the list
            if (insert1.getFrequency() > temp.getFrequency())
            {
                pos++;
            }
            
            //Inserts the new node
            sorted.insertIntoPosition(insert1, pos);
            
            //Moves onto the next node to be inserted
            trav = trav.next;
        }
        
        return sorted;
    }
    
    //Creates a Huffman Tree based off a linked list
    public static HTree createHuffmanTree(HLinkedList list)
    {
        HTree tree = new HTree();

        //Moves nodes until one root node remains, with every other node beneath it in a tree
        while (list.getSize() > 1)
        {
            HTreeNode newNode = new HTreeNode();
            HTreeNode trav = list.getHeadNode().next;
            
            //Creates a new node with no character data and a frequency that is the sum of it's children
            newNode.setFrequency(trav.getFrequency() + trav.next.getFrequency());
            newNode.left = trav;
            newNode.right = trav.next;
            
            //Removes the two new children node from the list
            list.removeNode(trav);
            list.removeNode(trav.next);
            
            //Replaces the two children with the parent node
            list.insertIntoPosition(newNode, 0);
            
            //Sorts the list so that the new node is in the correct position
            list = getSortedLinkedList(list);

            //If the list has been reduced to one node then the root is set to point at the first node
            if (list.getSize() == 1)
            {
                tree.insertRoot(newNode);
            }
        }
 
        return tree;
    }
    
    //Sets the values of each node to a 0 or 1 depending on their location in the tree
    public static void updateCodeValues(HTreeNode node, HTree tree)
    {
        //Sets the root of the tree to a 0
        if(tree.getRoot() == node && node.next != null)
        {
            node = node.next;
            
            node.setValue((byte)0);
            
            if(node.left != null)
            {
                //Updates the value of the left child of the node to 0, then recursively moves to the left subtree
                node.left.setValue((byte)0);
                updateCodeValues(node.left, tree);
            }
            
            if(node.right != null)
            {
                //Updates the value of the left child of the node to 1, then recursively moves to the right subtree
                node.right.setValue((byte)1);
                updateCodeValues(node.right, tree);
            }  
        }
        
        //If not the root of the tree the method just updates the children of the node, because the node already has its value assigned
        else
        {
           if(node.left != null)
           {
                node.left.setValue((byte)0);
                updateCodeValues(node.left, tree);
           }
            
           if(node.right != null)
           {
               node.right.setValue((byte)1);
               updateCodeValues(node.right, tree);
           }   
        }
    }
    
    //Creates the dictionary based off the values of the Huffman Trees
    public static void createHuffmanDictionary(HTreeNode node, String code)
    {
        HTreeNode temp = new HTreeNode();
        
        //Adds a 0 to the node's code if the value of the node is 0
        if(node.getValue() == 0)
        {
            code += "0"; 
        }
        
        //Adds a 1 to the node's code if the value of the node is 1
        else
        {
            code += "1";
        }
        
        //Takes nodes that have a character value and adds them to the dictionary
        if(node.getCharacter() != 0)
        {
            temp.setCharacter(node.getCharacter());
            temp.setCode(code);
            dictionary.insertIntoPosition(temp, 0);
        }
        
        //Recursively adds the left subtree to the dictionary
        if(node.left != null)
        {
            createHuffmanDictionary(node.left, code);
        }
        
        //Recursively adds the right subtree to the dictionary
        if(node.right != null)
        {
            createHuffmanDictionary(node.right, code);
        }
    }
    
    //Encodes a .txt file using a Huffman Tree from start to finish
    public static void Huffman_coder(String input_file, String output_file) throws FileNotFoundException, IOException, Exception
    {
        String book = readFileAsString(input_file);
        HLinkedList list = createHList(book);
        list = getSortedLinkedList(list);
        HTree tree = createHuffmanTree(list);
        updateCodeValues(tree.getRoot(), tree);
        createHuffmanDictionary(tree.getRoot().next, "");
        String compressedString = "";

        HTreeNode temp;
        
        //Creates a compressed string by running through the string book and adding the Huffman Code for each character to the compressed string
        for(int i = 0; i < book.length(); i++)
        {
            temp = dictionary.find(book.charAt(i)); //Finds the Huffman code in the dictionary
            
            if(temp != null) //Avoids null pointer errors if null characters are in original string
            {
                compressedString += temp.getCode();
            }
        }

        BinaryFileWriter.createBinaryFile(compressedString, "compressedpg20776"); //Writes a binary file with the new compressed string
    }
}
