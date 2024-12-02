/*
    Saul Toribio
    11/26/24
    CSE017 Fall 2024: PA 9
    IDE: VSCode; JDK: 11
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * A class that creates a binary search tree map.
*/
public class TreeMap<K, V> {
    private Comparator<K> comp;
    private TreeNode root;
    private int size;

    /**
     * The private class that defines what TreeNode is.
    */
    private class TreeNode {
        TreeNode left, right;
        MapEntry<K, V> entry;

        /**
         * Default configuration of a TreeNode.
         * @param key A generic key. Can be anything.
         * @param value A generic value. Can be anything.
        */
        TreeNode(K key, V value) {
            entry = new MapEntry<>(key, value);
        }
    }

    /**
     * Default configuration of a TreeMap.
    */
    public TreeMap() {
        this(null);
    }

    /**
     * A TreeMap with a configured comparator.
     * @param comp A generic comparator. Can be anything.
    */
    public TreeMap(Comparator<K> comp) {
        this.comp = comp;
    }

    /**
     * @return The size of the TreeMap.
    */
    public int size() {
        return size;
    }

    /**
     * @return True if the size of the TreeMap is 0, otherwise false.
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the TreeMap.
    */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Checks if a generic key is within the TreeMap.
     * @param key A generic key. Can be anything.
     * @return True if the generic key is within the TreeMap, otherwise false.
    */
    public boolean contains(K key) {
        TreeNode current = root;
        while (current != null) {
            if (compare(key, current.entry.getKey()) == 0) {
                return true;
            } else if (compare(key, current.entry.getKey()) < 0) {
                current = current.left;
            } else if (compare(key, current.entry.getKey()) > 0) {
                current = current.right;
            }
        }
        return false;
    }

