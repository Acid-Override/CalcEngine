package com.playground.BinaryTreeV2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BSTTest {

    @Test
    @DisplayName("Should add nodes to an empty tree and increment size")
    void addToEmptyTree() {
        BST test = new BST();
        assertEquals(0, test.getSize());
        
        BST.Node root = test.add(10);
        
        assertEquals(10, root.value);
        assertNull(root.left);
        assertNull(root.right);
        assertEquals(10, test.getRootNode().value);
        assertEquals(1, test.getSize());
    }
    
    @Test
    @DisplayName("Should add nodes in correct BST order and track size")
    void addMultipleNodes() {
        BST test = new BST();
        test.add(10);
        assertEquals(1, test.getSize());
        
        test.add(20);
        assertEquals(2, test.getSize());
        
        test.add(5);
        assertEquals(3, test.getSize());
        
        assertEquals(10, test.getRootNode().value);
        assertEquals(5, test.getRootNode().left.value);
        assertEquals(20, test.getRootNode().right.value);
    }
    
    @Test
    @DisplayName("Should handle duplicate values and increment size")
    void addDuplicateValues() {
        BST test = new BST();
        test.add(10);
        assertEquals(1, test.getSize());
        
        test.add(10);
        assertEquals(2, test.getSize()); // Size increases even for duplicates in current implementation
        
        assertEquals(10, test.getRootNode().value);
        assertNull(test.getRootNode().left);
        assertNull(test.getRootNode().right);
    }

    @Test
    @DisplayName("Should perform in-order traversal and populate array correctly")
    void inOrderSearch() {
        // Create a BST with a known structure
        BST tree = new BST();
        tree.add(10);  // Root
        tree.add(5);   // Left subtree
        tree.add(15);  // Right subtree
        tree.add(3);   // Left of 5
        tree.add(7);   // Right of 5
        tree.add(12);  // Left of 15
        tree.add(20);  // Right of 15
        
        // In-order traversal should visit nodes in ascending order: 3, 5, 7, 10, 12, 15, 20
        int[] values = new int[tree.getSize()];
        int[] index = new int[1]; // Using array to allow modification in recursive method
        
        // Call the inOrderSearch method
        tree.inOrderSearch(tree.getRootNode(), values, 0);
        Arrays.stream(values).forEach(val -> log.info("{}", val));

        // Verify the array contains values in ascending order
        int[] expected = {3, 5, 7, 10, 12, 15, 20};
        assertArrayEquals(expected, values);
    }
    
    @Test
    @DisplayName("Should handle empty tree in in-order traversal")
    void inOrderSearchEmptyTree() {
        BST tree = new BST();
        int[] values = new int[0];
        int[] index = new int[1];
        
        // Should not throw exception for empty tree
        assertDoesNotThrow(() -> tree.inOrderSearch(tree.getRootNode(), values, index[0]));
    }
    
    @Test
    @DisplayName("Should handle single node tree in in-order traversal")
    void inOrderSearchSingleNode() {
        BST tree = new BST();
        tree.add(10);
        
        int[] values = new int[tree.getSize()];
        int[] index = new int[1];
        
        tree.inOrderSearch(tree.getRootNode(), values, index[0]);
        
        int[] expected = {10};
        assertArrayEquals(expected, values);
    }
    
    @Nested
    @DisplayName("Rebalance tests")
    class RebalanceTests {
        @Test
        @DisplayName("Should rebalance an unbalanced tree with odd number of nodes")
        void rebalanceUnbalancedTreeOddNodes() {
            // Create an unbalanced tree (right-heavy)
            BST tree = new BST();
            tree.add(1);
            tree.add(2);
            tree.add(3);
            tree.add(4);
            tree.add(5);
            
            // Before rebalancing, the tree is a right-skewed linked list
            assertEquals(1, tree.getRootNode().value);
            assertEquals(2, tree.getRootNode().right.value);
            assertEquals(3, tree.getRootNode().right.right.value);
            assertEquals(4, tree.getRootNode().right.right.right.value);
            assertEquals(5, tree.getRootNode().right.right.right.right.value);
            
            // Rebalance the tree
            BST.Node newRoot = tree.rebalance();
            
            // After rebalancing, the tree should be balanced with the middle value (3) as the root
            assertEquals(3, newRoot.value);
            
            // Verify tree structure after rebalancing
            // For a balanced tree with values [1,2,3,4,5], the structure should be:
            //       3
            //     /   \
            //    1     4
            //     \     \
            //      2     5
            assertNotNull(newRoot.left);
            assertNotNull(newRoot.right);
            
            // Check that the size remains the same
            assertEquals(5, tree.getSize());
            
            // Verify the tree is balanced by checking heights
            int leftHeight = getHeight(newRoot.left);
            int rightHeight = getHeight(newRoot.right);
            assertTrue(Math.abs(leftHeight - rightHeight) <= 1, 
                    "Tree should be balanced: left height = " + leftHeight + ", right height = " + rightHeight);
        }
        
        @Test
        @DisplayName("Should rebalance an unbalanced tree with even number of nodes")
        void rebalanceUnbalancedTreeEvenNodes() {
            // Create an unbalanced tree (left-heavy)
            BST tree = new BST();
            tree.add(6);
            tree.add(5);
            tree.add(4);
            tree.add(3);
            tree.add(2);
            tree.add(1);
            
            // Before rebalancing, the tree is a left-skewed linked list
            assertEquals(6, tree.getRootNode().value);
            assertEquals(5, tree.getRootNode().left.value);
            assertEquals(4, tree.getRootNode().left.left.value);
            assertEquals(3, tree.getRootNode().left.left.left.value);
            assertEquals(2, tree.getRootNode().left.left.left.left.value);
            assertEquals(1, tree.getRootNode().left.left.left.left.left.value);
            
            // Rebalance the tree
            BST.Node newRoot = tree.rebalance();
            
            // After rebalancing, the tree should be balanced
            // For even number of nodes, the implementation chooses two middle values
            
            // Verify tree structure after rebalancing
            assertNotNull(newRoot);
            
            // Check that the size remains the same
            assertEquals(6, tree.getSize());
            
            // Verify the tree is balanced by checking heights
            int leftHeight = getHeight(newRoot.left);
            int rightHeight = getHeight(newRoot.right);
            assertTrue(Math.abs(leftHeight - rightHeight) <= 1, 
                    "Tree should be balanced: left height = " + leftHeight + ", right height = " + rightHeight);
        }
        
        @Test
        @DisplayName("Should handle empty tree in rebalance")
        void rebalanceEmptyTree() {
            BST tree = new BST();
            
            // Rebalance an empty tree
            BST.Node result = tree.rebalance();
            
            // Result should be null for an empty tree
            assertNull(result);
            assertEquals(0, tree.getSize());
        }
        
        @Test
        @DisplayName("Should handle single node tree in rebalance")
        void rebalanceSingleNodeTree() {
            BST tree = new BST();
            tree.add(10);
            
            // Rebalance a single node tree
            BST.Node result = tree.rebalance();
            
            // Result should be the same single node
            assertNotNull(result);
            assertEquals(10, result.value);
            assertEquals(1, tree.getSize());
        }
        
        @Test
        @DisplayName("Should maintain in-order traversal after rebalance")
        void maintainInOrderTraversalAfterRebalance() {
            // Create an unbalanced tree
            BST tree = new BST();
            tree.add(1);
            tree.add(2);
            tree.add(3);
            tree.add(4);
            tree.add(5);
            tree.add(6);
            tree.add(7);
            
            // Store the in-order traversal before rebalancing
            int[] beforeValues = new int[tree.getSize()];
            tree.inOrderSearch(tree.getRootNode(), beforeValues, 0);
            
            // Rebalance the tree
            tree.rebalance();
            
            // Store the in-order traversal after rebalancing
            int[] afterValues = new int[tree.getSize()];
            tree.inOrderSearch(tree.getRootNode(), afterValues, 0);
            
            // The in-order traversal should be the same before and after rebalancing
            assertArrayEquals(beforeValues, afterValues);
        }
        
        // Helper method to calculate the height of a subtree
        private int getHeight(BST.Node node) {
            if (node == null) {
                return 0;
            }
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    @Nested
    @DisplayName("Find operation tests")
    class FindTests {
        @Test
        @DisplayName("Should find a node in a simple tree")
        void findExistingNode() {
            BST tree = new BST();
            tree.add(10);
            tree.add(15);
            tree.add(5);
            assertEquals(3, tree.getSize());
            
            BST.Node node = tree.find(15);
            
            assertNotNull(node);
            assertEquals(15, node.value);
            // Find operation should not change size
            assertEquals(3, tree.getSize());
        }
        
        @Test
        @DisplayName("Should find a node in a complex tree")
        void findNodeInComplexTree() {
            BST tree = new BST();
            tree.add(10);
            tree.add(15);
            tree.add(13);
            tree.add(18);
            tree.add(5);
            tree.add(7);
            tree.add(2);
            assertEquals(7, tree.getSize());
            
            BST.Node node = tree.find(2);
            
            assertNotNull(node);
            assertEquals(2, node.value);
            // Find operation should not change size
            assertEquals(7, tree.getSize());
        }
        
        @Test
        @DisplayName("Should return null when finding a non-existent node")
        void findNonExistentNode() {
            BST tree = new BST();
            tree.add(10);
            tree.add(15);
            tree.add(5);
            assertEquals(3, tree.getSize());
            
            BST.Node node = tree.find(20);
            assertNull(node);
            // Find operation should not change size
            assertEquals(3, tree.getSize());
        }
    }
    
    @Nested
    @DisplayName("Remove operation tests")
    class RemoveTests {
        @Test
        @DisplayName("Should correctly remove a node with no children and decrement size")
        void removeLeafNode() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            assertEquals(3, tree.getSize());
            
            // Find the node before removal to check its isRemoved flag later
            BST.Node nodeToRemove = tree.find(5);
            assertNotNull(nodeToRemove);
            
            BST.Node root = tree.remove(5);
            
            assertTrue(nodeToRemove.isRemoved);
            assertNotNull(root);
            assertEquals(10, root.value);
            assertNull(root.left);
            assertNotNull(root.right);
            assertEquals(2, tree.getSize()); // Size should be decremented
        }
        
        @Test
        @DisplayName("Should handle removing a non-existent node without changing size")
        void removeNonExistentNode() {
            BST tree = new BST();
            tree.add(10);
            assertEquals(1, tree.getSize());
            
            BST.Node root = tree.remove(20);
            
            assertNotNull(root);
            assertEquals(10, root.value);
            // Size should not change when removing a non-existent node
            assertEquals(1, tree.getSize());
        }
        
        @Test
        @DisplayName("Should remove a node with one child and decrement size")
        void removeNodeWithOneChild() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            tree.add(20);
            assertEquals(4, tree.getSize());
            
            // Find the node before removal to check its isRemoved flag later
            BST.Node nodeToRemove = tree.find(15);
            assertNotNull(nodeToRemove);
            
            BST.Node root = tree.remove(15);
            
            assertTrue(nodeToRemove.isRemoved);
            assertNotNull(root);
            assertEquals(10, root.value);
            assertEquals(20, root.right.value);
            assertEquals(3, tree.getSize()); // Size should be decremented
        }
        
        @Test
        @DisplayName("Should remove a node with two children and decrement size")
        void removeNodeWithTwoChildren() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            tree.add(12);
            tree.add(20);
            assertEquals(5, tree.getSize());
            
            // Find the node before removal to check its isRemoved flag later
            BST.Node nodeToRemove = tree.find(15);
            assertNotNull(nodeToRemove);
            
            BST.Node root = tree.remove(15);
            
            assertTrue(nodeToRemove.isRemoved);
            assertNotNull(root);
            assertEquals(10, root.value);
            // The right child should now be the successor (smallest value in right subtree)
            assertNotNull(root.right);
            assertEquals(4, tree.getSize()); // Size should be decremented
        }
        
        @Test
        @DisplayName("Should remove the root node and decrement size")
        void removeRootNode() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            assertEquals(3, tree.getSize());
            
            // Find the node before removal to check its isRemoved flag later
            BST.Node nodeToRemove = tree.find(10);
            assertNotNull(nodeToRemove);
            
            BST.Node newRoot = tree.remove(10);
            
            assertTrue(nodeToRemove.isRemoved);
            assertNotNull(newRoot);
            // Root should be replaced with a successor
            assertTrue(newRoot.value == 5 || newRoot.value == 15);
            assertEquals(2, tree.getSize()); // Size should be decremented
        }
        
        @Test
        @DisplayName("Should remove multiple nodes and track size correctly")
        void removeMultipleNodes() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            tree.add(2);
            tree.add(7);
            tree.add(12);
            tree.add(20);
            assertEquals(7, tree.getSize());
            
            tree.remove(2); // Remove leaf node
            assertEquals(6, tree.getSize());
            
            tree.remove(15); // Remove node with two children
            assertEquals(5, tree.getSize());
            
            tree.remove(10); // Remove root node
            assertEquals(4, tree.getSize());
            
            tree.remove(30); // Remove non-existent node
            assertEquals(4, tree.getSize()); // Size should not change
        }
    }
    
    @Nested
    @DisplayName("Edge case tests")
    class EdgeCaseTests {
        @Test
        @DisplayName("Should handle an empty tree with size 0")
        void emptyTree() {
            BST tree = new BST();
            
            assertNull(tree.getRootNode());
            assertNull(tree.find(10));
            assertEquals(0, tree.getSize());
            
            // For an empty tree, remove should return null and not change size
            assertNull(tree.remove(10));
            assertEquals(0, tree.getSize());
        }
        
        @Test
        @DisplayName("Should handle a tree with only one node and size 1")
        void singleNodeTree() {
            BST tree = new BST();
            tree.add(10);
            
            assertEquals(10, tree.getRootNode().value);
            BST.Node nodeToRemove = tree.find(10);
            assertNotNull(nodeToRemove);
            assertEquals(1, tree.getSize());
            
            BST.Node result = tree.remove(10);
            // The node should be marked as removed
            assertTrue(nodeToRemove.isRemoved);
            // But the result will be null since the tree is now empty
            assertNull(result);
            assertEquals(0, tree.getSize()); // Size should be decremented to 0
        }
        
        @Test
        @DisplayName("Should handle a balanced tree with correct size")
        void balancedTree() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            tree.add(2);
            tree.add(7);
            tree.add(12);
            tree.add(20);
            
            assertEquals(10, tree.getRootNode().value);
            assertEquals(5, tree.getRootNode().left.value);
            assertEquals(15, tree.getRootNode().right.value);
            assertEquals(7, tree.getSize());
            
            // Test finding nodes at different levels
            assertEquals(2, tree.find(2).value);
            assertEquals(12, tree.find(12).value);
            // Find operations should not change size
            assertEquals(7, tree.getSize());
        }
    }
    
    @Test
    @DisplayName("Should correctly represent the tree as a string including size")
    void testToString() {
        BST tree = new BST();
        tree.add(10);
        tree.add(5);
        assertEquals(2, tree.getSize());
        
        String treeString = tree.toString();
        
        assertTrue(treeString.contains("rootNode"));
        assertTrue(treeString.contains("value=10"));
        assertTrue(treeString.contains("value=5"));
        assertTrue(treeString.contains("size=2"));
    }
}