package com.nafkhanzam.stima.tucil2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.nafkhanzam.utils.Pair;

public class Polynom {

    private int[] arr;

    public Polynom(int[] arr) {
        int lastIdx = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != 0) {
                lastIdx = i;
            }
        }
        this.arr = Arrays.copyOf(arr, lastIdx + 1);
    }

    public Polynom(String eq) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        Integer max = null;
        for (String s : eq.replace(" ", "").replace("-", "+-").substring(eq.startsWith("-") ? 1 : 0).split("\\+")) {
            Pair<Integer, Integer> p;
            if (!s.contains("x")) {
                p = Pair.of(0, Integer.valueOf(s));
            } else if (s.endsWith("x")) {
                p = Pair.of(1, Integer.valueOf(s.replace("x", "")));
            } else {
                String[] pt = s.split("x\\^");
                p = Pair.of(Integer.valueOf(pt[1]), pt[0].isEmpty() ? 1 : Integer.valueOf(pt[0]));
            }
            if (max == null || max < p.a) {
                max = p.a;
            }
            list.add(p);
        }
        if (max == null) {
            throw new RuntimeException("String is not valid as a polynomial!");
        }
        arr = new int[max + 1];
        for (Pair<Integer, Integer> p : list) {
            arr[p.a] += p.b;
        }
    }

    public int[] getArray() {
        return arr;
    }

    public int getMaxDegree() {
        return arr.length - 1;
    }

    public int getCoef(int degree) {
        return arr[degree];
    }

    @Override
    public String toString() {
        if (arr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean prevZero = arr[0] == 0;
        if (!prevZero) {
            sb.append(arr[0]);
        }
        for (int i = 1; i < arr.length; ++i) {
            int v = arr[i];
            if (v == 0) {
                continue;
            }
            int num = Math.abs(v);
            if (prevZero) {
                sb.append(num == 1 ? v < 0 ? "-" : "" : v);
                prevZero = false;
            } else {
                sb.append(" " + (v < 0 ? "-" : "+") + " ");
                sb.append(num == 1 ? "" : num);
            }
            sb.append("x" + (i > 1 ? "^" + i : ""));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Polynom)) {
            return false;
        }
        Polynom polynom = (Polynom) o;
        return Arrays.equals(arr, polynom.arr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(arr);
    }

}