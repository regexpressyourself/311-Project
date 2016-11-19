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

    public String view() {
        String prettyView;
        /**
         * TODO - write a method to iterate through the queue and print it in a friendly way
         */
        return prettyView;
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public boolean notEmpty() {
        return (this.size() > 0);
    }
}
