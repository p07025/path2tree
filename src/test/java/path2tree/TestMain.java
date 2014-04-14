package path2tree;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import com.sample.path2tree.Main;


public class TestMain {

	@org.junit.Test
	public void test() throws IOException {
		List<String> text = Main.getFileByPath("./target_file.txt");
		Assert.assertNotNull("NotNull", text);
		Assert.assertEquals(Arrays.asList(new String[]{
				"/abc/xyz/a",
				"/abc/xyz/b",
				"/abc/xyz/c/10",
				"/abc/omn"
		}), text);
	}

}
