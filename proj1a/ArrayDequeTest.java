public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(4);
        test.addFirst(10);
        test.addFirst(20);
        test.addFirst(1);
        test.addFirst(15);
        test.addLast(10);
        test.addLast(20);
        test.addLast(1);
        test.addFirst(20);
        test.addFirst(1);
        test.addFirst(15);
        test.addLast(10);
        test.addLast(20);
        test.addLast(1);
        test.addLast(15);

        test.clear();

        test.addFirst(4);
        test.addFirst(10);


    }
}
