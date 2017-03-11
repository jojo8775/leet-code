package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    public int findMinimumJump(String beg, String end, Set<String> dict) {
        dict.add(end);

        Queue<WordNode> queue = new LinkedList<WordNode>();
        queue.offer(new WordNode(beg, 0));

        while (!queue.isEmpty()) {
            WordNode top = queue.poll();
            if (top.val.equals(end)) {
                print(top);
                return top.step;
            }

            char[] cArr = top.val.toCharArray();
            for (int i = 0; i < cArr.length; i++) {
                char temp = cArr[i];
                for (int j = 'a'; j <= 'z'; j++) {
                    if (cArr[i] != (char) j) {
                        cArr[i] = (char) j;

                        String s = String.valueOf(cArr);

                        if (dict.contains(s)) {
                            WordNode node = new WordNode(s, top.step + 1);
                            node.prev = top;
                            queue.offer(node);
                            dict.remove(s);
                        }

                        cArr[i] = temp;
                    }
                }
            }
        }

        return 0;
    }

    private void print(WordNode node) {
        String str = "";
        while (node != null) {
            str = node.val + "," + str;
            node = node.prev;
        }

        System.out.println(str);
    }

    public List<List<String>> findAllPaths(String beg, String end, Set<String> dict) {
        dict.add(end);

        Queue<WordNode> queue = new LinkedList<WordNode>();
        queue.offer(new WordNode(beg, 0));

        Set<String> visited = new HashSet<String>(), unVisited = new HashSet<String>(dict);

        int minCount = Integer.MAX_VALUE, prevStep = 0;

        List<List<String>> paths = new ArrayList<List<String>>();

        while (!queue.isEmpty()) {
            WordNode top = queue.poll();

            if (top.step > minCount) {
                continue;
            }

            if (top.val.equals(end)) {
                if (minCount > top.step) {
                    minCount = top.step;
                }

                List<String> path = new ArrayList<String>();
                while (top != null) {
                    path.add(0, top.val);
                    top = top.prev;
                }

                paths.add(path);
            } else {
                if (prevStep < top.step) {
                    unVisited.removeAll(visited);
                }

                prevStep = top.step;

                char[] cArr = top.val.toCharArray();

                for (int i = 0; i < cArr.length; i++) {
                    char temp = cArr[i];
                    for (int j = 'a'; j <= 'z'; j++) {
                        if (cArr[i] != (char) j) {
                            cArr[i] = (char) j;

                            String s = String.valueOf(cArr);
                            if (dict.contains(s)) {
                                visited.add(s);
                                WordNode node = new WordNode(s, top.step + 1);
                                node.prev = top;
                                queue.add(node);
                            }

                            cArr[i] = temp;
                        }
                    }
                }
            }
        }

        return paths;
    }

    public static void main(String[] args) {
        System.out.println(new WordLadder().findMinimumJump("hit", "cog",
                new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));

        System.out.println("======================================");

        List<List<String>> result = new WordLadder().findAllPaths("hit", "cog",
                new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));

        for (List<String> p : result) {
            StringBuilder sb = new StringBuilder();
            for (String s : p) {
                sb.append(s).append(",");
            }

            System.out.println(sb.toString());
        }
    }

    private static class WordNode {
        String val;
        int step;
        WordNode prev;

        WordNode(String val, int step) {
            this.val = val;
            this.step = step;
        }
    }
}
