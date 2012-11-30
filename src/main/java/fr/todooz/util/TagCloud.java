package fr.todooz.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagCloud {
	private List<String> tags = new ArrayList<String>();

	private void add(String tag) {
		if (tag == null) {
			return;
		}
		if (tags.contains(tag) || tag.equals("")) {
			return;
		}
		tags.add(tag);
	}

	public void add(String... tags) {
		if (tags == null) {
			return;
		}

		for (String tag : tags) {
			this.add(tag);
		}

	}

	public int size() {
		return tags.size();
	}

	public boolean contains(String string) {
		return tags.contains(string);
	}

	public void top(int i) {
		List<String> shortList = new ArrayList<String>();
		if (tags.size() > i) {
			for (int count = 0; count < i; count++) {
				shortList.add(tags.get(count));
			}
			tags = shortList;
		}
	}

	public void shuffle() {
		Collections.shuffle(tags);

	}

	public List<String> getTags() {
		return tags;
	}
}
