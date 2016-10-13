package interview.leetcode.prob.paid;

/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.
 * @author jojo
 *
 */
public class SmallestRectriangleEnclosingBlackPixel {
	public int minArea(char[][] image, int x, int y) {
		if (image[x][y] != '1') {
			return 0;
		}

		findDimension(image, x, y);

		return (right - left + 1) * (down - top + 1);
	}

	int[][] pos = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
	int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE, top = Integer.MAX_VALUE, down = Integer.MIN_VALUE;

	private void findDimension(char[][] image, int x, int y) {
		if (x < 0 || x >= image.length || y < 0 || y >= image[x].length || image[x][y] != '1') {
			return;
		}

		left = Math.min(left, y);
		right = Math.max(right, y);
		top = Math.min(top, x);
		down = Math.max(down, x);

		image[x][y] = '#';

		for (int i = 0; i < 4; i++) {
			findDimension(image, x + pos[i][0], y + pos[i][1]);
		}
	}
}
