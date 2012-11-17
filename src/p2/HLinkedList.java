package p2;

public class HLinkedList 
{
    private HTreeNode head; //Declares the head of the node
    private int size; //Declares the variable that keeps track of the length of the list
    
    //Constructs an empty list by initializing the head and setting the size to 0
    public HLinkedList()
    {
        head = new HTreeNode();
        size = 0;
    }
    
    //Returns the head of the list
    public HTreeNode getHeadNode()
    {
        return head;
    }
    
    //Inserts a node into the list in the position specified
    public void insertIntoPosition(HTreeNode node, int position)
    {
        HTreeNode temp = getHeadNode();
        
        //Throws an exception if the index is not a positive number
        if (position < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Please specify an index greater than 0");
        }
        //If the position specified is greater than the size of the list the node is inserted at the end of the list
        if (position > size)
        {
            position = size;
        }
        
        //Iterates through the list until the proper node has been reached
        for(int i = 0; i < position; i++)
        {
            temp = temp.next;
        }
        
        //Inserts the node into the list
        node.next = temp.next;
        temp.next = node;
        
        //Increases the size to account for the new node
        size++;
    }
    
    //Accepts a char and returns the node in the list that contains that char, or returns null if a node isn't found
    public HTreeNode find(char character)
    {
        HTreeNode temp = getHeadNode();
        
        //Iterates temp through the list, checking to see if one of the nodes contains the char
        while( temp.getCharacter() != character && temp.next != null)
        {
            temp = temp.next;   
        }
        
        //Stops at last node and checks to see if it contains the correct character
        if (temp.getCharacter() != character)
        {
            return null; //Returns null if no node in the list contains the character
        }
       
        return temp; //Returns the node that has the character
    }
    
    //Prints the list in sequential order
    public void print()
    {
        HTreeNode trav = head.next;
        
        while(trav != null)
        {
            System.out.println(trav.getCharacter() +", " +trav.getFrequency() +", " +trav.getCode()); //Prints the character, frequency, and Huffman Code of the node
            trav = trav.next;
        }
    }
    
    //Removes a node from a HLinkedList
    public void removeNode(HTreeNode node)
    {
        HTreeNode trav = head;
        
        while(trav.next.getCharacter() != node.getCharacter() && trav.next != null)
        {
            trav = trav.next;
        }
        
        trav.next = node.next;
        
        size--;
    }
    
    //Returns the size of the list
    public int getSize()
    {
        return size; 
    }
}