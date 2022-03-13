public class ArrayDeque {
    public static void main(String[] args) {
        LinkedListDeque<Integer> mylist = new LinkedListDeque<>();

        mylist.addLast(3);
        mylist.addLast(5);
        mylist.addLast(1);
        mylist.addFirst(10);

        System.out.println(mylist.getRecursive(2));




    }
}