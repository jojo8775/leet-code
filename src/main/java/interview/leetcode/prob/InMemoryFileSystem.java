package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

 

Example:

Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]

Output:
[null,[],null,null,["a"],"hello"]

Explanation:
filesystem
 

Note:

You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
 * @author jojo
 * Aug 24, 2019 1:12:53 PM
 */
public class InMemoryFileSystem {

	public class FileSystem {

		private Node root;

		public FileSystem() {
			root = new Node("root");
		}

		public List<String> ls(String path) {
			String[] dirs = path.split("/");

			List<String> result = new ArrayList<>();
			Node node = root;
			for (String dir : dirs) {
				if (dir.isEmpty()) {
					continue;
				}

				if (!node.children.containsKey(dir)) {
					return result;
				}

				node = node.children.get(dir);
			}

			if (node.isFile) {
				result.add(node.name);
				return result;
			}

			for (String key : node.children.keySet()) {
				result.add(key);
			}

			Collections.sort(result);
			return result;
		}

		public void mkdir(String path) {
			String[] dirs = path.split("/");

			Node node = root;
			for (String dir : dirs) {
				if (dir.isEmpty()) {
					continue;
				}

				if (!node.children.containsKey(dir)) {
					node.children.put(dir, new Node(dir));
				}

				node = node.children.get(dir);
			}
		}

		public void addContentToFile(String filePath, String content) {
			String[] dirs = filePath.split("/");

			Node node = root;
			for (String dir : dirs) {
				if (dir.isEmpty()) {
					continue;
				}

				if (!node.children.containsKey(dir)) {
					node.children.put(dir, new Node(dir));
				}

				node = node.children.get(dir);
			}

			node.content += content;
			node.isFile = true;
		}

		public String readContentFromFile(String filePath) {
			String[] dirs = filePath.split("/");

			Node node = root;
			for (String dir : dirs) {
				if (dir.isEmpty()) {
					continue;
				}

				if (!node.children.containsKey(dir)) {
					node.children.put(dir, new Node(dir));
				}

				node = node.children.get(dir);
			}

			return node.content;
		}

		private static class Node {
			String content = "";
			String name = "";
			Map<String, Node> children = new HashMap<>();
			boolean isFile = false;

			public Node(String name) {
				this.name = name;
			}
		}
	}
}
