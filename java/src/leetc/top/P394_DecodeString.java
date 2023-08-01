package leetc.top;

public class P394_DecodeString {
    private static class Info {
        public String ans;
        public int end;
        public Info(String a, int e) {
            ans = a;
            end = e;
        }
    }

    private static String timesString(int times, String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < times; i++) {
            ans.append(str);
        }
        return ans.toString();
    }

    private static Info process(char[] s, int i) {
        StringBuilder ans = new StringBuilder();
        int times = 0;
        while (i < s.length && s[i] != ']') {
            if ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <= 'Z')) {
                ans.append(s[i++]);
            } else if (s[i] >= '0' && s[i] <= '9') {
                times = times * 10 + s[i++] - '0';
            } else {
                Info next = process(s, i + 1);
                ans.append(timesString(times, next.ans));
                times = 0;
                i = next.end + 1;
            }
        }
        return new Info(ans.toString(), i);
    }

    public static String decodeString(String s) {
        char[] str = s.toCharArray();
        return process(str, 0).ans;
    }
}
