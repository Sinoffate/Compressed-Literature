public class MyLinkedList<Type extends Comparable<Type>> {
    private Node first;
    private Node current;
    private Node previous;
    private int size;
    public int comparisons;

    private class Node {
        public Type item;
        public Node next;

        public Node(Type item) {
            this.item = item;
            this.next = null;
        }

        public String toString() {
            return item.toString();
        }
    }

    public MyLinkedList() {
        first = null;
        size = 0;
    }

    public void addBefore(Type item) {
        // If current is null
        if(current == null && first == null){
            addFirst(item);
        }
        // If current is null and first = previous add to the end of the list and connect
        else if(current == null &&  first == previous) {
            addLast(item);
            first.next = previous;
        }
        // If current is null add to the end of the list and connect
        else if(current == null) {
            addLast(item);
        }
        //if current is NOT null add new node before current and reconnect chain
        else if(current != null){
            if (current == first){
                Node tempNode = new Node(item);
                tempNode.next = current;
                previous = tempNode;
                first = tempNode;
            } else {
                Node tempNode = new Node(item);
                tempNode.next = current;
                previous.next = tempNode;
                previous = tempNode;
            }
        }
        size++;
    }

    private void addFirst(Type item) {
        first = new Node(item);
        previous = first;
    }

    private void addLast(Type item) {
        Node tempNode = new Node(item);
        previous.next = tempNode;
        previous = tempNode;
    }

    public void addAfter(Type item) {
        if (current == null) {
            return;
        }
        if (first == null) {
            first = new Node(item);
            current = first;
        }
        else {
            Node newNode = new Node(item);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    // add to the end of the list
    public void addToLast(Type item) {
        if (first == null) {
            first = new Node(item);
            current = first;
        } else {
            current.next = new Node(item);
            current = current.next;
        }
        size++;
    }

    public Type first() {
        if (first == null) {
            return null;
        }
        current = first;
        previous = null;
        return current.item;
    }

    public Type current() {
        if (current == null) {
            return null;
        }
        return current.item;
    }

    public Type next() {
        if (current == null) {
            return null;
        }
        previous = current;
        current = current.next;
        return current();
    }

    public Type remove() {
        if (current == null) {
            return null;
        }
        Type item = current.item;
        if (previous == null) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
        current = current.next;
        size--;
        return item;
    }

    public void sort() {
        quickSort(first, size);
    }

    private void quickSort(Node first, int size) {
        if (size <= 1) {
            return;
        }
        Node current = first.next;
        Node next;
        int left = 0;
        int right = size - 1;
        while (current != null) {
            next = current.next;
            if (current.item.compareTo(first.item) < 0) {
                swap(current, first);
                left++;
            } else if (current.item.compareTo(first.item) > 0) {
                swap(current, first.next.next);
                right--;
            }
            current = next;
        }
        assert first.next != null;   // fix the IDE suggested for potential null pointer exception
        swap(first, first.next);
        quickSort(first, left);
        quickSort(first.next.next, right);
    }

    private void swap(Node current, Node next) {
        Type temp = current.item;
        current.item = next.item;
        next.item = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = first;
        while (current != null) {
            sb.append(current.item);
            current = current.next;
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean contains(Type item) {
        Node current = first;
        comparisons++;
        while (current != null) {
            comparisons++;
            if (current.item.compareTo(item) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
