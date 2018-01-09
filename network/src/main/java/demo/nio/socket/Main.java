package demo.nio.socket;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        System.out.println("输入第一个数a：");

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();

        System.out.println("输入第二个数b：");
        Scanner scanner1 = new Scanner(System.in);
        int b = scanner1.nextInt();

        System.out.println(a +"+" +b +"="+sum(a,b));
    }

    private static int sum(int a,int b){
        return a+b;
    }
}
