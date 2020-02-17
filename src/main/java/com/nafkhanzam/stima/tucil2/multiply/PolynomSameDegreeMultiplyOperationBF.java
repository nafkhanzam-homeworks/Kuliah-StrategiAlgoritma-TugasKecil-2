package com.nafkhanzam.stima.tucil2.multiply;

import com.nafkhanzam.stima.tucil2.Polynom;

public class PolynomSameDegreeMultiplyOperationBF extends BasePolynomSameDegreeMultiplyOperation {

    public PolynomSameDegreeMultiplyOperationBF(Polynom a, Polynom b) {
        super(a, b);
    }

    @Override
    public Polynom multiply() {
        int degree = a.getMaxDegree() + b.getMaxDegree();
        int[] res = new int[degree + 1];
        for (int i = 0; i <= a.getMaxDegree(); ++i) {
            for (int j = 0; j <= b.getMaxDegree(); ++j) {
                res[i + j] += a.getCoef(i) * b.getCoef(j);
            }
        }
        return new Polynom(res);
    }

}