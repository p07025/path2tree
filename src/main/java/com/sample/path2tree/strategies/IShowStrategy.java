package com.sample.path2tree.strategies;

import com.sample.path2tree.entity.Node;

/**
 * 木構造表示用のストラテジー
 * @author HirotakaOkuda
 *
 */
public interface IShowStrategy {
	public void show(Node<String> root);
}
