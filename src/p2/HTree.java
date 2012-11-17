package p2;

public class HTree 
{
    //Tree root to point to beginning of tree
    private HTreeNode root;
    
    //Tree constructor
    public HTree()
    {
        root = new HTreeNode();
    }
    
    //Allows access to the root of the tree for traversal
    public HTreeNode getRoot()
    {
        return root;
    }
    
    //Creates the first node in the tree, with the root pointing at it
    public void insertRoot(HTreeNode node)
    {
        root.next = node;
    }
    
    //Preorder traversal and print of the tree
    public void print(HTreeNode node)
    {
        System.out.println(node.getCharacter() +", " +node.getCode());
        
        if(node.left != null)
        {
            print(node.left);
        }
        
        if(node.right != null)
        {
            print(node.right);
        }
                
    }
}