    /**
     * Adds a generic key and value to the TreeMap.
     * @param key A generic key. Can be anything.
     * @param value A generic value. Can be anything.
     * @return True if the generic key was not found within the TreeMap, otherwise false.
    */
    public boolean add(K key, V value) {
        if (root == null) {
            root = new TreeNode(key, value);
            size++;
            return true;
        }

        TreeNode current = root;
        while (current != null) {
            if (compare(key, current.entry.getKey()) == 0) {
                return false;
            } else if (compare(key, current.entry.getKey()) < 0) {
                if (current.left == null) {
                    current.left = new TreeNode(key, value);
                    size++;
                    return true;
                }
                current = current.left;
            } else if (compare(key, current.entry.getKey()) > 0) {
                if (current.right == null) {
                    current.right = new TreeNode(key, value);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
        return false;
    }

    /**
     * Removes a generic key and value from the TreeMap.
     * @param key A generic key. Can be anything.
     * @return True if generic key and value was removed from the TreeMap, otherwise false.
    */
    public boolean remove(K key) {
        if (root == null) {
            return false;
        }
        TreeNode nodeToDelete = root, parent = null;
        while (nodeToDelete != null) {
            if (compare(key, nodeToDelete.entry.getKey()) == 0) {
                break;
            }
            parent = nodeToDelete;

            if (compare(key, nodeToDelete.entry.getKey()) < 0) {
                nodeToDelete = nodeToDelete.left;
            } else if (compare(key, nodeToDelete.entry.getKey()) > 0) {
                nodeToDelete = nodeToDelete.right;
            }
        }

        if (nodeToDelete == null) {
            return false;
        }
        if ((nodeToDelete.left == null) && (nodeToDelete.right == null)) {
            if (parent != null) {
                if (parent.left == nodeToDelete) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                root = null; size = 0;
            }
        } else if (nodeToDelete.left == null) {
            if (parent == null) {
                root = nodeToDelete.right;
            } else if (parent.left == nodeToDelete) {
                parent.left = nodeToDelete.right;
            } else {
                parent.right = nodeToDelete.right;
            }
        } else if (nodeToDelete.right == null) {
            if (parent == null) {
                root = nodeToDelete.left;
            } else if (parent.left == nodeToDelete) {
                parent.left = nodeToDelete.left;
            } else {
                parent.right = nodeToDelete.left;
            }
        } else {
            TreeNode rightMostCurrent = nodeToDelete.left;
            TreeNode rightMostParent = nodeToDelete;
            while (rightMostCurrent.right != null) {
                rightMostParent = rightMostCurrent;
                rightMostCurrent = rightMostCurrent.right;
            }

            nodeToDelete.entry = rightMostCurrent.entry;
            if (rightMostParent.left == rightMostCurrent) {
                rightMostParent.left = rightMostCurrent.left;
            } else {
                rightMostParent.right = rightMostCurrent.left;
            }
        }
        size--;
        return true;
    }

    /**
     * A helper method that compares two keys via natural ordering or custom ordering.
     * @param k1 A generic key. Can be anything.
     * @param k2 A second generic key. Can be anything.
     * @return An int that indicates whether k1 is less than, equal to, or greater than k2.
    */
    @SuppressWarnings("unchecked")
    private int compare(K k1, K k2) {
        if (comp == null) {
            return ((Comparable<K>) k1).compareTo(k2);
        }
        return comp.compare(k1, k2);
    }

    /**
     * Time complexity: O(n).
     * @return A key-value pair with the lowest key in the TreeMap, otherwise null.
    */
    public MapEntry<K, V> first() {
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return (root != null) ? current.entry : null;
    }

    /**
     * Time complexity: O(n).
     * @return A key-value pair with the highest key in the TreeMap, otherwise null.
    */
    public MapEntry<K, V> last() {
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return (root != null) ? current.entry : null;
    }

    /**
     * Time complexity: O(n).
     * @param key A generic key. Can be anything.
     * @return A key-value pair associated with the least key greater than or equal to k, otherwise null.
    */
    public MapEntry<K, V> ceiling(K key) {
        TreeNode current = root, ceiling = null;
        while (current != null) {
            if (compare(key, current.entry.getKey()) == 0) {
                return current.entry;
            } else if (compare(key, current.entry.getKey()) < 0) {
                ceiling = current;
                current = current.left;
            } else if (compare(key, current.entry.getKey()) > 0) {
                current = current.right;
            }
        }
        return (ceiling != null) ? ceiling.entry : null;
    }

    /**
     * Time complexity: O(n).
     * @param key A generic key. Can be anything.
     * @return A key-value pair associated with the greatest key less than or equal to k, otherwise null.
    */
    public MapEntry<K, V> floor(K key) {
        TreeNode current = root, floor = null;
        while (current != null) {
            if (compare(key, current.entry.getKey()) == 0) {
                return current.entry;
            } else if (compare(key, current.entry.getKey()) < 0) {
                current = current.left;
            } else if (compare(key, current.entry.getKey()) > 0) {
                floor = current;
                current = current.right;
            }
        }
        return (floor != null) ? floor.entry : null;
    }

    /**
     * Time complexity: O(n).
     * @return A collection of the keys in the TreeMap in a inorder traversal.
    */
    public Collection<K> keys() {
        List<K> keys = new ArrayList<>();
        keysTraversal(root, keys);
        return keys;
    }

    /**
     * Time complexity: O(n).
     * @return A collection of the values in the TreeMap in a inorder traversal.
    */
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        valuesTraversal(root, values);
        return values;
    }

    /**
     * The main method of inorder.
     * Prints the values of values of TreeMap in a inorder traversal.
    */
    public void inorder() {
        inorder(root);
    }

    /**
     * The helper method of inorder.
     * Prints the values of TreeMap in a inorder traversal.
     * @param node A node. inorder gets the entry of each node to print.
    */
    public void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.entry + " ");
            inorder(node.right);
        }
    }

    /**
     * A helper method for keys(). Mimics inorder().
     * Adds every key within TreeMap to a list.
     * @param node A node within the TreeMap.
     * @param list A list that contains every key within TreeMap.
    */
    private void keysTraversal(TreeNode node, List<K> list) {
        if (node != null) {
            keysTraversal(node.left, list);
            list.add(node.entry.getKey());
            keysTraversal(node.right, list);
        }
    }

    /**
     * A helper method for values(). Mimics inorder().
     * Adds every value within TreeMap to a list.
     * @param node A node within the TreeMap.
     * @param list A list that contains every value within TreeMap.
    */
    private void valuesTraversal(TreeNode node, List<V> list) {
        if (node != null) {
            valuesTraversal(node.left, list);
            list.add(node.entry.getValue());
            valuesTraversal(node.right, list);
        }
    }
}