package algorithms.binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree {
    private final Node root;

    public static class Node {
        char value;
        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
        }
    }

    public BinaryTree(Node root) {
        this.root = root;
    }
//           Обход в глубину (Прямой обход)
    public void dfs(Node node, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }

        stringBuilder.append(node.value).append(' ');
        dfs(node.left, stringBuilder);
        dfs(node.right, stringBuilder);
    }
//          Обход в ширину
    public void bfs(StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            stringBuilder.append(current.value).append(' ');
            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

    }

    public static void main(String[] args) {
        //         A
        //       /   \
        //      B     C
        //     / \     \
        //    D   E     F
        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');
        Node f = new Node('F');

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.right = f;

        BinaryTree tree = new BinaryTree(a);
        StringBuilder stringBuilderDFS = new StringBuilder();
        tree.dfs(tree.root, stringBuilderDFS);
        System.out.println("Обход в глубину (Прямой обход): " + stringBuilderDFS);

        StringBuilder stringBuilderBFS = new StringBuilder();
        tree.bfs(stringBuilderBFS);
        System.out.println("Обход в ширину: " + stringBuilderBFS);
    }
}