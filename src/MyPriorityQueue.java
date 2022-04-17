public class MyPriorityQueue<Type extends Comparable<Type>> {
    private MyArrayList<Type> heap;

    public MyPriorityQueue() {
        heap = new MyArrayList<>();
    }

    public void insert(Type value) {
        heap.insert(value, heap.size());
        bubbleUp();
    }

    public Type removeMin() {
        Type min = min();
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        sinkDown();
        return min;
    }

    public Type min()   {
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() { return heap.isEmpty();}

    public String toString() {
        return heap.toString();
    }

    private void bubbleUp() {
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = parent(index);
            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void sinkDown() {
        int index = 0;
        while (index < heap.size()) {
            int leftChildIndex = left(index);
            int rightChildIndex = right(index);
            int minIndex = index;
            if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(minIndex)) < 0) {
                minIndex = leftChildIndex;
            }
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(minIndex)) < 0) {
                minIndex = rightChildIndex;
            }
            if (minIndex != index) {
                swap(index, minIndex);
                index = minIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Type temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private int parent(int index) {
        return index / 2;
    }

    private int right(int index) {
        return (2 * index) + 2;
    }

    private int left(int index) {
        return (2 * index) + 1;
    }
}
