package com.sample.path2tree;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Main {
	private static final String FILE_PATH = "./target_file.txt";
	
	public static void main(String[] args) throws IOException {
		StringPathClient client = new StringPathClient();
		for(String path : getFileByPath(FILE_PATH)){
			client.addPath(path);
		}
		client.show();
	}
	
	/**
	 * ファイルの各行を文字列のリストに変換する
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static List<String> getFileByPath(String filePath) throws IOException{
		return FileUtils.readLines(FileUtils.getFile(filePath));
	}
}
