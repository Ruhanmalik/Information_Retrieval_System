import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    public static void main(String args[]) {    
        BinarySearchTree bst = new BinarySearchTree();
        readFileRecords(bst, "JavaTextFile.txt");
        
        System.out.println("\t\tWelcome to Information Retrieval System");
        
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. InOrder Traversal with Details <Output keywords along with their associated referenced articles.>");
            System.out.println("2. InOrder Traversal (Keywords Only) <Output only the keywords, excluding the referenced articles.>");
            System.out.println("3. PreOrder Traversal (Keywords Only) <Output only the keywords in pre-order traversal, without the referenced articles.> ");
            System.out.println("4. Search for a specific Keyword <If found, display the keyword with referenced articles; otherwise, output the keyword not found message.>");
            System.out.println("5. Exit <Terminates the program.>");
            System.out.print("\nEnter a choice? ");
            choice = input.nextInt();
            
            switch(choice) {
                case 1:
                    bst.inOrder();
                    break;
                case 2:
                    bst.inOrderKeywordsOnly();
                    break;
                case 3:
                    bst.preOrder();
                    break;
                case 4:
                    input.nextLine(); 
                    System.out.print("Enter the keyword to search: ");
                    String keyword = input.nextLine();
                    bst.searchKeyword(keyword);
                    break;
                case 5:
                    System.out.println("\n ----jGRASP: operation complete.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
        
        input.close();
    }
    
    public static void readFileRecords(BinarySearchTree bst, String filename) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(filename));
            
            String strId;
            while ((strId = fileReader.readLine()) != null) {
                int id = Integer.parseInt(strId);
                String title = fileReader.readLine();
                String author = fileReader.readLine();
                int numKeys = Integer.parseInt(fileReader.readLine());
                
                String keyword;
                Article art = new Article(id, title, author);
                for (int i = 0; i < numKeys; i++) {
                    keyword = fileReader.readLine();
                    bst.insert(keyword, art);
                }
                
                fileReader.readLine(); 
            }
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                }
                catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }
    }
}

class BinarySearchTree <E extends Comparable<E>> {
    protected TreeNode<E> root;
    protected int size;
    
    public TreeNode<E> search(E element) {
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                current = current.leftC;
            }
            else if (element.compareTo(current.element) > 0) {
                current = current.rightC;
            }
            else {
                return current; 
            }
        }
        return null; 
    }
    
    public void insert(E element, Article art) {
        if (root == null) {
            root = new TreeNode<>(element);
            root.head.addFirst(art);
        }
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            
            while (current != null) {
                parent = current;
                if (element.compareTo(current.element) < 0) {
                    current = current.leftC;
                }
                else if (element.compareTo(current.element) > 0) {
                    current = current.rightC;
                }
                else {
                    current.head.addFirst(art);
                    return;
                }
            }
            
            TreeNode<E> newNode = new TreeNode<>(element);
            newNode.head.addFirst(art);
            
            if (element.compareTo(parent.element) < 0) {
                parent.leftC = newNode;
            }
            else {
                parent.rightC = newNode;
            }
        }
        size++;
    }
    
    public void inOrder() {
        System.out.println("\n====================================================");
        System.out.println("Running InOrder Traversal of the Binary Search tree:");
        inOrder(root, "", true);
    }
    
    protected void inOrder(TreeNode<E> root, String prefix, boolean isLeft) {
        if (root != null) {
            inOrder(root.leftC, prefix + (isLeft ? "   " : "    "), true);
            System.out.printf("%s%s%s\n", prefix, (isLeft ? "L→→  " : "R→→  "), root.element);
            for (Article node : root.head) { 
                System.out.print("\t " + node); 
            }
            inOrder(root.rightC, prefix + (isLeft ? "   " : "    "), false);
        }
    }
    
    public void inOrderKeywordsOnly() {
        System.out.println("\n====================================================");
        System.out.println("Running InOrder Traversal of the Binary Search tree:");
        inOrderKeywordsOnlyHelper(root, "", true);
    }
    
    private void inOrderKeywordsOnlyHelper(TreeNode<E> root, String prefix, boolean isLeft) {
        if (root != null) {
            inOrderKeywordsOnlyHelper(root.leftC, prefix + (isLeft ? "   " : "    "), true);
            System.out.printf("%s%s%s\n", prefix, (isLeft ? "L→→  " : "R→→  "), root.element);
            inOrderKeywordsOnlyHelper(root.rightC, prefix + (isLeft ? "   " : "    "), false);
        }
    }
    
    public void preOrder() {
        System.out.println("\n====================================================");
        System.out.println("Running InOrder Traversal of the Binary Search tree:");
        preOrder(root, "", true);
    }
    
    private void preOrder(TreeNode<E> root, String prefix, boolean isLeft) {
        if (root != null) {
            System.out.printf("%s%s%s\n", prefix, (isLeft ? "L→→  " : "R→→  "), root.element);
            preOrder(root.leftC, prefix + (isLeft ? "   " : "    "), true);
            preOrder(root.rightC, prefix + (isLeft ? "   " : "    "), false);
        }
    }
    
    public void searchKeyword(String keyword) {
        @SuppressWarnings("unchecked")
        TreeNode<E> result = search((E)keyword);
        
        if (result != null) {
            System.out.println(result.element + " ");
            for (Article article : result.head) {
                System.out.print("\t " + article);
            }
        } else {
            System.out.println(keyword + " does not exist in the Information Retrieval System!");
        }
    }
}

class TreeNode<E> {
    protected E element;
    protected TreeNode<E> leftC;
    protected TreeNode<E> rightC;
    protected LinkedList<Article> head;
    
    public TreeNode(E e) {
        element = e;
        head = new LinkedList<Article>();
    }
}

class Article {
    private int id;
    private String title;
    private String author;
    
    public Article(int i, String t, String a) {
        id = i;
        title = t;
        author = a;
    }
    
    @Override
    public String toString() {
        return String.format("%d | %s | %s-->\n", id, title, author);
    }
}