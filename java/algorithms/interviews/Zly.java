package interviews;

import org.junit.Test;

public class Zly {

	@Test
	public void testHere() {
		String a = new String("abc");
		String b = "abc";
		String c = "abc";
		System.out.println(a.intern() == b);
		System.out.println(b == c);
	}

	public int fib(int n) {
		if (n < 3) {
			return 1;
		}
		int a = 1, b = 1;
		int r = 0;
		for (int i = 3; i <= n; i++) {
			r = a + b;
			a = b;
			b = r;
		}
		return b;
	}

	@Test
	public void testFib() {
		System.out.println(fib(7));
	}

	public int fib2(int n) {
		if (n == 2) {
			return 1;
		} else if (n == 1) {
			return 1;
		} else {
			return fib2(n - 1) + fib2(n - 2);
		}
	}

	@Test
	public void testFib2() {
		System.out.println(fib2(7));
	}

	public int fib3(int n, int[] array) {
		if (n == 2) {
			return 1;
		} else if (n == 1) {
			return 1;
		} else if (array[n] != 0) {
			return array[n];
		} else {
			array[n - 1] = fib3(n - 1, array);
			array[n - 2] = fib3(n - 2, array);
			return array[n - 1] + array[n - 2];
		}
	}

	@Test
	public void testFib3() {
		System.out.println(fib3(7, new int[7 + 1]));
	}

	public int sumArr(int[] arr) {
		int tmp = arr[0], max = tmp;
		for (int i = 1; i < arr.length; i++) {
			if (tmp >= 0) {
				tmp += arr[i];
			} else {
				tmp = arr[i];
			}

			if (max < tmp) {
				max = tmp;
			}
		}

		return max;
	}

	@Test
	public void testSumArr() {
		System.out.println(sumArr(new int[] { -2, 10, 12, -20, 33 }));
	}
}
