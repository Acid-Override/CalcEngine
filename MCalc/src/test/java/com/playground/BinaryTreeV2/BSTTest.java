package com.playground.BinaryTreeV2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BSTTest {

    @Test
    @DisplayName("Should add nodes to an empty tree")
    void addToEmptyTree() {
        BST test = new BST();
        BST.Node root = test.add(10);
        
        assertEquals(10, root.value);
        assertNull(root.left);
        assertNull(root.right);
        assertEquals(10, test.getRootNode().value);
    }
    
    @Test
    @DisplayName("Should add nodes in correct BST order")
    void addMultipleNodes() {
        BST test = new BST();
        test.add(10);
        test.add(20);
        test.add(5);
        
        assertEquals(10, test.getRootNode().value);
        assertEquals(5, test.getRootNode().left.value);
        assertEquals(20, test.getRootNode().right.value);
    }
    
    @Test
    @DisplayName("Should handle duplicate values")
    void addDuplicateValues() {
        BST test = new BST();
        test.add(10);
        test.add(10);
        
        assertEquals(10, test.getRootNode().value);
        assertNull(test.getRootNode().left);
        assertNull(test.getRootNode().right);
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
            
            BST.Node node = tree.find(15);
            
            assertNotNull(node);
            assertEquals(15, node.value);
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
            
            BST.Node node = tree.find(2);
            
            assertNotNull(node);
            assertEquals(2, node.value);
        }
        
        @Test
        @DisplayName("Should return null when finding a non-existent node")
        void findNonExistentNode() {
            BST tree = new BST();
            tree.add(10);
            tree.add(15);
            tree.add(5);
            
            BST.Node node = tree.find(20);
            assertNull(node);
        }
    }
    
    @Nested
    @DisplayName("Remove operation tests")
    class RemoveTests {
        @Test
        @DisplayName("Should correctly remove a node with no children")
        void removeLeafNode() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            
            // Find the node before removal to check its isRemoved flag later
            BST.Node nodeToRemove = tree.find(5);
            assertNotNull(nodeToRemove);
            assertFalse(nodeToRemove.isRemoved);
            
            BST.Node root = tree.remove(5);
            
            assertTrue(nodeToRemove.isRemoved);
            assertNotNull(root);
            assertEquals(10, root.value);
            assertNull(root.left);
            assertNotNull(root.right);
        }
        
        @Test
        @DisplayName("Should handle removing a non-existent node")
        void removeNonExistentNode() {
            BST tree = new BST();
            tree.add(10);
            
            BST.Node root = tree.remove(20);
            
            assertNotNull(root);
            assertEquals(10, root.value);
            // No node was removed since it doesn't exist
        }
        
        @Test
        @DisplayName("Should remove a node with one child")
        void removeNodeWithOneChild() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            tree.add(20);
            
            // Find the node before removal to check its isRemoved flag later
            BST.Node nodeToRemove = tree.find(15);
            assertNotNull(nodeToRemove);
            
            BST.Node root = tree.remove(15);
            
            assertTrue(nodeToRemove.isRemoved);
            assertNotNull(root);
            assertEquals(10, root.value);
            assertEquals(20, root.right.value);
        }
        
        @Test
        @DisplayName("Should remove a node with two children")
        void removeNodeWithTwoChildren() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            tree.add(12);
            tree.add(20);
            
            // Find the node before removal to check its isRemoved flag later
            BST.Node nodeToRemove = tree.find(15);
            assertNotNull(nodeToRemove);
            
            BST.Node root = tree.remove(15);
            
            assertTrue(nodeToRemove.isRemoved);
            assertNotNull(root);
            assertEquals(10, root.value);
            // The right child should now be the successor (smallest value in right subtree)
            assertNotNull(root.right);
        }
        
        @Test
        @DisplayName("Should remove the root node")
        void removeRootNode() {
            BST tree = new BST();
            tree.add(10);
            tree.add(5);
            tree.add(15);
            
            // Find the node before removal to check its isRemoved flag later
            BST.Node nodeToRemove = tree.find(10);
            assertNotNull(nodeToRemove);
            
            BST.Node newRoot = tree.remove(10);
            
            assertTrue(nodeToRemove.isRemoved);
            assertNotNull(newRoot);
            // Root should be replaced with a successor
            assertTrue(newRoot.value == 5 || newRoot.value == 15);
        }
    }
    
    @Nested
    @DisplayName("Edge case tests")
    class EdgeCaseTests {
        @Test
        @DisplayName("Should handle an empty tree")
        void emptyTree() {
            BST tree = new BST();
            
            assertNull(tree.getRootNode());
            assertNull(tree.find(10));
            
            // For an empty tree, remove should return null
            assertNull(tree.remove(10));
        }
        
        @Test
        @DisplayName("Should handle a tree with only one node")
        void singleNodeTree() {
            BST tree = new BST();
            tree.add(10);
            
            assertEquals(10, tree.getRootNode().value);
            BST.Node nodeToRemove = tree.find(10);
            assertNotNull(nodeToRemove);
            
            BST.Node result = tree.remove(10);
            // The node should be marked as removed
            assertTrue(nodeToRemove.isRemoved);
            // But the result will be null since the tree is now empty
            assertNull(result);
        }
        
        @Test
        @DisplayName("Should handle a balanced tree")
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
            
            // Test finding nodes at different levels
            assertEquals(2, tree.find(2).value);
            assertEquals(12, tree.find(12).value);
        }
    }
    
    @Test
    @DisplayName("Should correctly represent the tree as a string")
    void testToString() {
        BST tree = new BST();
        tree.add(10);
        tree.add(5);
        
        String treeString = tree.toString();
        
        assertTrue(treeString.contains("rootNode"));
        assertTrue(treeString.contains("value=10"));
        assertTrue(treeString.contains("value=5"));
    }
}