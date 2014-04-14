package com.sample.path2tree.entity;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

/**
 * 子要素を持つノード
 * @author HirotakaOkuda
 * @param <E>
 */
public class Node<E> extends Component<E> {
	private final List<Component<E>> children;
	
	public Node(final Node<E> parent, final E value) {
		super(parent, value);
		this.children = Lists.newArrayList();
	}
	
	public List<Component<E>> getChildren() {
		return children;
	}
	
	public boolean addChild(Component<E> child){
		return this.children.add(child);
	}
	
	/**
	 * 既にある同じ値のノードがあれば取得し、無ければ新しく作成したものを返す
	 * @param n
	 * @param value
	 * @return Node型Object
	 */
	public Node<E> createOrGetExistsNode(final Node<E> n, final E value){
		final Node<E> obj = getSameValueObject(value);
		if(Objects.isNull(obj)){
			Node<E> node = new Node<>(n,value);
			this.addChild(node);
			return node;
		}
		return obj;
	}

	private Node<E> getSameValueObject(final E value) {
		final E value_ = Objects.requireNonNull(value);
		for(Component<E> c : getChildren()){
			if(c.isSameValue(value_) && (c instanceof Node)){
				return (Node<E>) c;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Node<E> other = (Node<E>) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		return true;
	}

}
