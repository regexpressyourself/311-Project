import java.util.*;

public class QueueImplementation<E> extends ArrayDeque {

    /**
     * QueueImplementation is the object that will be manipulated by the Q Language code
     *
     * QueueImplementation makes use of Java's ArrayDequeue by extending it. No
     * methods are overwritten, but some are added to make QueueImplementation work
     * in line with the Q Programming Language.
     */

    public void view() {
        /**
         * view prints the queue in a readable way
         * @param  none
         * @return none
         */

        String   prettyView = "";             // The printed version of the queue
        Object[] arrayQueue = this.toArray(); // The queue in array form

        for (int i = 0; i < arrayQueue.length; i++) {
            prettyView += arrayQueue[i] + " <- ";
        }

        System.out.println(prettyView);
    }

    public void showFirst(String firstElement) {
        /**
         * showFirst is a version of peek that prints the first element rather than returning it
         *
         * @param  firstElement - the first element (attained by ArrayDeque's "peek()")
         * @return none
         */

        System.out.println("The first element is: " + firstElement);

    }
    public void getLength(int length) {
        /**
         * getLength is a version of size() that prints the length
         *
         * @param  the length (attained by ArrayDeque's "size()")
         * @return none
         */

        System.out.println("The length is: " + length);
    }

    public boolean isEmpty() {
        /**
         * isEmpty determines if the queue is empty
         *
         * @param  none
         * @return true if empty, false if not
         */

        return (this.size() == 0);
    }

    public boolean notEmpty() {
        /**
         * notEmpty determines if the queue is not empty
         *
         * @param  none
         * @return true if not empty, false if empty
         */

        return (this.size() > 0);
    }
}
