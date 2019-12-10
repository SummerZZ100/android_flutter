package cn.com.jsj.lib_base.tools;

import android.util.Log;

import java.math.BigDecimal;
import java.util.Random;


/**
 * 数学处理类.
 * <p>
 * SaMathUtils.java
 *
 * @Date 2015年6月26日上午1:22:48
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
public class SaMathUtils {
    /**
     * Log 输出标签
     */
    public static String TAG = SaMathUtils.class.getName();

    private static Random random = new Random();

    /**
     * 四舍五入.
     *
     * @param number  原数
     * @param num 保留几位小数
     * @return 四舍五入后的值
     */
    public static BigDecimal round(double number, int num) {

        double data = Math.round(number * Math.pow(10, num)) / Math.pow(10, num) ;
        return new BigDecimal(data).setScale(2, BigDecimal.ROUND_HALF_UP);

       /* return new BigDecimal(number).setScale(decimal,
                BigDecimal.ROUND_HALF_UP);*/
    }

    /**
     * 四舍五入  两位
     *
     * @param number 原数
     * @return 四舍五入后的String值
     */
    public static String round(double number) {
        int num = 2;
        double data = Math.round(number * Math.pow(10, num)) / Math.pow(10, num) ;
        return getFormattNumber(data);

        //这个存在问题
        /*BigDecimal big = new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return getFormattNumber(big.doubleValue());*/
    }

    /**
     * 描述：字节数组转换成16进制串.
     *
     * @param b      the b
     * @param length the length
     * @return the string
     */
    public static String byte2HexStr(byte[] b, int length) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < length; ++n) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else {
                hs = hs + stmp;
            }
            hs = hs + ",";
        }
        Log.d(TAG, "字节数组转换成16进制串.>>" + hs.toUpperCase());
        return hs.toUpperCase();
    }

    /**
     * 二进制转为十六进制.
     *
     * @param binary the binary
     * @return char hex
     */
    public static char binaryToHex(int binary) {
        char ch = ' ';
        switch (binary) {
            case 0:
                ch = '0';
                break;
            case 1:
                ch = '1';
                break;
            case 2:
                ch = '2';
                break;
            case 3:
                ch = '3';
                break;
            case 4:
                ch = '4';
                break;
            case 5:
                ch = '5';
                break;
            case 6:
                ch = '6';
                break;
            case 7:
                ch = '7';
                break;
            case 8:
                ch = '8';
                break;
            case 9:
                ch = '9';
                break;
            case 10:
                ch = 'a';
                break;
            case 11:
                ch = 'b';
                break;
            case 12:
                ch = 'c';
                break;
            case 13:
                ch = 'd';
                break;
            case 14:
                ch = 'e';
                break;
            case 15:
                ch = 'f';
                break;
            default:
                ch = ' ';
        }
        Log.d(TAG, "二进制转为十六进制..>>" + ch);
        return ch;
    }

    /**
     * 一维数组转为二维数组
     *
     * @param m      the m
     * @param width  the width
     * @param height the height
     * @return the int[][]
     */
    public static int[][] arrayToMatrix(int[] m, int width, int height) {
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int p = j * height + i;
                result[i][j] = m[p];
                Log.d(TAG, "一维数组转为二维数组.中...>>" + result[i][j]);
            }
        }
        Log.d(TAG, "一维数组转为二维数组.完成>>" + result.toString());
        return result;
    }

    /**
     * 二维数组转为一维数组
     *
     * @param m the m
     * @return the double[]
     */
    public static double[] matrixToArray(double[][] m) {
        int p = m.length * m[0].length;
        double[] result = new double[p];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                int q = j * m.length + i;
                result[q] = m[i][j];
                Log.d(TAG, "二维数组转为一维数组.中...>>" + result[q]);
            }
        }
        Log.d(TAG, "二维数组转为一维数组.>>" + result);
        return result;
    }

    /**
     * 描述：int数组转换为double数组.
     *
     * @param input the input
     * @return the double[]
     */
    public static double[] intToDoubleArray(int[] input) {
        int length = input.length;
        double[] output = new double[length];
        for (int i = 0; i < length; i++) {
            output[i] = Double.valueOf(String.valueOf(input[i]));
            Log.d(TAG, "int数组转换为double数组.>>" + output[i]);
        }
        Log.d(TAG, "int数组转换为double数组.>>" + output);
        return output;
    }

    /**
     * 描述：int二维数组转换为double二维数组.
     *
     * @param input the input
     * @return the double[][]
     */
    public static double[][] intToDoubleMatrix(int[][] input) {
        int height = input.length;
        int width = input[0].length;
        double[][] output = new double[height][width];
        for (int i = 0; i < height; i++) {
            // 列
            for (int j = 0; j < width; j++) {
                // 行
                output[i][j] = Double.valueOf(String.valueOf(input[i][j]));
                Log.d(TAG, "int二维数组转换为double二维数组.>>" + output[i][j]);
            }
        }
        Log.d(TAG, "int二维数组转换为double二维数组>>" + output);
        return output;
    }

    /**
     * 计算数组的平均值.
     *
     * @param pixels 数组
     * @return int 平均值
     */
    public static int average(int[] pixels) {
        float m = 0;
        for (int i = 0; i < pixels.length; ++i) {
            m += pixels[i];
        }
        m = m / pixels.length;
        Log.d(TAG, "计算数组的平均值.>>" + m);
        return (int) m;
    }

    /**
     * 计算数组的平均值.
     *
     * @param pixels 数组
     * @return int 平均值
     */
    public static int average(double[] pixels) {
        float m = 0;
        for (int i = 0; i < pixels.length; ++i) {
            m += pixels[i];
        }
        m = m / pixels.length;
        Log.d(TAG, "计算数组的平均值..>>" + m);
        return (int) m;
    }

    /**
     * 描述：点在直线上. 点A（x，y）,B(x1,y1),C(x2,y2) 点A在直线BC上吗?
     *
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static boolean pointAtSLine(double x, double y, double x1,
                                       double y1, double x2, double y2) {
        double result = (x - x1) * (y2 - y1) - (y - y1) * (x2 - x1);
        if (result == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 描述：点在线段上. 点A（x，y）,B(x1,y1),C(x2,y2) 点A在线段BC上吗?
     *
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static boolean pointAtELine(double x, double y, double x1,
                                       double y1, double x2, double y2) {
        double result = (x - x1) * (y2 - y1) - (y - y1) * (x2 - x1);
        if (result == 0) {
            if (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)
                    && y >= Math.min(y1, y2) && y <= Math.max(y1, y2)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 描述：两条直线相交. 点A（x1，y1）,B(x2,y2),C(x3,y3),D(x4,y4) 直线AB与直线CD相交吗?
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4
     * @return
     */
    public static boolean LineAtLine(double x1, double y1, double x2,
                                     double y2, double x3, double y3, double x4, double y4) {
        double k1 = (y2 - y1) / (x2 - x1);
        double k2 = (y4 - y3) / (x4 - x3);
        if (k1 == k2) {
            Log.d(TAG, "平行线");
            return false;
        } else {
            double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x3 * y4 - y3 * x4)
                    * (x1 - x2))
                    / ((y2 - y1) * (x3 - x4) - (y4 - y3) * (x1 - x2));
            double y = (x1 * y2 - y1 * x2 - x * (y2 - y1)) / (x1 - x2);
            Log.d(TAG, "直线的交点(" + x + "," + y + ")");
            return true;
        }
    }

    /**
     * 描述：线段与线段相交. 点A（x1，y1）,B(x2,y2),C(x3,y3),D(x4,y4) 线段AB与线段CD相交吗?
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4
     * @return
     */
    public static boolean eLineAtELine(double x1, double y1, double x2,
                                       double y2, double x3, double y3, double x4, double y4) {
        double k1 = (y2 - y1) / (x2 - x1);
        double k2 = (y4 - y3) / (x4 - x3);
        if (k1 == k2) {
            Log.d(TAG, "平行线");
            return false;
        } else {
            double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x3 * y4 - y3 * x4)
                    * (x1 - x2))
                    / ((y2 - y1) * (x3 - x4) - (y4 - y3) * (x1 - x2));
            double y = (x1 * y2 - y1 * x2 - x * (y2 - y1)) / (x1 - x2);
            Log.d(TAG, "直线的交点(" + x + "," + y + ")");
            if (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)
                    && y >= Math.min(y1, y2) && y <= Math.max(y1, y2)
                    && x >= Math.min(x3, x4) && x <= Math.max(x3, x4)
                    && y >= Math.min(y3, y4) && y <= Math.max(y3, y4)) {
                Log.d(TAG, "交点（" + x + "," + y + "）在线段上");
                return true;
            } else {
                Log.d(TAG, "交点（" + x + "," + y + "）不在线段上");
                return false;
            }
        }
    }

    /**
     * 描述：线段直线相交. 点A（x1，y1）,B(x2,y2),C(x3,y3),D(x4,y4) 线段AB与直线CD相交吗?
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4
     * @return
     */
    public static boolean eLineAtLine(double x1, double y1, double x2,
                                      double y2, double x3, double y3, double x4, double y4) {
        double k1 = (y2 - y1) / (x2 - x1);
        double k2 = (y4 - y3) / (x4 - x3);
        if (k1 == k2) {
            Log.d(TAG, "平行线");
            return false;
        } else {
            double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x3 * y4 - y3 * x4)
                    * (x1 - x2))
                    / ((y2 - y1) * (x3 - x4) - (y4 - y3) * (x1 - x2));
            double y = (x1 * y2 - y1 * x2 - x * (y2 - y1)) / (x1 - x2);
            Log.d(TAG, "交点(" + x + "," + y + ")");
            if (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)
                    && y >= Math.min(y1, y2) && y <= Math.max(y1, y2)) {
                Log.d(TAG, "交点（" + x + "," + y + "）在线段上");
                return true;
            } else {
                Log.d(TAG, "交点（" + x + "," + y + "）不在线段上");
                return false;
            }
        }
    }

    /**
     * 描述：点在矩形内. 矩形的边都是与坐标系平行或垂直的。 只要判断该点的横坐标和纵坐标是否夹在矩形的左右边和上下边之间。
     * 点A（x，y）,B(x1,y1),C(x2,y2) 点A在以直线BC为对角线的矩形中吗?
     *
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static boolean pointAtRect(double x, double y, double x1, double y1,
                                      double x2, double y2) {
        if (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)
                && y >= Math.min(y1, y2) && y <= Math.max(y1, y2)) {
            Log.d(TAG, "点（" + x + "," + y + "）在矩形内上");
            return true;
        } else {
            Log.d(TAG, "点（" + x + "," + y + "）不在矩形内上");
            return false;
        }
    }

    /**
     * 描述：矩形在矩形内. 只要对角线的两点都在另一个矩形中就可以了. 点A(x1,y1),B(x2,y2)，C(x1,y1),D(x2,y2)
     * 以直线AB为对角线的矩形在以直线BC为对角线的矩形中吗?
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4
     * @return
     */
    public static boolean rectAtRect(double x1, double y1, double x2,
                                     double y2, double x3, double y3, double x4, double y4) {
        if (x1 >= Math.min(x3, x4) && x1 <= Math.max(x3, x4)
                && y1 >= Math.min(y3, y4) && y1 <= Math.max(y3, y4)
                && x2 >= Math.min(x3, x4) && x2 <= Math.max(x3, x4)
                && y2 >= Math.min(y3, y4) && y2 <= Math.max(y3, y4)) {
            Log.d(TAG, "矩形在矩形内");
            return true;
        } else {
            Log.d(TAG, "矩形不在矩形内");
            return false;
        }
    }

    /**
     * 描述：圆心在矩形内 . 圆心在矩形中且圆的半径小于等于圆心到矩形四边的距离的最小值。 圆心(x,y) 半径r
     * 矩形对角点A（x1，y1），B(x2，y2)
     *
     * @param x
     * @param y
     * @param r
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static boolean circleAtRect(double x, double y, double r, double x1,
                                       double y1, double x2, double y2) {
        // 圆心在矩形内
        if (x >= Math.min(x1, x2) && x <= Math.max(x1, x2)
                && y >= Math.min(y1, y2) && y <= Math.max(y1, y2)) {
            // 圆心到4条边的距离
            double l1 = Math.abs(x - x1);
            double l2 = Math.abs(y - y2);
            double l3 = Math.abs(x - x2);
            double l4 = Math.abs(y - y2);
            if (r <= l1 && r <= l2 && r <= l3 && r <= l4) {
                Log.d(TAG, "圆在矩形内");
                return true;
            } else {
                Log.d(TAG, "圆不在矩形内");
                return false;
            }

        } else {
            Log.d(TAG, "圆不在矩形内");
            return false;
        }
    }

    /**
     * 描述：获取两点间的距离.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double getDistance(double x1, double y1, double x2, double y2) {
        double x = x1 - x2;
        double y = y1 - y2;
        return Math.sqrt(x * x + y * y);
    }

    /**
     * 矩形碰撞检测 参数为x,y,width,height
     *
     * @param x1 第一个矩形的x
     * @param y1 第一个矩形的y
     * @param w1 第一个矩形的w
     * @param h1 第一个矩形的h
     * @param x2 第二个矩形的x
     * @param y2 第二个矩形的y
     * @param w2 第二个矩形的w
     * @param h2 第二个矩形的h
     * @return 是否碰撞
     */
    public static boolean isRectCollision(float x1, float y1, float w1,
                                          float h1, float x2, float y2, float w2, float h2) {
        if (x2 > x1 && x2 > x1 + w1) {
            return false;
        } else if (x2 < x1 && x2 < x1 - w2) {
            return false;
        } else if (y2 > y1 && y2 > y1 + h1) {
            return false;
        } else if (y2 < y1 && y2 < y1 - h2) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * This method maps a number x, which is in the range [sourceStart,
     * sourceEnd], to a new range [targetStart, targetEnd]
     * <p>
     * <p>
     * sourceStart <= x <= sourceEnd <br/>
     * targetStart <= returnValue <= targetEnd
     * </p>
     *
     * @param x           The value that should be mapped
     * @param sourceStart The source range start (inclusive)
     * @param sourceEnd   The source range end (inclusive)
     * @param targetStart The target range start (inclusive)
     * @param targetEnd   The target range end (inclusive)
     * @return The corresponding value of x in the target range
     */
    public static float mapPoint(float x, float sourceStart, float sourceEnd,
                                 float targetStart, float targetEnd) {

        if (x <= sourceStart) {
            return targetStart;
        }

        if (x >= sourceEnd) {
            return targetEnd;
        }

        return (x - sourceStart) / (sourceEnd - sourceStart)
                * (targetEnd - targetStart) + targetStart;
    }

    /**
     * <b>This is the same as
     * {@link #mapPoint(float, float, float, float, float)}but without rounding
     * the integer up. Use {@link #mapPointRound(float, float, float, int, int)}
     * if you want rounded results</b>
     * <p>
     * This method maps a number x, which is in the range [sourceStart,
     * sourceEnd], to a new range [targetStart, targetEnd]
     * </p>
     * <p>
     * sourceStart <= x <= sourceEnd <br/>
     * targetStart <= returnValue <= targetEnd
     * </p>
     *
     * @param x           The value that should be mapped
     * @param sourceStart The source range start (inclusive)
     * @param sourceEnd   The source range end (inclusive)
     * @param targetStart The target range start (inclusive)
     * @param targetEnd   The target range end (inclusive)
     * @return The corresponding value of x in the target range
     */
    public static int mapPoint(float x, float sourceStart, float sourceEnd,
                               int targetStart, int targetEnd) {

        if (x <= sourceStart) {
            return targetStart;
        }

        if (x >= sourceEnd) {
            return targetEnd;
        }

        float fRes = (x - sourceStart) / (sourceEnd - sourceStart)
                * (targetEnd - targetStart) + targetStart;

        return (int) fRes;
    }

    /**
     * <b>This is the same as
     * {@link #mapPoint(float, float, float, float, float)}but rounds to
     * integer.</b>
     * <p>
     * This method maps a number x, which is in the range [sourceStart,
     * sourceEnd], to a new range [targetStart, targetEnd]
     * </p>
     * <p>
     * sourceStart <= x <= sourceEnd <br/>
     * targetStart <= returnValue <= targetEnd
     * </p>
     *
     * @param x           The value that should be mapped
     * @param sourceStart The source range start (inclusive)
     * @param sourceEnd   The source range end (inclusive)
     * @param targetStart The target range start (inclusive)
     * @param targetEnd   The target range end (inclusive)
     * @return The corresponding value of x in the target range
     */
    public static int mapPointRound(float x, float sourceStart,
                                    float sourceEnd, int targetStart, int targetEnd) {

        if (x <= sourceStart) {
            return targetStart;
        }

        if (x >= sourceEnd) {
            return targetEnd;
        }

        float fRes = (x - sourceStart) / (sourceEnd - sourceStart)
                * (targetEnd - targetStart) + targetStart;

        return (int) (fRes + 0.5f);
    }

    /**
     * Checks if a value is between up and down (inclusive up and down)
     *
     * @param x    the value to check
     * @param down the lower bound
     * @param up   the upper bound
     * @return true, if between bounds, otherwise false
     */
    public static boolean isBetween(float x, float down, float up) {
        return x >= down && x <= up;
    }

    /**
     * Get a random int
     *
     * @return a random integer
     */
    public static int randomInt() {
        return random.nextInt(Integer.MAX_VALUE - 1);
    }


    public static double add(Number d1, Number d2) {        // 进行加法运算
        BigDecimal b1 = new BigDecimal(Double.toString(d1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(d2.doubleValue()));
        return b1.add(b2).doubleValue();
    }

    public static double sub(Number d1, Number d2) {        // 进行减法运算 d1-d2
        BigDecimal b1 = new BigDecimal(Double.toString(d1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(d2.doubleValue()));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(Number d1, Number d2) {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(Double.toString(d1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(d2.doubleValue()));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(Number d1,
                             Number d2, int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(Double.toString(d1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(d2.doubleValue()));
        return b1.divide(b2, len, BigDecimal.
                ROUND_HALF_UP).doubleValue();
    }


    /**
     * 两个数相乘
     * double 类型自动格式化小数点之后的位数
     *
     * @param number1
     * @return
     */

    public static String getNumberMul(Number number1, Number number2) {
        double mul = mul(number1, number2);
        return getFormattNumber(mul);

    }

    /**
     * 两个数相加
     * double 类型自动格式化小数点之后的位数
     *
     * @param number1
     * @return
     */

    public static String getNumberAdd(Number number1, Number number2) {
        double add = add(number1, number2);
        return getFormattNumber(add);

    }

    /**
     * 两个数相减 number1-number2
     * double 类型自动格式化小数点之后的位数
     *
     * @param number1
     * @return
     */

    public static String getNumberSub(Number number1, Number number2) {
        double sub = sub(number1, number2);
        return getFormattNumber(sub);
    }

    /**
     * double 类型自动格式化小数点之后的位数
     *
     * @param number
     * @return
     */

    public static String getFormattNumber(double number) {
        BigDecimal bigDecimal = BigDecimal.valueOf(number)
                .stripTrailingZeros();
        double value = bigDecimal.doubleValue();
        if (value == 0) {
            return "0";
        } else {
            return BigDecimal.valueOf(number)
                    .stripTrailingZeros().toPlainString();
        }

    }
}
