package com.sample.path2tree.entity;

import java.util.Objects;

public abstract class Component<E> {

	private final E value;
	
	/**
	 * 親ノード
	 */
	private final Node<E> parent;
	
	protected Component(final Node<E> parent, final E value){
		this.parent = parent;
		this.value = Objects.requireNonNull(value);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Component<E> other = (Component<E>) obj;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * 木の深さを計算するメソッド
	 * @return 深さ(int)
	 */
	public int getDepth(){
		// parentがnullはrootノードを表す
		if(parent==null){ 
			return 0;
		}else{
			return parent.getDepth() + 1;
		}
	}
	
	/**
	 * ノードの値が同じであるかどうかを判定する
	 * @param v
	 * @return boolean
	 */
	public boolean isSameValue(E v){
		return value.equals(Objects.requireNonNull(v));
	}
	
	public String getValueName(){
		return value.toString();
	}
	
	public E getValue() {
		return value;
	}
	
	public Node<E> getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return this.getValueName();
	}
	
	
	
}
