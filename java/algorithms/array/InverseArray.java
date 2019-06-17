package array;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Test;

public class InverseArray {
	
	public void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	/**
	 * 时间复杂度: 只有一个for循环，时间复杂度为O(n)
	 * 空间复杂度: 因为只开辟了几个临时变量，空间复杂度为O(1)
	 * 
	 * @param arr
	 */
	public void inverse(int[] arr){
		int tmp = 0;
		for(int i = 0, j = arr.length - 1; i < j; i++, j--){
			swap(arr, i, j);
		}
	}
	
	@Test
	public void testInverse(){
		int [] arr = {1, 2, 3, 4, 5};
		inverse(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public void inverse2(int[] arr){
		int tmp = 0, n = arr.length, half = n/2;
		for(int i = 0; i < half; i++){
			swap(arr, i, n - 1 - i);
		}
	}

	public void inverse3(int[] arr, int start, int end){
		for(int i = start, j = end; i < j; i++, j--){
			swap(arr, i, j);
		}
	}
	
	/**
	 * [1, 2, 3, 4, 5, 6, 7], 3
	 * [5, 6, 7, 1, 2, 3, 4]
	 * 
	 * @param arr
	 * @param n
	 * @param k
	 */
	public void rotate(int[] arr, int k){
		if(k == 0)
			return;
		int n = arr.length;
		if(k > n){
			k = k % n;
		}
		inverse3(arr, 0, n - 1 - k);
		inverse3(arr, n - k, n - 1);
		inverse3(arr, 0, n - 1);
	}
	
	@Test
	public void testRotate(){
		int[] arr= {1, 2, 3, 4, 5, 6, 7};
		rotate(arr, 3);
		System.out.println(Arrays.toString(arr));
	}

}
