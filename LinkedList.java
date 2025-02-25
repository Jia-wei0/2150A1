// CLASS: LinkedList
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: Manages food item
// 
//-----------------------------------------
public class LinkedList<T> {
    private Node<T> head;
    private int size;

    //------------------------------------------------------
    // add
    //
    // PURPOSE:    Add element to list
    // PARAMETERS:
    //     data: element to add
    //------------------------------------------------------
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
        size++;
    }

    //------------------------------------------------------
    // find
    //
    // PURPOSE:    find data
    // PARAMETERS:
    //     key: The key to find
    //------------------------------------------------------
    public T find(String key) {
        Node<T> current = head;
        while (current != null) {
            if (current.data instanceof Food) {
                Food food = (Food) current.data;
                if (food.getDescription().equals(key)) return current.data;
            } 
            else if (current.data instanceof User) {
                User user = (User) current.data;
                if (user.getUsername().equals(key)) return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public Node<T> getHead() { return head; }
    public int size() { return size; }
}