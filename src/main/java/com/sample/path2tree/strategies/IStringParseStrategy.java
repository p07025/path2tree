package com.sample.path2tree.strategies;

import com.sample.path2tree.entity.Node;

public interface IStringParseStrategy {
	public Node<String> addPath(String path, Node<String> root);
}
