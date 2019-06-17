package array;

import java.util.Arrays;

import org.junit.Test;

/**
 * 升序不重复数组中，所有两个元素和为s的组合打印出来
 * 
 * @author outrun
 *
 */
public class TwoSum {

	public void twoSum01(int[] arr, int s) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] + arr[j] == s) {
					System.out.println(i + " | " + j);
					break;
				}
			}
		}
	}

	@Test
	public void testTwoSum01() {

		twoSum01(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 10);
	}

	public void twoSum02(int[] arr, int s) {
		int i = 0, j = arr.length - 1;
		while (i < j) {
			if (arr[i] + arr[j] == s) {
				System.out.println(i + " | " + j);
				i++;
				j--;
			} else if (arr[i] + arr[j] < s) {
				i++;
			} else {
				j--;
			}
		}
	}

	@Test
	public void testTwoSum02() {
		// twoSum02(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 10);
		twoSum02(new int[] { 1, 3, 4, 5, 8, 9, 11 }, 13);
	}

	/**
	 * 既然是有序数组，可以考虑二分查找
	 * 
	 * @param arr
	 * @param s
	 */
	public void twoSum03(int[] arr, int s) {
		int n = arr.length;
		int another = 0, j = 0;
		for (int i = 0; i < n - 1; i++) {
			another = s - arr[i];
			j = Arrays.binarySearch(arr, i + 1, n, another);
			if (j > i) {
				System.out.println(i + " | " + j);
			}
		}
	}

	@Test
	public void testTwoSum03() {
		twoSum03(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 10);
	}

}
