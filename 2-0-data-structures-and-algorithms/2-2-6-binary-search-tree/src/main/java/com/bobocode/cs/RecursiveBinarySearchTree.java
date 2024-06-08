package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private int size;
    private Node<T> head;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> recursiveBinarySearchTree = new RecursiveBinarySearchTree<>();
        for (T element : elements) {
            recursiveBinarySearchTree.insert(element);
        }
        return recursiveBinarySearchTree;
    }

    @Override
    public boolean insert(T element) {
        Node<T> tNode = new Node<>(element);
        if (head == null) {
            head = tNode;
            size++;
            return true;
        } else {
            boolean isAdded = insertNode(tNode, head);
            if (isAdded) {
                size++;
            }
            return isAdded;
        }

    }

    private boolean insertNode(Node<T> tNode, Node<T> head) {
        if (head.getValue().equals(tNode.getValue())) {
            return false;
        }
        Node<T> left = head.getLeft();
        Node<T> right = head.getRight();

        if (tNode == left || tNode == right) {
            return false;
        }
        if (head.getValue().compareTo(tNode.getValue()) > 0) {
            if (left == null) {
                head.setLeft(tNode);
                return true;
            }
            return insertNode(tNode, left);
        } else {
            if (right == null) {
                head.setRight(tNode);
                return true;
            }
            return insertNode(tNode, right);
        }
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node<T> tNode = new Node<>(element);
        return checkContains(tNode, head);
    }

    private boolean checkContains(Node<T> node, Node<T> head) {
        if (head == null) {
            return false;
        }
        if (head.equals(node)) {
            return true;
        }
        if (head.getValue().compareTo(node.getValue()) > 0) {
           return checkContains(node, head.getLeft());
        } else {
           return checkContains(node, head.getRight());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        if (head == null) {
            return 0;
        }
        return countDepth(head, -1);
    }

    private int countDepth(Node<T> node, int depth) {
        if (node == null) {
            return depth;
        }

        int leftDepth = countDepth(node.getLeft(), depth + 1);
        int rightDepth = countDepth(node.getRight(), depth + 1);

        return Math.max(leftDepth, rightDepth);
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        List<T> values = new ArrayList<>();
        addValues(head, values);
        values.stream().sorted().forEach(consumer);
    }

    private void addValues(Node<T> head, List<T> values) {
        if (head != null) {
            values.add(head.getValue());
            addValues(head.getLeft(), values);
            addValues(head.getRight(), values);
        }
    }

    @Getter
    @Setter
    private static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        Node(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
