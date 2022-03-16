public class LinkedListDeque<T> {                 // T --> some data type, whatever the user wants

    private class ItemNode {
        public T item;                           // head
        public ItemNode next;                    // tail containing reference to another node
        public ItemNode prev;

        public ItemNode(T i, ItemNode n, ItemNode p) {       // the nakedness within, handles the creation of new node
            item = i;
            next = n;
            prev = p;
        }

    }

    /** Keeping track of sentinel (dummy node) and size here.
     * First item if it exists is at sentinel.next */
    private ItemNode sentinel;
    private int size;

    /** List Constructors, either enter argument or leave args blank */
    public LinkedListDeque(T item) {
        sentinel = new ItemNode(null, null, null);              // dummy node
        sentinel.next = new ItemNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;                                   // prev item in list is also the first (only one item)
        size = 1;
    }

    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        size = 0;
    }

    /** Add an item to the head of the list */
    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new ItemNode(item, sentinel, sentinel);                 // if the list is empty, make next item circle back
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new ItemNode(item, sentinel.next, sentinel);            // new node pushes rest of list to back
            sentinel.next.next.prev = sentinel.next;                                // value AFTER new node should point to new node for prev
        }
        size++;
    }

    /** Add an item to the back of the list */
    public void addLast(T item) {
        if (size == 0) {
            sentinel.next = new ItemNode(item, sentinel, sentinel);                 // if the list is empty, make next item circle back
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev = new ItemNode(item, sentinel, sentinel.prev);            // new node will have old last node as prev and sentinel as next
            sentinel.prev.prev.next = sentinel.prev;                                // value BEFORE new node will point to new node for next
        }
        size++;
    }

    /** If the side of the list is zero, then its empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns cumulative size */
    public int size() {
        return size;
    }

    /** Prints out the list with an arrow */
    public void printDeque() {
        ItemNode pnt = sentinel.next;                           // pointer to keep track of each item throughout list
        while (pnt.item != null) {                              // print out  list until we reach the end
            System.out.print(pnt.item);
            if (pnt.next.item != null) { System.out.print(" --> "); }       // if the next item is null, dont print arrow
            pnt = pnt.next;
        } System.out.println();
    }

    /** Removes the first item of the list while maintaining circularity */
    public T removeFirst() {
        if (size == 0) { return null; }                     // if list is empty
        sentinel.next = sentinel.next.next;                 // sentinels new next is the one after the current first
        sentinel.next.prev = sentinel;                      // new next item should point to sentinel
        size--;
        return sentinel.next.item;
    }

    /** Removes the last item of the list while maintaining circularity */
    public T removeLast() {
        if (size == 0) { return null; }                     // if list is empty
        sentinel.prev = sentinel.prev.prev;                 // sentinels new prev is the one before the last
        sentinel.prev.next = sentinel;                      // new previous item will point to sentinel
        size--;
        return sentinel.prev.item;
    }

    /** Gets some item from a specified index iteratively */
    public T get(int index) {
        if (index <= size - 1) {
            int count = 0;
            ItemNode p = sentinel.next;
            while (p.item != null) {                              // as long as we dont reach sentinel (end of list)
                if (count++ == index)
                    return p.item;                              // if we get to target index, return that value (++ at end will add 1 when line is done)
                p = p.next;
            }
        } return null;
    }

    /** Helper function that uses sentinel as arg */
    private T getRecursive(ItemNode p, int index) {
        if (index == 0) { return p.item; }
        return getRecursive(p.next, index - 1);                      // getting the item of the next node
    }

    /** Gets some item from a specified index using recursion */
    public T getRecursive(int index) {
        if (index <= size - 1) {                                           // given index must be less than size of linked list
            return getRecursive(sentinel.next, index);
        } return null;
    }

}