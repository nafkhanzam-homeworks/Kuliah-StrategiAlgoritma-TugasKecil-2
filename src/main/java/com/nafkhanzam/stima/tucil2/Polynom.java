package com.nafkhanzam.stima.tucil2;

public class Polynom {

    private int[] arr;

    public Polynom(int n) {
        if (n < 0) {
            throw new RuntimeException("Polynom's degree has to be non-negative!");
        }
        this.arr = new int[n + 1];
    }

    public Polynom(int[] arr) {
        this.arr = arr;
    }

    public int[] getArray() {
        return this.arr;
    }

    public int getCoef(int degree) {
        return this.arr[degree];
    }

    @Override
    public String toString() {
        if (arr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; ++i) {
            int v = arr[i];
            sb.append(" " + (v < 0 ? "-" : "+") + " " + Math.abs(v) + "x" + (i > 1 ? "^" + i : ""));
        }
        return sb.toString();
    }

}