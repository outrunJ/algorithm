package sort;

import java.util.Arrays;

import org.junit.Test;

public class InsertSort {

	public void swap(int[] arr, int posA, int posB) {
		int swap = arr[posA];
		arr[posA] = arr[posB];
		arr[posB] = swap;
	}

	public void sort(int[] arr) {
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			int j = i - 1;
			while (j >= 0) {
				if (arr[j] < arr[i]) {
					break;
				}
				j--;
			}
			if (j != i - 1) {
				int temp = arr[i];
				int k;
				for (k = i - 1; k > j; k--) {
					arr[k + 1] = arr[k];
				}
				arr[k + 1] = temp;
			}
		}
	}

	@Test
	public void testSort() {
		int[] arr = new int[] { 2, 4, 3, 1 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort2(int[] arr) {
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			if (arr[i] < arr[i - 1]) {
				int temp = arr[i];
				int j;
				for (j = i - 1; j >= 0 && arr[j] > temp; j--) {
					arr[j + 1] = arr[j];
				}
				arr[j + 1] = temp;
			}
		}
	}

	@Test
	public void testSort2() {
		int[] arr = new int[] { 2, 4, 3, 1 };
		sort2(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort3(int[] arr) {
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	@Test
	public void testSort3() {
		int[] arr = new int[] { 2, 4, 3, 1 };
		sort3(arr);
		System.out.println(Arrays.toString(arr));
	}
}
