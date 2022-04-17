public class MyOrderedList <Type extends Comparable<Type>>{
    private MyArrayList<Type> list;
    public int comparisons;

    public MyOrderedList() {
        list = new MyArrayList<>();
    }

    public void add(Type item) {
        int i = 0;
        while (i < list.size() && list.get(i).compareTo(item) < 0) {
            comparisons++;
            i++;
        }
        list.insert(item, i);
    }

    public Type remove(Type item) {
        // remove item from list
        int i = 0;
        while (i < list.size() && list.get(i).compareTo(item) < 0) {
            i++;
        }
        if (i < list.size() && list.get(i).compareTo(item) == 0) {
            return list.remove(i);
        }
        return null;
    }

    public Type binarySearch(Type item) {
        return binarySearch(item, 0, list.size() - 1);
    }

    private Type binarySearch(Type item, int start, int finish) {
        if (start > finish) {
            return null;
        }
        int mid = (start + finish) / 2;
        comparisons++;

        if (list.get(mid).compareTo(item) == 0) {
            comparisons++;
            return list.get(mid);

        } else if (list.get(mid).compareTo(item) < 0) {
            comparisons++;
            return binarySearch(item, mid + 1, finish);

        } else {
            return binarySearch(item, start, mid - 1);
        }
    }

    public Type get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public String toString() {
        return list.toString();
    }
}
