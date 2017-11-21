package com.andreas.basicApp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.andreas.basicApp.model.Message;

public class MessageServer implements Iterable<Message> {
	private Map<Integer, List<Message>> messages;
	private List<Message> selected;

	public MessageServer() {
		selected = new ArrayList<>();
		messages = new TreeMap<>();

		List<Message> list = new ArrayList<>();
		list.add(new Message("Cat", "Cute Cat is missing"));
		list.add(new Message("Dog", "Cute Dog is missing"));

		messages.put(0, list);

		list = new ArrayList<>();
		list.add(new Message("Egg", "Bring Egg"));
		list.add(new Message("Bacon", "Bring Bacon"));

		messages.put(1, list);

		list = new ArrayList<>();
		list.add(new Message("Ball", "Throw Ball"));
		list.add(new Message("Pin", "Toss Pin"));
		list.add(new Message("Racket", "Use Racket"));

		messages.put(2, list);

		list = new ArrayList<>();
		list.add(new Message("Love", "Feel it in the air tonight"));

		messages.put(3, list);

		list = new ArrayList<>();
		list.add(new Message("Dieter", "Good name"));
		list.add(new Message("Olaf", "Bad name"));

		messages.put(4, list);
	}

	public void setSelectedServers(Set<Integer> servers) {
		selected.clear();

		for (Integer id : servers) {
			if (messages.containsKey(id)) {
				selected.addAll(messages.get(id));
			}
		}
	}

	public int getMessageCount() {
		return selected.size();
	}

	public List<Message> getSelected() {
		return selected;
	}

	@Override
	public Iterator<Message> iterator() {
		return new MessageIterator(selected);
	}
}

class MessageIterator implements Iterator<Message> {

	private Iterator<Message> iterator;

	public MessageIterator(List<Message> messages) {
		iterator = messages.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Message next() {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}

		return iterator.next();
	}

}
