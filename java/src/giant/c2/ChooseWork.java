package giant.c2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

// 按能力选收入最高的工作
public class ChooseWork {
    public static class Job {
        public int money;
        public int hard;
        public Job(int m, int h) {
            money = m;
            hard = h;
        }
    }

    private static class Comp implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }

    public static int[] choose(Job[] job, int[] ability) {
        Arrays.sort(job, new Comp());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(job[0].hard, job[0].money);
        Job pre = job[0];
        for (int i = 1; i < job.length; i++) {
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                pre = job[i];
                map.put(pre.hard, pre.money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }
}
