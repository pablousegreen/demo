package com.example.demo;

import java.util.Arrays;

public class Test {
    public static  void main(String[] args){
        int [] numbers = {1,2,3};
        String [] names = {"ali", "got"};
        int [] others = new int[3];
        others[0] = 1;
        others[1] = 2;
        //others[2] = 3;
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(others));
        System.out.println(Arrays.toString(names));
        for(String e : names){
            System.out.println(e);
        }
    }
}
