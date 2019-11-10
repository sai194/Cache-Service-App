package com.sye.cache.service;

import java.util.HashMap;

import com.sye.cache.domain.Entry;

public class InMemCache {

	private HashMap<String, Entry> hashmap;
	private Entry start, end;
	private final int LRU_SIZE = 4;
						
	private InMemCache() {
		hashmap = new HashMap<String , Entry>();
	}

	public Object getEntry(String key) {
		if (hashmap.containsKey(key)) 
		{
			Entry entry = hashmap.get(key);
			removeNode(entry);
			addAtTop(entry);
			return entry.value;
		}
		return null;
	}

	public void putEntry(String key, Object value) {
		if (hashmap.containsKey(key))
		{
			Entry entry = hashmap.get(key);
			entry.value = String.valueOf(value);
			removeNode(entry);
			addAtTop(entry);
		} else {
			Entry newnode = new Entry();
			newnode.left = null;
			newnode.right = null;
			newnode.value = String.valueOf(value);
			newnode.key = key;
			if (hashmap.size() > LRU_SIZE)
			{
				hashmap.remove(end.key);
				removeNode(end);				
				addAtTop(newnode);

			} else {
				addAtTop(newnode);
			}

			hashmap.put(key, newnode);
		}
	}
	public void addAtTop(Entry node) {
		node.right = start;
		node.left = null;
		if (start != null)
			start.left = node;
		start = node;
		if (end == null)
			end = start;
	}

	public void removeNode(Entry node) {

		if (node.left != null) {
			node.left.right = node.right;
		} else {
			start = node.right;
		}

		if (node.right != null) {
			node.right.left = node.left;
		} else {
			end = node.left;
		}
	}
	
	private static class SingletonHelper {
        private static final InMemCache INSTANCE = new InMemCache();
    }

    public static InMemCache getInstance() {
        return SingletonHelper.INSTANCE;
    }
	
}