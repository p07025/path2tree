package com.sample.path2tree.strategies;

import java.util.List;
import java.util.Objects;
import java.util.Queue;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.sample.path2tree.entity.Component;
import com.sample.path2tree.entity.Leaf;
import com.sample.path2tree.entity.Node;

public class StringPathTreeCreator implements IStringParseStrategy {

	public Node<String> addPath(String path, Node<String> root) {
		Queue<String> q = pathToQueue(path);
		
		if(q.isEmpty()) return root;
		
		String value = q.poll();
		if(root==null && q.size()==1){
			root = new Node<String>(null, value);
		}else if(root==null && q.size()>0){
			root = new Node<String>(null, value);
		}
		if(root.isSameValue(value)){
			createTree(root,q);
		}
		return root;
	}
	
	private Queue<String> pathToQueue(String path){
		List<String> list = Lists.newArrayList(Objects.requireNonNull(path).split("/"));
		list.remove(StringUtils.EMPTY);
		return Queues.newArrayDeque(list);
	}
	
	private void createTree(Node<String> c, final Queue<String> path) {
		
		if(path.peek()!=null){
			String value = path.poll();
			if(path.isEmpty()){
				c.addChild(new Leaf<String>(c,value));
			}else{
				createTree(c.createOrGetExistsNode(c, value),path);
			}
		}
	}

}
