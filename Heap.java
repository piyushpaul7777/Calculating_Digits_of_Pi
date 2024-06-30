import java.util.*;

public class Heap {
    private List<Double> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    public void insert(double timestamp) {
        heap.add(timestamp);
        int index = heap.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(parent) <= heap.get(index))
                break;
            Collections.swap(heap, parent, index);
            index = parent;
        }
    }

    public double extractMin() {
        if (heap.isEmpty())
            throw new NoSuchElementException("Heap is empty");
        double min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        int index = 0;
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            if (leftChild >= heap.size())
                break;
            int minChild = leftChild;
            if (rightChild < heap.size() && heap.get(rightChild) < heap.get(leftChild))
                minChild = rightChild;
            if (heap.get(index) <= heap.get(minChild))
                break;
            Collections.swap(heap, index, minChild);
            index = minChild;
        }
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(3);

        while (!heap.isEmpty()) {
            System.out.println(heap.extractMin());
        }
    }
}
