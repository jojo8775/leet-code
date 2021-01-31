package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FileSystem {

	private FileExplorer fileExplorer;
	
	public List<String> search(String pattern){
		String curPath = getCurPath();
		List<Entry> entries = fileExplorer.getEntries(curPath);
		
		Stack<List<Entry>> stack = new Stack<>();
		stack.push(entries);
		
		List<String> result = new ArrayList<>();
		
		while(!stack.isEmpty()) {
			for(Entry entry : stack.pop()) {
				if(entry.isAFolder()) {
					stack.push(fileExplorer.getEntries(entry.getPath() + "/" + entry.getName()));
				}
				else if(entry.getName().startsWith(pattern)) {
					result.add(entry.getName());
				}
			}
		}
		
		return result;
	}
	
	
	// consider there is a function which returns the current path. 
	public String getCurPath() {
		return "";
	}
	
	public interface FileExplorer{
		List<Entry> getEntries(String path);
	}
	
	public interface Entry{
		boolean isAFolder();
		String getPath();
		String getName();
	}
}
