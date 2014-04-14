package com.sample.path2tree.strategies;

import java.util.Stack;

import com.sample.path2tree.entity.Component;
import com.sample.path2tree.entity.Leaf;
import com.sample.path2tree.entity.Node;

public class DepthFirstSearchStrategy implements IShowStrategy {

	
	@Override
	public void show(Node<String> root) {
		depthFirstSearchRecursive(root,true);
	}

	/**
	 * 深さ優先探索で走査
	 * @param n
	 * @param isHeader
	 */
	private void depthFirstSearchRecursive(Component<String> n, boolean isHeader) {

		if (n instanceof Node) {
			System.out.print(whitespace(n, isHeader));
			for (int i = 0; i < ((Node<String>) n).getChildren().size(); i++) {
				Component<String> c = ((Node<String>) n).getChildren().get(i);
				if (i == 0) {
					depthFirstSearchRecursive(c, true);
				} else {
					depthFirstSearchRecursive(c, false);
				}
			}
		} else if (n instanceof Leaf) {
			System.out.println(whitespace(n, isHeader));
		}

	}

	private String whitespace(Component<String> c, boolean isHeader) {

		if ((c instanceof Node) && isHeader) {
			return "+-" + c.getValue() + "-";
		} else if ((c instanceof Leaf) && isHeader) {
			return "+-" + c.getValue();
		}

		int depth = c.getDepth();
		Component<String> tmp_ = c;
		Stack<Component<String>> s = new Stack<>();
		for (int i = 0; i < depth; i++) {
			s.push(tmp_);
			tmp_ = tmp_.getParent();
		}

		String str = "";
		Stack<String> s_ = new Stack<>();
		while (!s.isEmpty()) {
			Component<String> tmp = s.pop();
			int margin = -(s_.size() == 0 ? 0 : s_.pop().length());
			if (tmp instanceof Node) {
				if ((tmp.getParent().getChildren().size() > 1)) {
					str += whitespace(tmp.getParent().getValue().length()
							+ "+--".length() + margin)
							+ "|";
					s_.push("|");
				} else {
					str += whitespace(tmp.getParent().getValue().length()
							+ "+--".length() + margin);
				}
			} else if (tmp instanceof Leaf) {
				if ((tmp.getParent().getChildren().size() > 1)
						&& (s.size() == depth)) {
					str += whitespace(tmp.getParent().getValue().length()
							+ "+--".length() + margin)
							+ "|";
					s_.push("|");
				} else {
					str += whitespace(tmp.getParent().getValue().length()
							+ "+--".length() + margin);
				}
			}
		}

		if ((c instanceof Node) && !isHeader) {
			str += "-" + c.getValue() + "-";
		} else if ((c instanceof Leaf) && !isHeader) {
			str += "|-" + c.getValue();
		}
		return str;

	}

	private static String whitespace(int number) {
		String str = "";
		for (int i = 0; i < number; i++) {
			str += " ";
		}
		return str;
	}

}
