package com.sample.path2tree.entity;

public class Leaf<E> extends Component<E> {

	public Leaf(final Node<E> parent,final  E value) {
		super(parent, value);
	}

}
