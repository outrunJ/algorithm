package sort;

import java.util.Arrays;

import org.junit.Test;

public class MergeSort {
	public void merge(int arr[], int start, int mid, int end, int[] temp) {
		int i = start, j = mid + 1;
		int m = mid, n = end;
		int k = 0;
		while (i <= m && j <= n) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}
		while (i <= m) {
			temp[k++] = arr[i++];
		}
		while (j <= m) {
			temp[k++] = arr[j++];
		}
		for (int r = 0; r < k; r++) {
			arr[start + r] = temp[r];
		}

	}

	public void recurse(int arr[], int start, int end, int[] temp) {
		if (start < end) {
			int mid = (start + end) / 2;
			recurse(arr, start, mid, temp);
			recurse(arr, mid + 1, end, temp);
			merge(arr, start, mid, end, temp);
		}
	}

	public void mergeSort(int[] arr) {
		int len = arr.length;
		int[] temp = new int[len];
		recurse(arr, 0, arr.length - 1, temp);
	}

	@Test
	public void testMergeSort() {
		int[] arr = new int[] { 3, 4, 2, 1 };
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
