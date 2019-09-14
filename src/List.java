/*
 * Class List with “find,” “size,” “contains,” “remove,” “add,”, “get,” “getNext,”, “reset,” “toString” methods
 */

public class List<E> {

	transient int size = 0;
	transient Node<E> first;
	transient Node<E> last;
	protected transient int modCount = 0;
	boolean found;

	public List() {
	}

	//Find if the patient object exists in list
	public void find(Object o) {
		found = false;
		while (first != null) {
			if (first.getItem().equals(o))  {
				found = true;
				return; 
			}
			else {
				last = first;
				first = first.getNext(); 
			}
		}
	}

	//Returns the size of list
	public int size() {
		return size;
	}

	//Checks if the patient is present in the list
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	//Removes the patient object from the list
	public boolean remove(Object o) {
		if (o == null) {
			for (Node<E> x = first; x != null; x = x.next) {
				if (x.item == null) {
					unlink(x);
					return true;
				}
			}
		} else {
			for (Node<E> x = first; x != null; x = x.next) {
				if (o.equals(x.item)) {
					unlink(x);
					return true;
				}
			}
		}
		return false;
	}

	//Adds the patient object to the list
	public boolean add(E e) {
		final Node<E> l = last;
		final Node<E> newNode = new Node<>(l, e, null);
		last = newNode;
		if (l == null)
			first = newNode;
		else
			l.next = newNode;
		size++;
		modCount++;
		return true;
	}

	//Gets the patient objects based on the index from the list
	public E get(int index) {
		if (!(index >= 0 && index < size))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		return node(index).item;
	}

	//Prints the string form of the patient object
	public String toString() {
		Node<E> currNode = first; 
		String listString = "List:\n"; 
		while (currNode != null) {
			listString = listString + " " + currNode.getItem() + "\n";
			currNode = currNode.getNext(); 
		}
		return listString; 
	}

	//Gets the next element node from the linked list
	public E getNext() {
		E next = first.getItem();
		first = first.getNext(); 
		return next;
	}

	//Resets the list
	public void reset() {
		first = null;
	}

	//Returns the index of each of each patient object from the list
	public int indexOf(Object o) {
		int index = 0;
		if (o == null) {
			for (Node<E> x = first; x != null; x = x.next) {
				if (x.item == null)
					return index;
				index++;
			}
		} else {
			for (Node<E> x = first; x != null; x = x.next) {
				if (o.equals(x.item))
					return index;
				index++;
			}
		}
		return -1;
	}

	Node<E> node(int index) {
		if (index < (size >> 1)) {
			Node<E> x = first;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {
			Node<E> x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}
	private String outOfBoundsMsg(int index) {
		return "Index: "+index+", Size: "+size;
	}

	//unlinking the node of the linked list to remove from the list
	E unlink(Node<E> x) {
		final E element = x.item;
		final Node<E> next = x.next;
		final Node<E> prev = x.prev;

		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			x.prev = null;
		}

		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
			x.next = null;
		}

		x.item = null;
		size--;
		modCount++;
		return element;
	}

	//class node for the linked list
	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
		public E getItem() {
			return item;
		}
		public Node<E> getNext() {
			return next;
		}
	}
}



