package com.nafkhanzam.stima.tucil2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.nafkhanzam.utils.PairInt;

public class Polynom {

    private int[] arr;

    public Polynom() {
        this(new int[1]);
    }

    public Polynom(Polynom p) {
        this(p.getArray());
    }

    public Polynom(int[] arr) {
        this.arr = arr;
    }

    public Polynom(String eq) {
        List<PairInt> list = new ArrayList<>();
        Integer max = null;
        for (String s : eq.replace(" ", "").replace("-", "+-").substring(eq.startsWith("-") ? 1 : 0).split("\\+")) {
            PairInt p;
            if (!s.contains("x")) {
                p = new PairInt(0, Integer.valueOf(s));
            } else if (s.endsWith("x")) {
                s = s.replace("x", "");
                int value;
                if (s.isEmpty()) {
                    value = 1;
                } else if (s.equals("-")) {
                    value = -1;
                } else {
                    value = Integer.valueOf(s);
                }
                p = new PairInt(1, value);
            } else {
                String[] pt = s.split("x\\^");
                p = new PairInt(Integer.valueOf(pt[1]), pt[0].isEmpty() ? 1 : Integer.valueOf(pt[0]));
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
        for (PairInt p : list) {
            arr[p.a] += p.b;
        }
    }

    public int[] getArray() {
        return arr;
    }

    public int getLength() {
        return arr.length;
    }

    public int getMaxDegree() {
        int ans = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] != 0) {
                ans = i;
            }
        }
        return ans;
    }

    public int getCoef(int degree) {
        return arr[degree];
    }

    public Polynom makeLength(int newLength) {
        return new Polynom(Arrays.copyOf(getArray(), newLength));
    }

    public Polynom getHalfPolynom(boolean halfLeft) {
        int h = (getLength() - 1) / 2;
        int from, to;
        if (halfLeft) {
            from = 0;
            to = h + 1;
        } else {
            from = h + 1;
            to = getLength();
        }
        return new Polynom(Arrays.copyOfRange(getArray(), from, to));
    }

    public Polynom multiplyDegree(int degree) {
        int[] newArr = new int[getMaxDegree() + degree + 1];
        for (int i = newArr.length - 1; i >= degree; --i) {
            newArr[i] = arr[i - degree];
        }
        return new Polynom(newArr);
    }

    public Polynom add(Polynom p) {
        int[] newArr = new int[Math.max(getMaxDegree(), p.getMaxDegree()) + 1];
        for (int i = 0; i < newArr.length; ++i) {
            newArr[i] = (i <= getMaxDegree() ? arr[i] : 0) + (i <= p.getMaxDegree() ? p.arr[i] : 0);
        }
        return new Polynom(newArr);
    }

    public Polynom substract(Polynom p) {
        int[] newArr = new int[Math.max(getMaxDegree(), p.getMaxDegree()) + 1];
        for (int i = 0; i < newArr.length; ++i) {
            newArr[i] = (i <= getMaxDegree() ? arr[i] : 0) - (i <= p.getMaxDegree() ? p.arr[i] : 0);
        }
        return new Polynom(newArr);
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
        return sb.length() == 0 ? "0" : sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Polynom)) {
            return false;
        }
        Polynom p = (Polynom) o;
        int n = getMaxDegree();
        if (n != p.getMaxDegree()) {
            return false;
        }
        for (int i = 0; i <= n; ++i) {
            if (getCoef(i) != p.getCoef(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(arr);
    }

}