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

    public boolean binarySearch(Type item) {
        return binarySearch(item, 0, list.size() - 1);
    }

    private boolean binarySearch(Type item, int start, int finish) {
        if (start > finish) {
            comparisons++;
            return false;
        }
        int mid = (start + finish) / 2;
        if (list.get(mid).compareTo(item) == 0) {
            comparisons++;
            return true;
        }
        if (list.get(mid).compareTo(item) > 0) {
            comparisons++;
            return binarySearch(item, start, mid - 1);
        }
        return binarySearch(item, mid + 1, finish);
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
