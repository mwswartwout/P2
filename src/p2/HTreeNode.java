package p2;

public class HTreeNode 
{
    
    private byte value; //Contains a 0 or 1 depending on the node's location in a tree
    private char character; //Contains the character the node represents
    private int frequency; // Contains the frequency that the node's character appears in the text 
    public String code; //Contains the sequence of 0s and 1s that represent the character with Huffman Encoding
    public HTreeNode next, left, right; //Contains the node relations next, left, and right.
    
    //Constructs a Node. Assumes if a node is being constructed that is has a frequency >= 1
    public HTreeNode()
    {
        frequency = 1;
    }
    
    //Returns the character that the node represents
    public char getCharacter()
    {
        return character;
    }
    
    //Sets the character of the node to the specified character
    public void setCharacter(char ch)
    {
        character = ch;
    }
    
    //Increases the frequency of the the node by 1
    public void increaseFrequency()
    {
        frequency++;
    }
    
    //Returns the frequency of the node
    public int getFrequency()
    {
        return frequency;
    }
    
    //Sets the frequency of the node to the specified integer
    public void setFrequency(int fr)
    {
        frequency = fr;
    }
    
    //Returns the value of the node
    public byte getValue()
    {
        return value;
    }
    
    //Sets the value of the node to the specified byte
    public void setValue(byte va)
    {
        value =va;
    }
    
    //Returns the code of the node
    public String getCode()
    {
        return code;
    }
    
    //Sets the code of the node to the specified string
    public void setCode(String co)
    {
        code = co;
    }
}
