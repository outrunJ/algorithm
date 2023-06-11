package base.direct;

// 只用6号8号袋，要装满，求最小袋子数
public class MinBags {
    public static int min(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag8 = (apple >> 3);
        int rest = apple - (bag8 << 3);
        while (bag8 >= 0) {
            if (rest % 6 == 0) {
                return bag8 + (rest / 6);
            } else {
                bag8--;
                rest += 8;
            }
        }
        return -1;
    }

    public static int min2(int apple) {
        if ((apple & 1) != 0) {
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 :
                    (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int apple = 1; apple < 200; apple++) {
            System.out.println(apple + ":" + min(apple));
        }
    }
}
