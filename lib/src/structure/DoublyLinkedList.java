// Implementation of lists, using doubly linked elements.
// (c) 1998, 2001 duane a. bailey
package structure;
import java.util.Iterator;
/**
 * An implementation of lists that makes use of doubly linked elements.
 * This provided efficient implementation of both head and tail operations.
 * 
 * @version $Id: DoublyLinkedList.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 */
public class DoublyLinkedList extends AbstractList
{
    /**
     * Number of elements within list.
     */
    protected int count;
    /**
     * Reference to head of list.
     */
    protected DoublyLinkedNode head;
    /**
     * Reference to tail of list.
     */
    protected DoublyLinkedNode tail;

    /**
     * Constructs an empty list.
     *
     * @post constructs an empty list
     * 
     */
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Add a value to head of list.
     *
     * @post adds value to beginning of list
     * 
     * @param value value to be added.
     * @see #addToHead
     */
    public void add(Object value)
    {
        addFirst(value);
    }
    
    /**
     * Add a value to head of list.
     *
     * @pre value is not null
     * @post adds element to head of list
     * 
     * @param value value to be added.
     */
    public void addFirst(Object value)
    {
        // construct a new element, making it head
        head = new DoublyLinkedNode(value, head, null);
        // fix tail, if necessary
        if (tail == null) tail = head;
        count++;
    }

    /**
     * Remove a value from head of list.
     * Value is returned.
     *
     * @pre list is not empty
     * @post removes first value from list
     * 
     * @return value removed from list.
     */
    public Object removeFirst()
    {
        Assert.pre(!isEmpty(),"IList is not empty.");
        DoublyLinkedNode temp = head;
        head = head.next();
        if (head != null) {
            head.setPrevious(null);
        } else {
            tail = null; // remove final value
        }
        temp.setNext(null);// helps clean things up; temp is free
        count--;
        return temp.value();
    }

    /**
     * Add a value to tail of list.
     *
     * @pre value is not null
     * @post adds new value to tail of list
     * 
     * @param value value to be added.
     */
    public void addLast(Object value)
    {
        // construct new element
        tail = new DoublyLinkedNode(value, null, tail);
        // fix up head
        if (head == null) head = tail;
        count++;
    }

    /**
     * Remove a value from tail of list.
     *
     * @pre list is not empty
     * @post removes value from tail of list
     * 
     * @return value removed from list.
     */
    public Object removeLast()
    {
        Assert.pre(!isEmpty(),"IList is not empty.");
        DoublyLinkedNode temp = tail;
        tail = tail.previous();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        count--;
        return temp.value();
    }
    /*
    public void addLast(Object value)
    {
        // construct new element
        tail = new DoublyLinkedNode(value, null, tail);
        count++;
    }

    public Object removeLast()
    {
        Assert.pre(!isEmpty(),"IList is not empty.");
        DoublyLinkedNode temp = tail;
        tail = tail.previous();
        tail.setNext(null);
        count--;
        return temp.value();
    }
    */

    /**
     * Get a copy of first value found in list.
     *
     * @pre list is not empty
     * @post returns first value in list
     * 
     * @return A reference to first value in list.
     */
    public Object getFirst()
    {
        return head.value();
    }

    /**
     * Get a copy of last value found in list.
     *
     * @pre list is not empty
     * @post returns last value in list
     * 
     * @return A reference to last value in list.
     */
    public Object getLast()
    {
        return tail.value();
    }

    /**
     * Check to see if a value is within list.
     *
     * @pre value not null
     * @post returns true iff value is in list
     * 
     * @param value A value to be found in list.
     * @return True if value is in list.
     */
    public boolean contains(Object value)
    {
        DoublyLinkedNode finger = head;
        while ((finger != null) && (!finger.value().equals(value)))
        {
            finger = finger.next();
        }
        return finger != null;
    }

    /**
     * Remove a value from list.  At most one value is removed.
     * Any duplicates remain.  Because comparison is done with "equals,"
     * actual value removed is returned for inspection.
     *
     * @pre value is not null.  IList can be empty
     * @post first element matching value is removed from list
     * 
     * @param value value to be removed.
     * @return value actually removed.
     */
    public Object remove(Object value)
    {
        DoublyLinkedNode finger = head;
        while (finger != null &&
               !finger.value().equals(value))
        {
            finger = finger.next();
        }
        if (finger != null)
        {
            // fix next field of element above
            if (finger.previous() != null)
            {
                finger.previous().setNext(finger.next());
            } else {
                head = finger.next();
            }
            // fix previous field of element below
            if (finger.next() != null)
            {
                finger.next().setPrevious(finger.previous());
            } else {
                tail = finger.previous();
            }
            count--;            // fewer elements
            return finger.value();
        }
        return null;
    }

