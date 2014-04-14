package path2tree;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sample.path2tree.entity.Leaf;
import com.sample.path2tree.entity.Node;
import com.sample.path2tree.strategies.DepthFirstSearchStrategy;

public class TestShowStrategy {
    private PrintStream _saved;
    private ByteArrayOutputStream _actual;
    private ByteArrayOutputStream _expected;
    private PrintStream _out;
    
    @Before
    public void setUp() {
        _saved = System.out;
        _actual = new ByteArrayOutputStream();
        _expected = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new BufferedOutputStream(_actual)));
        _out = new PrintStream(new BufferedOutputStream(_expected));
    }

    @After
    public void tearDown() {
        System.setOut(_saved);
    }

    
	@Test
	public void test() {
		//actual
		_out.println("+-abc-+-xyz-+-a");
        _out.println("      |     |-b");
        _out.println("      |     |-c-+-10");
        _out.println("      |-omn");
        _out.flush();

		// expecte
        Node<String> root = new Node<String>(null,"abc");
        Node<String> node1 = new Node<String>(root,"xyz");
        root.addChild(node1);
        node1.addChild(new Leaf<String>(node1,"a"));
        node1.addChild(new Leaf<String>(node1,"b"));
        
        Node<String> node2 = new Node<String>(node1,"c");
        node1.addChild(node2);
        node2.addChild(new Leaf<String>(node2,"10"));
        
        root.addChild(new Leaf<String>(root,"omn"));
        
		DepthFirstSearchStrategy strategy = new DepthFirstSearchStrategy();
		strategy.show(root);
		System.out.flush();
		
		assertEquals(_expected.toString(), _actual.toString());
	}

}
