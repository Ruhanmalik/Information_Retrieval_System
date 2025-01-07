

# Information Retrieval System

A Java-based Information Retrieval System that implements a Binary Search Tree (BST) for efficient keyword-based article searching and organization.

## Features

- Binary Search Tree implementation for keyword indexing
- Multiple traversal options:
  - InOrder traversal with full article details
  - InOrder traversal with keywords only
  - PreOrder traversal with keywords only
- Keyword search functionality
- Article management with ID, title, and author information
- File-based data persistence

## Usage

1. Compile the Java files:
```bash
javac Main.java
```

2. Run the program:
```bash
java Main
```

3. Use the interactive menu to:
   - View all keywords and their associated articles (Option 1)
   - Display keywords only in InOrder traversal (Option 2)
   - Display keywords only in PreOrder traversal (Option 3)
   - Search for specific keywords (Option 4)
   - Exit the program (Option 5)

## Menu Options

1. **InOrder Traversal with Details**
   - Displays keywords along with their associated referenced articles
   - Shows the complete tree structure with L→→ and R→→ indicators for left and right children

2. **InOrder Traversal (Keywords Only)**
   - Displays only the keywords in InOrder traversal
   - Useful for viewing the keyword hierarchy

3. **PreOrder Traversal (Keywords Only)**
   - Displays only the keywords in PreOrder traversal
   - Alternative view of the keyword organization

4. **Search for a specific Keyword**
   - Enter a keyword to search
   - Displays all articles associated with the keyword if found
   - Shows "not found" message if the keyword doesn't exist

## Implementation Details

- Uses a Generic Binary Search Tree for flexibility
- Implements LinkedList for handling multiple articles per keyword
- Includes robust file handling with error management
- Tree traversals include visual indicators for tree structure

## Error Handling

The system includes error handling for:
- File reading operations
- Invalid user input
- File format inconsistencies
- Resource cleanup

## Example Output

```
        Welcome to Information Retrieval System
1. InOrder Traversal with Details
2. InOrder Traversal (Keywords Only)
3. PreOrder Traversal (Keywords Only)
4. Search for a specific Keyword
5. Exit

Enter a choice?
```
