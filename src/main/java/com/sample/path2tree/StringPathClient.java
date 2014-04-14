package com.sample.path2tree;

import com.sample.path2tree.entity.Node;
import com.sample.path2tree.strategies.DepthFirstSearchStrategy;
import com.sample.path2tree.strategies.IShowStrategy;
import com.sample.path2tree.strategies.IStringParseStrategy;
import com.sample.path2tree.strategies.StringPathTreeCreator;

/**
 * Stringのパスから木構造を作成するクラス
 * @author HirotakaOkuda
 *
 */
public class StringPathClient {
	
	private Node<String> root;
	private final IShowStrategy showStrategy;
	private final IStringParseStrategy parseStrategy;
	public StringPathClient(){
		this.showStrategy = new DepthFirstSearchStrategy();
		this.parseStrategy = new StringPathTreeCreator();
	}

	/**
	 * パスを入力する
	 * @param string /hoge/hoge2/hoge3 の様な形式で
	 */
	public void addPath(String path){
		this.root = this.parseStrategy.addPath(path, root);
	}
	
	/**
	 * 木構造をコンソールに表示する
	 */
	public void show(){
		showStrategy.show(root);
	}
	
	
}
