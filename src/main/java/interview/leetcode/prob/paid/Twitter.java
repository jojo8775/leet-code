package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Twitter {
//	private Map<Integer, User> userMap;
//	private static final AtomicLong sequence = new AtomicLong();
//
//	private static class User {
//		Tweet head;
//		Set<Integer> following = new HashSet<Integer>();
//	}
//
//	private static class Tweet {
//		int ID;
//		long timeStamp;
//		Tweet next;
//
//		public Tweet(int ID, long timeStamp) {
//			this.timeStamp = timeStamp;
//			this.ID = ID;
//		}
//	}
//
//	/** Initialize your data structure here. */
//	public Twitter() {
//		this.userMap = new HashMap<Integer, User>();
//	}
//
//	/** Compose a new tweet. */
//	public void postTweet(int userId, int tweetId) {
//		userMap.computeIfAbsent(userId, v -> new User());
//
//		Tweet tweet = new Tweet(tweetId, sequence.incrementAndGet());
//
//		User user = userMap.get(userId);
//		Tweet temp = user.head;
//		user.head = tweet;
//		user.head.next = temp;
//	}
//
//	/**
//	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item
//	 * in the news feed must be posted by users who the user followed or by the
//	 * user herself. Tweets must be ordered from most recent to least recent.
//	 */
//	public List<Integer> getNewsFeed(int userId) {
//
//		List<Integer> feed = new ArrayList<Integer>();
//		User user = userMap.get(userId);
//		if (user == null) {
//			return feed;
//		}
//
//		PriorityQueue<Tweet> tweetList = new PriorityQueue<Tweet>((u, v) -> {
//			if (u == null && v == null) {
//				return 0;
//			} else if (u == null) {
//				return 1;
//			} else if (v == null) {
//				return -1;
//			} else
//				return (int) (v.timeStamp - u.timeStamp);
//		});
//
//		tweetList.add(user.head);
//
//		for (Integer id : user.following) {
//			tweetList.add(userMap.get(id).head);
//		}
//
//		int count = 0;
//		while (count < 10 && !tweetList.isEmpty()) {
//			Tweet tweet = tweetList.poll();
//			if (tweet != null) {
//				feed.add(tweet.ID);
//				tweet = tweet.next;
//				count++;
//				if (tweet != null) {
//					tweetList.add(tweet);
//				}
//			}
//		}
//
//		return feed;
//	}
//
//	/**
//	 * Follower follows a followee. If the operation is invalid, it should be a
//	 * no-op.
//	 */
//	public void follow(int followerId, int followeeId) {
//		User user = userMap.get(followerId);
//		if (user == null) {
//			return;
//		}
//
//		user.following.add(followeeId);
//	}
//
//	/**
//	 * Follower unfollows a followee. If the operation is invalid, it should be
//	 * a no-op.
//	 */
//	public void unfollow(int followerId, int followeeId) {
//		User user = userMap.get(followerId);
//		if (user == null) {
//			return;
//		}
//
//		user.following.remove(followeeId);
//	}
	
	 
    private static int timeStamp = 0;
    
    private static class Tweet{
        Tweet next = null;
        int id;
        int createdTime;
        
        public Tweet(int id){
            this.id = id;
            this.createdTime = timeStamp++;
        }
    }
    
    private static class User{
        Tweet head = null;
        Set<Integer> follows = new HashSet<>();
        int userId;
        
        public User(int userId){
            this.userId = userId;
            follows.add(userId);
        }
        
        public void postTweet(int tid){
            Tweet tweet = new Tweet(tid);
            tweet.next = head;
            
            head = tweet;
        }
        
        public void follows(int followeeId){
            follows.add(followeeId);
        }
        
        public void unFollow(int followeeId){
            follows.remove(followeeId);
        }
    }
    
    private Map<Integer, User> userMap = new HashMap<>();
    
    public Twitter() {
        
    }
    
    public void postTweet(int userId, int tweetId) {
        if(!userMap.containsKey(userId)){
            userMap.put(userId, new User(userId));
        }
        
        userMap.get(userId).postTweet(tweetId);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feeds = new ArrayList<>();
        
        if(!userMap.containsKey(userId)){
            return feeds;
        }
        
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a,b) -> b.createdTime - a.createdTime);
        
        User user = userMap.get(userId);
        for(int id : user.follows){
            User followee = userMap.get(id);
            if(followee.head != null){
                pq.offer(followee.head);
            }
        }
        
        int count = 0;
        while(count < 10 && !pq.isEmpty()){
            Tweet top = pq.poll();
            feeds.add(top.id);
            
            if(top.next != null){
                pq.offer(top.next);
            }
            
            count++;
        }
        
        return feeds;
    }
    
    public void follow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId)){
            userMap.put(followerId, new User(followerId));
        }
        
        if(!userMap.containsKey(followeeId)){
            userMap.put(followeeId, new User(followeeId));
        }
        
        userMap.get(followerId).follows(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId)){
            userMap.put(followerId, new User(followerId));
        }
        
        userMap.get(followerId).unFollow(followeeId);
    }

	public static void main(String[] args) {
		Twitter twitter = new Twitter();
		twitter.postTweet(1, 4);
		twitter.postTweet(2, 5);
		twitter.unfollow(1, 2);
		twitter.follow(1, 2);
		print(twitter.getNewsFeed(1));
	}
	
	private static void print(List<Integer> feed){
		for(int i : feed){
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
