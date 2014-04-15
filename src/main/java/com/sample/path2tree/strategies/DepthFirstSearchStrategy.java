package com.sample.path2tree.strategies;

import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.sample.path2tree.entity.Component;
import com.sample.path2tree.entity.Leaf;
import com.sample.path2tree.entity.Node;

public class DepthFirstSearchStrategy implements IShowStrategy {

	/**
	 * 木構造の枝の部分を洗わす文字
	 */
	private static final String TREE_EDGE = "|";
	
	@Override
	public void show(Node<String> root) {
		depthFirstSearchRecursive(root,true);
	}

	/**
	 * 深さ優先探索で走査
	 * @param n
	 * @param isHeader　先頭行であるかどうか
	 */
	private void depthFirstSearchRecursive(Component<String> n, boolean isHeader) {

		if (n instanceof Node) {
			System.out.print(createDecoratedNodeString(n, isHeader));
			for (int i = 0; i < ((Node<String>) n).getChildren().size(); i++) {
				Component<String> c = ((Node<String>) n).getChildren().get(i);
				if (i == 0) {
					depthFirstSearchRecursive(c, true);
				} else {
					depthFirstSearchRecursive(c, false);
				}
			}
		} else if (n instanceof Leaf) {
			System.out.println(createDecoratedNodeString(n, isHeader));
		}

	}
	
	/**
	 * 木構造表示用の空白文字などの挿入を行うメソッド
	 * @param c
	 * @param isHeader 先頭行であるかどうか
	 * @return
	 */
	private String createDecoratedNodeString(Component<String> c, boolean isHeader) {

		if ((c instanceof Node) && isHeader) {
			return createHeaderNodeString((Node<String>) c);
		} else if ((c instanceof Leaf) && isHeader) {
			return createHeaderLeafString((Leaf<String>) c);
		}

		StringBuilder builder = new StringBuilder();
		Stack<String> treeEdgeMarginStack = new Stack<>(); // 木構造の枝表示した場合の空白を除く計算に必要なスタック
		Stack<Component<String>> s = this.getNodeRouteOfRoot(c);
		int depth = c.getDepth();
		while (!s.isEmpty()) {
			Component<String> component = s.pop();
			builder.append(whitespace(calcWhiteSpaceSize(component,calcMarginForTreeEdge(treeEdgeMarginStack))));
			
			//子が複数の場合に枝を表示
			if (isNeedTeeEdge(component,s.size(),depth)) { 
				builder.append(TREE_EDGE);
				treeEdgeMarginStack.push(TREE_EDGE); 
			}
		}

		if ((c instanceof Node) && !isHeader) {
			builder.append(createNoneHeaderNodeString((Node<String>) c));
		} else if ((c instanceof Leaf) && !isHeader) {
			builder.append(createNoneHeaderLeafString((Leaf<String>) c));
		}
		return builder.toString();
	}
	
	private String createHeaderLeafString(Leaf<String> l){
		return "+-" + l.getValue();
	}
	
	private String createHeaderNodeString(Node<String> n){
		return "+"+createNoneHeaderNodeString(n);
	}
	
	private String createNoneHeaderLeafString(Leaf<String> l){
		return "|-" + l.getValue();
	}
	
	private String createNoneHeaderNodeString(Node<String> n){
		return "-" + n.getValue() + "-";
	}
	
	/**
	 * 枝を複数表示させた際の空白業の調整分を計算する
	 * @param treeEdgeMarginStack
	 * @return
	 */
	private int calcMarginForTreeEdge(Stack<String> treeEdgeMarginStack){
		return (treeEdgeMarginStack.size() == 0 ? 0 : treeEdgeMarginStack.pop().length());
	}
	
	/**
	 * 枝を複数表示する必要があるかどうか
	 * @param component
	 * @param stackSize
	 * @param depth
	 * @return
	 */
	private boolean isNeedTeeEdge(Component<String> component, int stackSize, int depth){
		int parentChildrenSize = component.getParent().getChildren().size();
		if ((component instanceof Node) && (parentChildrenSize > 1)
				|| ((component instanceof Leaf) && (parentChildrenSize > 1)  && (stackSize == depth)) ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 木構造を描く際の空白文字数を計算する
	 * @param n
	 * @param margin
	 * @return
	 */
	private int calcWhiteSpaceSize(Component<String> n, int margin){
		return createHeaderNodeString(n.getParent()).length() - margin;
	}
	
	/**
	 * 根までのNodeをStackに入れる
	 * @param c
	 * @return
	 */
	private Stack<Component<String>> getNodeRouteOfRoot(final Component<String> c){
		final int depth = c.getDepth();
		Component<String> tmp_ = c;
		Stack<Component<String>> s = new Stack<>();
		for (int i = 0; i < depth; i++) {
			s.push(tmp_);
			tmp_ = tmp_.getParent();
		}
		return s;
	}
	
	private String whitespace(int number) {
		return StringUtils.repeat(" ", number);
	}

}
