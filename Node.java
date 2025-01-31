// CLASS: Node
//
// Author: Jiawei Fan, 7909503
//
// REMARKS: Node structure for linked list
// 
//-----------------------------------------
public class Node<T> {
    public T data;
    public Node<T> next;
    
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}