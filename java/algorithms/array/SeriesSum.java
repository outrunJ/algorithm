package array;

import org.junit.Test;

/**
 * 给正整数s, 写出连续正整数和为s的所有序列
 * 
 * @author outrun
 *
 */
public class SeriesSum {

	public void seriesSum01(int s) {
		int half = s + 1 / 2;
		int start = 1, end = 2, sum = 0;
		while (start < half) {
			sum = (start + end) * (end - start + 1) / 2;
			if (sum == s) {
				System.out.println(start + " | " + end);
				start++;
				end++;
			} else if (sum < s) {
				end++;
			} else {
				start++;
			}
		}
	}

	@Test
	public void testSeriesSum01() {
		seriesSum01(21);
	}

	public void seriesSum02(int s) {
		int half = s + 1 / 2;
		int start = 1, end = 2, sum = start + end;
		while(start < half){
			if(sum == s){
				System.out.println(start + " | " + end);
				sum -= start;
				start++;
				end++;
				sum +=end;
			}else if(sum < s){
				end++;
				sum += end;
			}else{
				sum -= start;
				start++;
			}
		}
	}
	
	@Test
	public void testSeries02(){
		seriesSum02(21);
	}
}
