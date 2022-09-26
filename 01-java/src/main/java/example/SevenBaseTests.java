package example;

public class SevenBaseTests {
    public static void main(String[] args) {
        /*
         * 7进制123转int
         * Java 中任意进制使用String表示
         * radix: 基数
         */
        String str = "-123";
        //将字符串 按照 7进制转换为int整数
        int n = Integer.parseInt(str, 7);
        System.out.println(n);
        System.out.println(parseInt(str, 7));
    }
    public static int parseInt(String str, int radix){
        int weight = 1;
        int sum = 0;
        int negative = str.charAt(0)=='-' ? 1 : 0;
        for (int i = str.length()-1; i>=negative; i--){
            char c = str.charAt(i); //c='3' '2' '1'
            int n = c - '0'; //0 ~ radix
            if (n < 0 || n >= radix){
                throw new NumberFormatException("超过范围!");
            }
            sum += n * weight;
            weight *= radix;
        }
        return negative==1 ? -sum : sum;
    }
}
