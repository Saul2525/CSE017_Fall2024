/*
    Saul Toribio
    11/21/24
    CSE017 Fall 2024: ALA 9
    IDE: VSCode; JDK: 11
*/

/**
 * A class that implements a binary search tree data structure using linked nodes.
*/
public class TreeSet<E extends Comparable<E>> {
    public static int addIterations, containsIterations, removeIterations;
    private TreeNode root;
    private int size;

    /**
     * The class that defines what TreeNode is.
    */
    private class TreeNode {
        TreeNode left, right;
        E value;

        /**
         * Default configuration of a TreeNode.
         * @param value A generic value. Can be anything.
        */
        TreeNode(E value) {
            this.value = value;
        }
    }

    /**
     * Default configuration of a TreeSet.
    */
    public TreeSet() {
        root = null;
        size = 0;
    }

    /**
     * @return The size of the TreeSet.
    */
    public int size() {
        return size;
    }

    /**
     * @return True if the size of the TreeSet is 0, otherwise false.
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Clears the TreeSet.
    */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Checks if the generic element is within the TreeSet.
     * @param element A generic element. Can be anything.
     * @return True if generic element is within the TreeSet, otherwise false.
    */
    public boolean contains(E element) {
        containsIterations = 0;
        if (root == null) {
            return false;
        }
        TreeNode current = root;

        while (current != null) {
            containsIterations++;
            if (element.compareTo(current.value) == 0) {
                return true;
            } else if (element.compareTo(current.value) < 0) {
                current = current.left;
            } else if (element.compareTo(current.value) > 0) {
                current = current.right;
            }
        }
        return false;
    }

    /**
     * Adds a generic element to the TreeSet.
     * @param element A generic element. Can be anything.
     * @return True if generic element was added to the TreeSet, otherwise false.
    */
    public boolean add(E element) {
        TreeNode newNode = new TreeNode(element);
        addIterations = 0;
        if (root != null) {
            TreeNode current = root, parent = null;
            while (current != null) {
                addIterations++;
                parent = current;
                if (element.compareTo(current.value) == 0) {
                    return false;
                } else if (element.compareTo(current.value) < 0) {
                    current = current.left;
                } else if (element.compareTo(current.value) > 0) {
                    current = current.right;
                }
            }

            if (element.compareTo(parent.value) < 0) {
                parent.left = newNode;
            } else if (element.compareTo(parent.value) > 0) {
                parent.right = newNode;
            }
        } else {
            root = newNode;
        }
        size++;
        return true;
    }

    /**
     * Removes a generic element from the TreeSet.
     * @param element A generic element. Can be anything.
     * @return True if the generic element was removed from the TreeSet.
    */
    public boolean remove(E element) {
        if (root == null) {
            return false;
        }
        TreeNode nodeToDelete = root, parent = null;
        removeIterations = 0;

        while (nodeToDelete != null) {
            removeIterations++;
            if (element.compareTo(nodeToDelete.value) == 0) {
                break;
            }
            parent = nodeToDelete;

            if (element.compareTo(nodeToDelete.value) < 0) {
                nodeToDelete = nodeToDelete.left;
            } else if (element.compareTo(nodeToDelete.value) > 0) {
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
                removeIterations++;
                rightMostParent = rightMostCurrent;
                rightMostCurrent = rightMostCurrent.right;
            }

            nodeToDelete.value = rightMostCurrent.value;
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
     * The main method of preorder.
     * Prints the values of TreeSet in a preorder traversal.
    */
    public void preorder() {
        System.out.print("[");
        preorder(root);
        System.out.print("]");
    }

    /**
     * The helper method of preorder.
     * Prints the values of TreeSet in a preorder traversal.
     * @param node A node. preorder gets the value of node to print.
    */
    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * The main method of inorder.
     * Prints the values of values of TreeSet in a inorder traversal.
    */
    public void inorder() {
        System.out.print("[");
        inorder(root);
        System.out.print("]");
    }

    /**
     * The helper method of inorder.
     * Prints the values of TreeSet in a inorder traversal.
     * @param node A node. inorder gets the value of node to print.
    */
    public void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    /**
     * The main method of postorder.
     * Prints the values of values of TreeSet in a postorder traversal.
    */
    public void postorder() {
        System.out.print("[");
        postorder(root);
        System.out.println("]");
    }

    /**
     * The helper method of postorder.
     * Prints the values of TreeSet in a postorder traversal.
     * @param node A node. postorder gets the value of node to print.
    */
    private void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }

    /**
     * The main method of height.
     * Time complexity: O(n).
     * @return The height of the TreeSet.
    */
    public int height() {
        return height(root);
    }

    /**
     * The helper method of height.
     * Time complexity: O(n).
     * @param node A node.
     * @return The height of the TreeSet.
    */
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if ((node.left == null) && (node.right == null)) {
            return 1;
        }
        return (1 + Math.max(height(node.left), height(node.right)));
    }

    /**
     * The main method of isBalanced.
     * Time complexity: O(n^2).
     * @return True if the height difference between the left and right subtrees of every node is at most 1.
    */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * The helper method of isBalanced.
     * Time complexity: O(n^2).
     * @param node A node.
     * @return True if the height difference between the left and right subtrees of every node is at most 1.
    */
    private boolean isBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }

        int hL = height(node.left);
        int hR = height(node.right);
        if (Math.abs(hL - hR) > 1) {
            return false;
        }

        return (isBalanced(node.left) && isBalanced(node.right));
    }
}