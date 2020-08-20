package interview.leetcode.prob;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebCrawlerMultiThreaded {
	public List<String> crawl(String startUrl, HtmlParser htmlParser) {
		int index = startUrl.indexOf('/', 7);
		String hostName = index == -1 ? startUrl : startUrl.substring(0,index);
		Set<String> visitedUrls = ConcurrentHashMap.newKeySet();
		
		Stream<String> result = crawl(hostName, startUrl, htmlParser, visitedUrls);
		
		return result.collect(Collectors.toList());
	}
	
	private Stream<String> crawl(String hostName, String startUrl, HtmlParser htmlParser, Set<String> visitedUrl){
		Stream<String> stream = htmlParser.getUrls(startUrl)
				.parallelStream()
				.filter(v -> isSameAsHost(hostName, v))
				.filter(v -> visitedUrl.add(v))
				.flatMap(v -> crawl(hostName, v, htmlParser, visitedUrl));
		
		return Stream.concat(Stream.of(startUrl), stream);
	}
	
	private boolean isSameAsHost(String hostName, String url) {
		int idx = url.indexOf('/', 7);
		String subUrl = idx == -1 ? url : url.substring(0, idx);
		
		return hostName.equals(subUrl);
	}
	
	public interface HtmlParser{
		List<String> getUrls(String url);
	}
}
