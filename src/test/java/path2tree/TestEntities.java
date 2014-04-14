package path2tree;

import org.junit.Assert;
import org.junit.Test;

import com.sample.path2tree.entity.Leaf;
import com.sample.path2tree.entity.Node;

public class TestEntities {

	@Test
	public void testDepth() {
		Node<String> root = new Node<String>(null,"hoge");
		Node<String> child1 = new Node<String>(root,"hoge1");
		Node<String> child2 = new Node<String>(root,"hoge2");
		
		Leaf<String> child1_child1 = new Leaf<String>(child1,"hoge1-1");
		child1.addChild(child1_child1);
		
		root.addChild(child1);
		root.addChild(child2);
		
		Assert.assertSame(2, child1_child1.getDepth());
		Assert.assertSame(1, child2.getDepth());
	}

}
