package modell;

import bitub.matrix.Matrix;

public class Markise {

    private Matrix p1;
    private Matrix p2;
    private Matrix p3;

    private Matrix m1;
    private Matrix m2;

    public Markise(Matrix p1, Matrix p2, Matrix p3, Matrix m1, Matrix m2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.m1 = m1;
        this.m2 = m2;
    }

	public Matrix getP1() {
		return p1;
	}

	public Matrix getP2() {
		return p2;
	}

	public Matrix getP3() {
		return p3;
	}

	public Matrix getM1() {
		return m1;
	}

	public Matrix getM2() {
		return m2;
	}
}
