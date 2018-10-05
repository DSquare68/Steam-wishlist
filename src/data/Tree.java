package data;

public class Tree<T> {
    private Node<T> root;
    private int size=0;
    public Tree() {
    	
    }
    public Tree(int rootkey,T data) {
        root = new Node<T>();
        root.key = rootkey;
        root.data=data;
    }
    public void setRoot(int rootkey,T data) {
        root = new Node<T>();
        root.key = rootkey;
        root.data=data;
    }
    public T find(Node<T> node,int key) {
    	if(node==null||node.key==0) return null;
    	if(node.key==key) {
    		return node.data;
    	}else if(node.key>key){
    		return find(node.left,key);
    	}else if(node.key<key){
    		return find(node.right,key);
    	}
    	return null;
    }
    public int height(Node<T> n) {
    	if (n == null)
                return 0;
     
        return n.height;
    	
    }
    private Node<T>rightRotate(Node<T>y) {
        Node<T>x = y.left;
        Node<T>T2 = x.right;
 
        // Perform rotation
        x.right = y;
        y.left = T2;
 
        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
 
        // Return new root
        return x;
    }
 
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
     private Node<T> leftRotate(Node<T>x) {
        Node<T>y = x.right;
        Node<T>T2 = y.left;
 
        // Perform rotation
        y.left = x;
        x.right = T2;
 
        //  Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
 
        // Return new root
        return y;
    }
  // Get Balance factor of Node<T>N
     int getBalance(Node<T>N) {
         if (N == null)
             return 0;
  
         return height(N.left) - height(N.right);
     }
  
     public Node<T> insert(Node<T>node,int key, T data) {
  
         /* 1.  Perform the normal BST insertion */
         if (node== null) {
             size++;
        	 return (new Node(key,data));
         }
         if (compare(key,node.key))
             node.left = insert(node.left, key,data);
         else if (!compare(key,node.key))
             node.right = insert(node.right, key,data);
         else // Duplicate keys not allowed
             return node;
  
         /* 2. Update height of this ancestor Node<T>*/
         node.height = 1 + Math.max(height(node.left),
                               height(node.right));
  
         /* 3. Get the balance factor of this ancestor
               Node<T>to check whether this Node<T>became
               unbalanced */
         int balance = getBalance(node);
  
         // If this Node<T>becomes unbalanced, then there
         // are 4 cases Left Left Case
         if (balance > 1 && compare(key,node.left.key))
             return rightRotate(node);
  
         // Right Right Case
         if (balance < -1 && !compare(key,node.right.key))
             return leftRotate(node);
  
         // Left Right Case
         if (balance > 1 && !compare(key,node.left.key)) {
             node.left = leftRotate(node.left);
             return rightRotate(node);
         }
  
         // Right Left Case
         if (balance < -1 && compare(key,node.right.key)) {
             node.right = rightRotate(node.right);
             return leftRotate(node);
         }
         
         /* return the (unchanged) Node<T>pointer */
         return node;
     }
  
     private boolean compare(int key, int key2) {
		if(key < key2 ) return true; else return false;
	}

	// A utility function to print preorder traversal
     // of the tree.
     // The function also prints height of every node
     public void preOrder(Node<T> node) {
         if (node!= null) {
             System.out.print(node.key + " ");
             preOrder(node.left);
             preOrder(node.right);
         }
     }
     public Node<T> getRoot() {
 		return root;
 	}
 	public void setRoot(Node<T> root) {
 		this.root = root;
 	}
 	public int size() {
 		return size;
 	}
    public static class Node<T> {
        private int key=0;
        private T data;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;
        int height;
        public Node(int key, T data){
        	this.key=key;
        	this.data=data;
        }
		public Node() {
			// TODO Auto-generated constructor stub
		}
		public int getkey() {
			return key;
		}
		public void setkey(int key) {
			this.key = key;
		}
		
		public Node<T> getParent() {
			return parent;
		}
		public void setParent(Node<T> parent) {
			this.parent = parent;
		}
		public Node<T> getLeftChild() {
			return left;
		}
		public void setLeftChild(Node<T> leftChild) {
			this.left = leftChild;
		}
		public Node<T> getRirghtChild() {
			return right;
		}
		public void setRirghtChild(Node<T> rirghtChild) {
			this.right = rirghtChild;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
        
        
    }
}
