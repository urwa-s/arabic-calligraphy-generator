import java.awt.*;

public class StateManager {
    private Node<State> currentNode;
    private Node<State> head; 
    private Node<State> tail;

    public StateManager() {
        head = null; 
        tail = null;
        currentNode = null;
    }

    public void addState(Color backgroundColor, Font font, Color fontColor, int textSize) {
        while (currentNode != null && currentNode.next != null) {
            removeLast();
        }
        State newState = new State(backgroundColor, font, fontColor, textSize);
        Node<State> newNode = new Node<>(newState);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        currentNode = tail;
    }

    public State getCurrentState() {
        if (currentNode != null) {
            return currentNode.data; 
        } else {
            return null; 
        }
    }

    public State getPreviousState() {
        if (currentNode != null && currentNode.prev != null) {
            currentNode = currentNode.prev;
            return currentNode.data;
        }
        return null;
    }

    public State getNextState() {
        if (currentNode != null && currentNode.next != null) {
            currentNode = currentNode.next;
            return currentNode.data;
        }
        return null; 
    }

    public boolean hasPrevious() {
        return currentNode != null && currentNode.prev != null; 
    }

    public boolean hasNext() {
        return currentNode != null && currentNode.next != null; // Check if there is a next state
    }

    public void removeLast() {
        if (tail != null) {
            if (tail.prev != null) {
                tail = tail.prev;
                tail.next = null;
            } else {
                head = null;
                tail = null;
            }
        }
    }

    public class State {
        Color backgroundColor;
        Font font;
        Color fontColor;
        int textSize;

        public State(Color backgroundColor, Font font, Color fontColor, int textSize) {
            this.backgroundColor = backgroundColor;
            this.font = font;
            this.fontColor = fontColor;
            this.textSize = textSize;
        }
    }
    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
}