package com.szy.multitouch;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    static class Node {
        public int value;
        public Node left;
        public Node right;
    }

    public Node buildTree(int[] values) {
        Node[] list = new Node[values.length];
        for (int i = 0; i < values.length; i++) {
            Node node = new Node();
            node.value = values[i];
            list[i] = node;
        }

        for (int i = 0; i < values.length; i++) {
            if (2 * i + 1 < values.length) {
                list[i].left = list[2 * i + 1];
            }

            if (2 * i + 2 < values.length) {
                list[i].right = list[2 * i + 2];
            }
        }

        return list[0];
    }

    public void print(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        print(root.left);
        print(root.right);
    }

    public void print1(Node root) {
        Stack<Node> stack = new Stack<>();

        Node node = root;
        int i = 0;
        while (node != null) {
            System.out.println(node.value + "");
            stack.push(node);
            if (node.left != null) {
                node = node.left;
            } else {
                if (!stack.isEmpty()) {
                    node = stack.pop();
                }
                while (!stack.isEmpty() && node.right == null) {
                    node = stack.pop();
                }
                node = node.right;
            }
        }
    }

    @Test
    public void addition_isCorrect() {
        int[] values = new int[] {0,1,2,3,4,5,6,7,8,9};
        Node node = buildTree(values);
        System.out.println("\n         " + node.value
                + "\n      " + node.left.value + "      " + node.right.value
                + "\n   " + node.left.left.value + "    " + node.left.right.value + "   " + node.right.left.value + " " + node.right.right.value
                + "\n  " + node.left.left.left.value + " " + node.left.left.right.value + " " + node.left.right.left.value);

        print1(node);
    }
}