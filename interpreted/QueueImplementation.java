import java.util.*;

public class QueueImplementation<E> extends ArrayDeque {

    /**
     * ArrayDeque has implementation of most of our queue methods for us. These include:
     *
     * add(element) - adds to end of queue
     * remove()     - removes and returns first element in queue
     * peek()       - returns but does not remove first element in queue
     * size()       - returns the number of elements in the queue
     */

    public void view() {
        // view the queue
        String    prettyView = "";
        Object[] arrayQueue = this.toArray();

        for (int i = 0; i < arrayQueue.length; i++) {
            prettyView += arrayQueue[i] + " <- ";
        }
        System.out.println(prettyView);
    }

    public void showFirst(String firstElement) {
        // our version of peek() that prints
        System.out.println("The first element is: " + firstElement);

    }
    public void getLength(int length) {
        // our version of size() that prints
        System.out.println("The length is: " + length);
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean notEmpty() {
        return (this.size() > 0);
    }
}
