package sort;

import java.util.Arrays;

import org.junit.Test;

public class BubbleSort {

	public void swap(int[] arr, int posA, int posB) {
		int swap = arr[posA];
		arr[posA] = arr[posB];
		arr[posB] = swap;
	}

	public void sort1(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			for (int j = 1; j < len - i; j++) {
				if (arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				}
			}
		}
	}

	@Test
	public void testSort1() {
		int[] arr = new int[] { 3, 4, 1, 2 };
		sort1(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort2(int[] arr) {
		int j, k = arr.length;
		boolean flag = true; 
		while(flag) {
			flag = false;
			for(j = 1; j < k; j++) {
				if(arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
					flag = true;
				}
			}
			k--;
		}
	}

	@Test
	public void Sort2() {
		int[] arr = new int[] { 3, 4, 1, 2 };
		sort2(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public void sort3(int[] arr) {
		int j, k;
		int flag = arr.length;
		
		while(flag > 0) {
			k = flag;
			flag = 0;
			for(j = 1; j < k; j++) { // 这里变相实现了k--
				if(arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
					flag = j;
				}
			}
		}
	}
	
	@Test
	public void testSort3 () {
		int[] arr = new int[] { 3, 4, 1, 2 };
		sort3(arr);
		System.out.println(Arrays.toString(arr));
	}
}
