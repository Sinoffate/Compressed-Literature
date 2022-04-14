public class MyArrayList<Type extends Comparable<Type>> {
    private Type[] list;
    private int capacity;
    private int size;
    public int comparisons;

    public MyArrayList() {
        capacity = 16;
        list = (Type[]) new Comparable[capacity];
        size = 0;
    }

    public void insert(Type item, int index) {
        if (index < 0 || index > size) {
            return;
        }
        if (size == capacity) {
            resize();
        }
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    public Type remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Type item = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        size--;
        return item;
    }

    public boolean contains(Type item) {
        comparisons++;
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (list[i].compareTo(item) == 0) {
                return true;
            }
        }

        return false;
    }

    public int indexOf(Type item) {
        for (int i = 0; i < size; i++) {
            if (list[i].compareTo(item) == 0) {
                return i;
            }
        }
        return -1;
    }

    public Type get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return list[index];
    }

    public void set(int index, Type item) {
        if (index < 0 || index >= size) {
            return;
        }
        list[index] = item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void sort() {
        quickSort(0, size - 1);

    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int p = partition(low, high);
            quickSort(low, p - 1);
            quickSort(p + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = list[high].compareTo(list[low]);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list[j].compareTo(list[high]) < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Type temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    private void resize() {
        capacity *= 2;
        Type[] temp = (Type[]) new Comparable[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = list[i];
        }
        list = temp;
    }
}
