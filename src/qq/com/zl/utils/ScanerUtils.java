package qq.com.zl.utils;

import java.util.Scanner;

public class ScanerUtils {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * 字符串输入
     **/
    public static String inputString(String types) {
        System.out.print(types);
        try {
            String str = SCANNER.next();
            return str;
        } catch (Exception e) {
            System.out.println("请输入正确的字符串");
        }
        return null;

    }

    public static String inputString() {

        try {
            String str = SCANNER.next();
            return str;
        } catch (Exception e) {
            System.out.println("请输入正确的字符串");
        }
        return null;

    }

    /**
     * 整数输入
     */
    public static int inputInt(String types) {

        int num = 0;
        while (true) {
            try {
                System.out.print(types);
                String str = SCANNER.next();
                num = Integer.parseInt(str);
                break;

            } catch (Exception e) {
                System.out.println("你输入的不是整数!请重新输入");
            }

        }
        return num;
    }

    public static int inputInt() {

        int num = 0;
        while (true) {
            try {

                String str = SCANNER.next();
                num = Integer.parseInt(str);
                break;

            } catch (Exception e) {
                System.out.println("你输入的不是整数!请重新输入");
            }

        }
        return num;
    }


    /**
     * 字符输入
     */

    public static char inputChar(String types) {


        System.out.print(types);

        return SCANNER.next().charAt(0);


    }

    public static char inputChar() {


        return SCANNER.next().charAt(0);


    }

    /**
     * 接受double
     */
    public static double inputDouble(String type) {
        double num = 0;
        while (true) {
            try {
                System.out.print(type);
                String str = SCANNER.next();
                num = Double.parseDouble(str);
                break;

            } catch (Exception e) {
                System.out.println("你输入格式错误!请重新输入");
            }

        }
        return num;
    }

    public static double inputDouble() {
        double num = 0;
        while (true) {
            try {

                String str = SCANNER.next();
                num = Double.parseDouble(str);
                break;

            } catch (Exception e) {
                System.out.println("你输入格式错误!请重新输入");
            }

        }
        return num;
    }

    /**
     * 数组从大到小逆向排序
     */
    public static int[] sortArrBig(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {

                int temp;
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 数组从小到大正向排序排序
     */
    public static int[] sortArrSmall(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {

                int temp;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 打印数组
     */
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("第" + (i + 1) + "个元素 ：" + arr[i]);
        }
        System.out.println();

    }

    /**
     * 退出菜单
     */
    public static boolean exit(boolean loop) {
        loop = true;
        System.out.println("退出-->y" +
                "不退出-->n");
        System.out.print("请输入你的选择：");
        do {
            char key = inputChar("");
            if (key != 'y' && key != 'n') {
                System.out.println("输入错误！！！");
                System.out.print("请重新输入：");
                System.out.println();

            }
            if (key == 'y') {
                loop = false;
            }
            if (key == 'n') {
                return true;
            }

        } while (loop);
        return loop;
    }

}
