package MusicProject;
/**
 * Name: Elena Tomson
 * Email: etomson@ucsd.edu
 * PID: A16798221
 * Sources used: None
 * 
 * Defines a linked list class that connects a string of linked nodes of data. 
 * List obect points to the head and tail and each node points the the one 
 * before and after itself.
 * Also has an iterator for the list.
 */

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/** 
 * A double-linked list with a dummy head node and dummy tail node.
 * Extends AbstractList
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;


	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	public class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		/* Add your implementation here */
		// 
		this.head = new Node(null);
		this.tail = new Node(null);
		this.tail.setPrev(this.head);
		this.head.setNext(this.tail);
		this.size = 0;

	}

	/** 
	 * @return the size of the list
	 */ 
	@Override
	public int size() {
		// need to implement the size method
		return this.size; 
	}

	/**
	 * @param index - position in list to be returned.
	 * @return data from node at index
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= size){	//FIX added "="
			throw new IndexOutOfBoundsException();
		}
		return (E) getNth(index).getElement();  // 
	}

	/**
	 * adds an element to the array at the given index
	 * @param index - position in array for new node to be
	 * @param data - information stored in new node
	 */
	@Override
	public void add(int index, E data) {
		/* Add your implementation here */
		// 
		Node curNode;
		Node newNode = new Node(data);

		if(data == null){
			throw new NullPointerException("add was given a null data");
		}
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		if(isEmpty()){
			tail.setPrev(newNode);
			head.setNext(newNode);
			newNode.setPrev(head);
			newNode.setNext(tail);;
			size ++;
		}
		else{
			curNode = getNth(index);
			newNode.setNext(curNode);
			newNode.setPrev(curNode.getPrev());
			curNode.getPrev().setNext(newNode);
			curNode.setPrev(newNode);
			size++;
		}
	}

	/**
	 * appends a node to the end of the list
	 * @param data - information to be stored in node at end of list
	 * @return true always
	 */
	public boolean add(E data) {

		Node newNode = new Node(data);

		if(data == null){
			throw new NullPointerException("add was given a null data");
		}
		if(isEmpty()){
			tail.setPrev(newNode);
			head.setNext(newNode);
			newNode.setPrev(head);
			newNode.setNext(tail);;
			size ++;
		}
		else{
			Node curNode = new Node(data);
			curNode.setPrev(this.tail.getPrev());
			curNode.setNext(this.tail);
			this.tail.getPrev().setNext(curNode);
			this.tail.setPrev(curNode);
			this.size++;
		}
		return true; // 
	}

	/**
	 * sets the data in a specified node
	 * @param index - position of node to be replaced
	 * @param data- new data for the node
	 * @return original data of the node
	 */
	public E set(int index, E data) {
		if(data == null){
			throw new NullPointerException("set was given a null data");
		}
		if(index < 0 || index >= size){	//FIX added "="
			throw new IndexOutOfBoundsException();
		}
		Node curNode = getNth(index);
		E toReturn = get(index);
		curNode.setElement(data);
		return (E) toReturn; // 
	}

	/**
	 * removes node at index
	 * @param index - location of node to be removed
	 * @return removed node
	 */
	public E remove(int index) {
		if(index < 0 || index >= size){	//FIX added "="
			throw new IndexOutOfBoundsException();
		}
		Node curNode = getNth(index);
		E toReturn = get(index);
		curNode.getNext().setPrev(curNode.getPrev());
		curNode.getPrev().setNext(curNode.getNext());
		curNode = null;
		size--;
		return (E) toReturn; // 
	}

	//removes all elements from list, leaving only a head and tail
	public void clear() {
		while(size > 0){
			remove(0);
		}
	}

	/**
	 * @return true is list is empty and false otherwise
	 */
	public boolean isEmpty() {
		if(this.size == 0){
			return true;
		}  
		return false;
	}

	/**
	 * @param index - location of Node to be returned
	 * @return Node at given index
	 */
	protected Node getNth(int index) {
		if(index < 0 || index >= size){	//FIX added "="
			throw new IndexOutOfBoundsException();
		}
		Node toReturn = this.head;
		for(int i = 0; i <= index; i++){
			toReturn = toReturn.getNext();
		}
		return (Node) toReturn;  // 
	}



	/**
	 * An iterator for the list class
	 */
	protected class MyListIterator implements ListIterator<E>{
		Node left, right;
		int idx;
		boolean forward;
		boolean canRemoveOrSet;

		/**
		 * Constructor for the iterator
		 */
		public MyListIterator(){
			this.left = head;
			this.right = head.getNext();
			this.idx = 0;
			this.forward = true;
			this.canRemoveOrSet = false;
		}

		/**
		 * detects if there is an object in front of the iterator
		 * @return true if there is a next item in the list
		 */
		public boolean hasNext(){
			if(right == tail){
				return false;
			}
			return true;
		}

		/**
		 * moves iterator one forward in the list
		 * @return the item that was in front of the iterator prior to being called
		 */
		public E next(){
			if(right == tail){
				throw new NoSuchElementException("this is the end of the list, there is no next element");
			}
			right = right.getNext();
			left = left.getNext();
			canRemoveOrSet = true;
			forward = true;
			idx++;
			return (E) left.getElement();
		}

		//returns next node in the list
		public Node nextNode(){
			return right;
		}
		/**
		 * detects if there is an element before the iterator in the list
		 * @return true if there is an element before the iterator
		 */
		public boolean hasPrevious(){
			if(left == head){
				return false;
			}
			return true;
		}
		/**
		 * moves the iterator to one position previous to where it is.
		 * @return the item that was previous to the iterator prior to being called
		 */
		public E previous(){
			if(left == head){
				throw new NoSuchElementException("this is the beginning of the list, there is no previous element");
			}
			right = right.getPrev();
			left = left.getPrev();
			canRemoveOrSet = true;
			forward = false;
			idx--;
			return (E) right.getElement();
		}
		//return previous node in list
		public Node previousNode(){
			return left;
		}
		/**
		 * @return the item next to the iterator
		 */
		public E nextItem(){
			if(hasNext()){
				return right.getElement();
			}
			return null;
		}
		/**
		 * @return the item previous to the iterator
		 */
		public E previousItem(){
			if(hasPrevious()){
				return left.getElement();
			}
			return null;
		}

		/**
		 * @return the index of the item next to the iterator
		 */
		public int nextIndex(){
			if(!hasNext()){
				return size;
			}
			return idx;
		}
		/**
		 * @return the index of the item previous to the iterator
		 */
		public int previousIndex(){
			if(!hasPrevious()){
				return -1;
			}
			return idx-1;
		}
		/**
		 * adds an element to the list
		 * @param element- element to be added to the list
		 */
		public void add(E element){
			if(element == null){
				throw new NullPointerException("add was given null element in ListIterator");
			}
			idx++;
			Node newNode = new Node(element);
			left.setNext(newNode);
			right.setPrev(newNode);
			newNode.setNext(right);
			newNode.setPrev(left);
			left = newNode;
			canRemoveOrSet = false;
		}
		public void set(E element){
			if(element == null){
				throw new NullPointerException("cannot set element to null with iterator");
			}
			if(!canRemoveOrSet){
				throw new IllegalStateException("cannot set when canRemoveOrSet is false");
			}
			if(forward){
				left.setElement(element);
			}
			else{
				right.setElement(element);
			}
		}

		public void setLocation(Node n){
			this.right = n;
			this.left = n.prev;
		}
		/**
		 * removes the element that was last returned by next/previous
		 */
		public void remove(){
			if(!canRemoveOrSet){
				throw new IllegalStateException("cannot set when canRemoveOrSet is false");
			}
			if(forward){
				left.getPrev().setNext(right);
				right.setPrev(left.getPrev());
				left = null;
				left = right.getPrev();
				idx--;
			}
			else{
				left.setNext(right.getNext());
				right.getNext().setPrev(left);
				right = null;
				right = left.getNext();
			}
			canRemoveOrSet = false;
		}//remove

		public void reset(){
			this.left = head;
			this.right = head.getNext();
			this.idx = 0;
			this.forward = true;
			this.canRemoveOrSet = false;
		}

	}//Iterator

	@Override
	public ListIterator<E> listIterator(){
		return new MyListIterator();
	}

	@Override
	public Iterator<E> iterator(){
		return new MyListIterator();
	}

	

}//linked list