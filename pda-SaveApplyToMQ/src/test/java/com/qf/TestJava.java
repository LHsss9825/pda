package com.qf;

/**
 * @author ：lh
 * @date ：Created in 2022/3/17 11:57
 */
public class TestJava {
    public static void main(String[] args) {
        //使用循环输出等腰三角
        int num = 4;
        for(int i = 0; i<num; i++)
        {
            for(int j =0; j<num-i-1; j++)
            {
                System.out.print(" ");
            }
            for(int k=0; k<(i*2+1); k++)
            {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

}
