package hash_tables;

public class HashTable<Key, Value> {
	private int M = 32;
	private Node[] st = new Node[M];

	static class Node {
		private Object key, value;
		private Node next;

		Node(Object key, Object value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7ffffffff) % M;
	}

	public Value get(Key key) {
		int i = hash(key);

		for(Node x = st[i]; x!=null; x = x.next) {
			if((x.key.equals(key)) {
				return ((Value x.value);
			}
		}
		return null;
	}

	public void put (Key key, Value value) {
		int i = hash(key);

		for(Node x = st[i]; x!=null; x = x.next) {
			if(x.key.equals(key)) {
				x = x.value;
			}
		}
		st[i] = new Node(key, value[i]);
	}
}


