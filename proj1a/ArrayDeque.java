public class ArrayDeque<T> {                                        // of some data type

    /** Keeping track of pointers and size here.
     * will keep track of where items should be placed if (before or after) */
    private int front;
    private int back;
    private T[] items;
    private int size;

    /** List Constructors, either enter argument or leave args blank */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = back = -1;
        size = 0;
    }

    public ArrayDeque(T x) {
        items = (T[]) new Object[8];
        items[0] = x;
        front = items.length - 1;
        back = 1;
        size = 1;
    }

    /** Returns cumulative size */
    public int size() { return size; }

    /** If the side of the list is zero, then its empty */
    public boolean isEmpty() { return size == 0; }

    /** If the side of the list matches that of the length, then it's full */
    public boolean isFull() { return size == items.length;  // OR (front == 0 && back == items.length - 1) || (front == back + 1)
    }

    public void resize() {
        // if were trying to add number and it makes size == length, then we need to resize
        if (size + 1 >= items.length) {
            int i = 0;
            T[] newArray = (T[]) new Object[items.length * 2];      // want to copy all of items from front to back starting at 0
            for (int j = front + 1; j < items.length; i++, j++) {
                newArray[i] = items[j];
            }
            for (int j = back - 1; j >= 0; i++, j--) {
                newArray[i] = items[j];
            }
            items = newArray;
            front = items.length - 1;
            back = i;               // the current end/back for the new array
        }
//         if were not using over 25% of the array, cut it in half                          THIS IS SO HARD WTF
        else if ((double) size / items.length < .25) {
            T[] newArray = (T[]) new Object[items.length / 2];

            if (!isEmpty()) {
                int i = 0;

                if (front != -1) {
                    for (int j = front + 1; j < items.length; i++, j++) {
                        newArray[i] = items[j];
                    }
                }

                for (int j = back - 1; j >= 0; i++, j--) {
                    newArray[i] = items[j];
                }
                front = newArray.length -1;
            }
            items = newArray;                                                           // I literally cant believe I figured this out
        }

    }

    /** Add an item to the head of the list, depending on where you are in list */
    public void addFirst(T x) {
        resize();
        if (isEmpty() && (front == back)) {          // this condition handles starting point
            front = back = 0;
            items[front] = x;
            back++;

        } else if (front < 0) {                                 // if we are at the start of the list (negative index)
            front = items.length - 1;
            items[front] = x;

        } else {
            items[front] = x;

        } front--;
          size++;
    }

    /** Add an item to the back of the list, depending on where you are in list */
    public void addLast(T x) {
        resize();
        if (isEmpty() && (front == -1 && back == -1)) {        // this condition handles starting point
            front = back = 0;
            items[back] = x;

        } else if (back > items.length - 1) {                // if we reach the end of list (out of bounds)
            back = 0;
            items[back] = x;

        } else {
            items[back] = x;

        } back++;
          size++;
    }

    /** Prints out the list with an arrow */
    public void printDeque() {
        int pointer = front + 1;
        while (pointer <= items.length - 1) {            // print out items until we reach end of list
            System.out.print(items[pointer] + " ");
            pointer++;
        }
        if (front != 0) {                             // then we have to print out the beginning of list until where we began
            for (int i = 0; i < back; i++)              // go up to the last item which is in back
                System.out.print(items[i] + " ");
        }

    }

    /** Removes the first item of the list while maintaining circularity */
    public void removeFirst() {
        if (!isEmpty()) {
            if (front + 1 > items.length) {
                front = 0;
            } else {
                front++;
            }
            items[front] = null;
            size--;
        }
    }
    /** Removes the last item of the list while maintaining circularity */
    public void removeLast() {
        if (!isEmpty()) {
            if (back - 1 < 0) {
                back = items.length - 1;
            } else {
                back--;
            }
            items[back] = null;
            size--;
        }
    }

    /** Clears content of the list completely */
    public void clear() {
        for (int i = front + 1; i < items.length; i++)          // go through first until end
            items[i] = null;
        for (int i = 0; i < back; i++)                     // then last if there any stuff left to delete (better than going through whole array)
            items[i] = null;
        size = 0;
        back = front = -1;
    }

    /** Gets some item from a specified index iteratively */
    public T get(int x) {
        if (x <= size - 1) return items[x];
        return null;
    }


}