    /**
     * Determine number of elements in list.
     *
     * @post returns number of elements in list
     * 
     * @return number of elements found in list.
     */
    public int size()
    {
        return count;
    }

    /**
     * Determine if list is empty.
     *
     * @post returns true iff list has no elements
     * 
     * @return True iff list has no values.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Remove all values from list.
     *
     * @post removes all elements from list
     */
    public void clear()
    {
        head = tail = null;
        count = 0;
    }

    /**
     * Get value at location i.
     *
     * @pre 0 <= i < size()
     * @post returns object found at that location
     *
     * @param i position of value to be retrieved.
     * @return value retrieved from location i (returns null if i invalid)
     */
    public Object get(int i)
    {
        if (i >= size()) return null;
        DoublyLinkedNode finger = head;
        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }
        // not end of list, return value found
        return finger.value();
    }

    /**
     * Set value stored at location i to object o, returning old value.
     *
     * @pre 0 <= i < size()
     * @post sets ith entry of list to value o, returns old value
     * @param i location of entry to be changed.
     * @param o new value
     * @return former value of ith entry of list.
     */
    public Object set(int i, Object o)
    {
        if (i >= size()) return null;
        DoublyLinkedNode finger = head;
        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }
        // get old value, update new value
        Object result = finger.value();
        finger.setValue(o);
        return result;
    }

    /**
     * Insert value at location.
     *
     * @pre 0 <= i <= size()
     * @post adds ith entry of list to value o
     * @param i index of this new value
     * @param o value to be stored
     */
    public void add(int i, Object o)
    {
        Assert.pre((0 <= i) &&
                   (i <= size()), "Index in range.");
        if (i == 0) addFirst(o);
        else if (i == size()) addLast(o);
        else {
            DoublyLinkedNode before = null;
            DoublyLinkedNode after = head;
            // search for ith position, or end of list
            while (i > 0)
            {
                before = after;
                after = after.next();
                i--;
            }
            // create new value to insert in correct position
            DoublyLinkedNode current =
                new DoublyLinkedNode(o,after,before);
            count++;
            // make after and before value point to new value
            before.setNext(current);
            after.setPrevious(current);
        }
    }

    /**
     * Remove and return value at location i.
     *
     * @pre 0 <= i < size()
     * @post removes and returns object found at that location
     *
     * @param i position of value to be retrieved.
     * @return value retrieved from location i (returns null if i invalid)
     */
    public Object remove(int i)
    {
        Assert.pre((0 <= i) &&
                   (i < size()), "Index in range.");
        if (i == 0) return removeFirst();
        else if (i == size()-1) return removeLast();
        DoublyLinkedNode previous = null;
        DoublyLinkedNode finger = head;
        // search for value indexed, keep track of previous
        while (i > 0)
        {
            previous = finger;
            finger = finger.next();
            i--;
        }
        previous.setNext(finger.next());
        finger.next().setPrevious(previous);
        count--;
        // finger's value is old value, return it
        return finger.value();
    }

    /**
     * Determine first location of a value in list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of value,
     *   or -1 if value is not found
     * 
     * @param value value sought.
     * @return index (0 is first element) of value, or -1
     */
    public int indexOf(Object value)
    {
        int i = 0;
        DoublyLinkedNode finger = head;
        // search for value or end of list, counting along way
        while (finger != null && !finger.value().equals(value))
        {
            finger = finger.next();
            i++;
        }
        // finger points to value, i is index
        if (finger == null)
        {   // value not found, return indicator
            return -1;
        } else {
            // value found, return index
            return i;
        }
    }

    /**
     * Determine last location of a value in list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of value,
     *   or -1 if value is not found
     * 
     * @param value value sought.
     * @return index (0 is first element) of value, or -1
     */
    public int lastIndexOf(Object value)
    {
        int i = size()-1;
        DoublyLinkedNode finger = tail;
        // search for last matching value, result is desired index
        while (finger != null && !finger.value().equals(value))
        {
            finger = finger.previous();
            i--;
        }
        if (finger == null)
        {   // value not found, return indicator
            return -1;
        } else {
            // value found, return index
            return i;
        }
    }

    /**
     * Construct an iterator to traverse list.
     *
     * @post returns iterator that allows traversal of list
     * 
     * @return An iterator that traverses list from head to tail.
     */
    public Iterator iterator()
    {
        return new DoublyLinkedListIterator(head);
    }

    /**
     * Construct a string representation of list.
     *
     * @post returns a string representing list
     * 
     * @return A string representing elements of list.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<DoublyLinkedList:");
        Iterator li = iterator();
        while (li.hasNext())
        {
            s.append(" "+li.next());
        }
        s.append(">");
        return s.toString();
    }
}


