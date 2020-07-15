 public class SerializationDeserializationofBT
{ 
	static class Node { 
		int key; 
		Node left, right; 

		Node(int key){ 
			this.key = key; 
			left = null; 
			right = null; 
		} 
	} 
	static Node root; 
	static Node temp = root; 
	
	static void preorder(Node temp) 
	{ 
		if (temp == null) 
			return; 
		
		System.out.print(temp.key+" "); 
		preorder(temp.left); 
		preorder(temp.right); 
	} 
	
	private static String serializeBinaryTree(Node rootNode) {
        if (rootNode == null) {
            return "null,";
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append(rootNode.key);
        sb.append(",");
 
        sb.append(serializeBinaryTree(rootNode.left));
        sb.append(serializeBinaryTree(rootNode.right));
        return sb.toString();
    }

	private static Node deserializeBinaryTree(String str) 
	{
		String[] st = str.split(",");
		return deserialize(st, new int[] {0});
        //return deserializeUsingStaticCounter(st);
	}

	static int index;
	 
	private static Node deserializeUsingStaticCounter(String[] st) 
	{
		if (index > st.length || st[index].equals("null")) {
            index++;
            return null;
        }
 
        Node node = new Node(Integer.parseInt(st[index++]));
        node.left = deserializeUsingStaticCounter(st);
        node.right = deserializeUsingStaticCounter(st);
 
        return node;
	} 
	
	private static Node deserialize(String[] data, int[] index) {
        if (index[0] > data.length || data[index[0]].equals("null")) {
            index[0]++;
            return null;
        }
        
        Node node = new Node(Integer.parseInt(data[index[0]++]));
        node.left = deserialize(data, index);
        node.right = deserialize(data, index);
 
        return node;
    }
	
	
	public static void main(String args[]) { 
		root = new Node(10); 
		root.left = new Node(5); 
		root.right = new Node(20); 
		root.left.left = new Node(3); 
		root.left.right = new Node(8); 
		root.left.right.left = new Node(7); 
		
		preorder(root); 
		System.out.println();
		
		String str = serializeBinaryTree(root);
        System.out.println(str);
        
        System.out.println();
        Node start = deserializeBinaryTree(str);
        preorder(start);
	}
} 
