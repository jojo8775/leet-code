package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * You are asked to design a file system that allows you to create new paths and associate them with different values.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.

Implement the FileSystem class:

bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 

Example 1:

Input: 
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output: 
[null,true,1]
Explanation: 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
Example 2:

Input: 
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output: 
[null,true,true,2,false,-1]
Explanation: 
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.
 

Constraints:

2 <= path.length <= 100
1 <= value <= 109
Each path is valid and consists of lowercase English letters and '/'.
At most 104 calls in total will be made to createPath and get.
Accepted
38,883
Submissions
62,790
 * @author jojo
 * Nov 25, 2022 4:13:36 PM
 */
public class FileSystem {
	private static class FileSystem_1 {
	    File root;
	    
	    public FileSystem_1() {
	        root = new File("");
	    }
	    
	    public boolean createPath(String path, int value) {
	        String[] arr = path.split("/");
	        
	        File cur = root;
	        for(int i=0; i<arr.length; i++){
	            if(arr[i].equals("") || arr[i].equals("/")){
	                continue;
	            }
	            
	            File next = cur.children.get(arr[i]);
	            if(next == null && i == arr.length - 1){
	                cur.children.put(arr[i], new File(arr[i]));
	                cur = cur.children.get(arr[i]);
	            }
	            else if(next != null){
	                cur = next;
	            }
	            else{
	                return false;
	            }
	        }
	        
	        if(cur.val != -1){
	            return false;
	        }
	        
	        cur.val = value;
	        return true;
	    }
	    
	    public int get(String path) {
	        String[] arr = path.split("/");
	        
	        File cur = root;
	        for(String s : arr){
	            if(s.equals("") || s.equals("/")){
	                continue;
	            }
	            
	            if(!cur.children.containsKey(s)){
	                return -1;
	            }
	            
	            cur = cur.children.get(s);
	        }
	        
	        return cur.val;
	    }
	    
	    private static class File{
	        String name;
	        int val = -1;
	        Map<String, File> children = new HashMap<>();
	        
	        public File(String name){
	            this.name = name;
	        }
	    }
	}

	/**
	 * Your FileSystem object will be instantiated and called as such:
	 * FileSystem obj = new FileSystem();
	 * boolean param_1 = obj.createPath(path,value);
	 * int param_2 = obj.get(path);
	 */
}
