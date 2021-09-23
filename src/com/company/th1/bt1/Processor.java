package com.company.th1.bt1;

public class Processor implements Calculator{
    @Override
    public int plus(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }

    @Override
    public int divide(int a, int b) {
        return a/b;
    }

    @Override
    public int multiply(int a, int b) {
        return a*b;
    }
}

interface Calculator {
    int plus(int a, int b);

    int minus(int a, int b);

    int divide(int a, int b);

    int multiply(int a, int b);
}

