package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Note: This is a companion problem to the System Design problem: Design
 * TinyURL. TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL such
 * as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. There is no
 * restriction on how your encode/decode algorithm should work. You just need to
 * ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 * 
 * @author jojo Mar 25, 201711:30:32 AM
 */
public class EncodingAndDecodingTinyUrl {
    private static final String BASE_URL = "http://tinyurl.com/";
    private static final String ENCODING_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private Map<String, String> encodedUrlMap = new HashMap<String, String>();
    private Map<String, String> longUrlMap = new HashMap<String, String>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (encodedUrlMap.containsKey(longUrl)) {
            return encodedUrlMap.get(longUrl);
        }

        StringBuilder key;
        do {
            key = new StringBuilder().append(BASE_URL);
            for (int i = 0; i < 6; i++) {
                key.append(getRandom());
            }

        } while (longUrlMap.containsKey(key));

        encodedUrlMap.put(longUrl, key.toString());
        longUrlMap.put(key.toString(), longUrl);
        return encodedUrlMap.get(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return longUrlMap.get(shortUrl);
    }

    private static int getRandom() {
        return (int) (Math.random() * ENCODING_STR.length());
    }
}