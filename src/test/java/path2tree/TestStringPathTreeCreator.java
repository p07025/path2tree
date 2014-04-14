package path2tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.sample.path2tree.entity.Node;
import com.sample.path2tree.strategies.StringPathTreeCreator;

public class TestStringPathTreeCreator {

	@Test
	public void test() {
		StringPathTreeCreator creator = new StringPathTreeCreator();
		Node<String> root = null;
		root = creator.addPath("/abc/xyz/a", root);
		root = creator.addPath("/abc/xyz/b", root);
		root = creator.addPath("/abc/xyz/c", root);
		
		assertEquals("abc",root.getValueName());
		assertSame(1, root.getChildren().size());
		assertEquals("xyz", ((Node<String>)root.getChildren().get(0)).getValueName());
		assertSame(3, ((Node<String>)root.getChildren().get(0)).getChildren().size());
		assertEquals("a", ((Node<String>)root.getChildren().get(0)).getChildren().get(0).getValueName());
	}

}
