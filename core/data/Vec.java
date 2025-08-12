package data;

/**
 * Immutable-length, mutable-value mathematical vector of doubles.
 *
 * <p>
 * This class provides basic linear algebra utilities for small vectors used in
 * the app's simulations and renderers. The dimension (length) of a vector is
 * fixed at construction time and cannot change. Individual component values are
 * stored in the public array {@link #data} for performance and convenience, so
 * callers may mutate component values directly if desired.
 * </p>
 *
 * <p>
 * Unless stated otherwise, methods require both operand vectors to have the
 * same dimension and will throw {@link IllegalArgumentException} if the
 * dimensions disagree.
 * </p>
 */
public class Vec {
    private final int n;
    /**
     * Backing storage for vector components. Indexing is zero-based. Exposed as a
     * public field for performance and direct manipulation.
     */
    public double[] data;

    /**
     * Creates a 2D zero vector (x = 0, y = 0).
     */
    public Vec() {
        this.n = 2;
        this.data = new double[n];
    };

    /**
     * Creates an n-dimensional zero vector.
     *
     * @param n dimension (number of components); must be non-negative
     */
    public Vec(int n) {
        this.n = n;
        this.data = new double[n];
    }

    /**
     * Creates a 2D vector with the given components.
     *
     * @param x x-component
     * @param y y-component
     */
    public Vec(double x, double y) {
        this.n = 2;
        this.data = new double[n];
        this.data[0] = x;
        this.data[1] = y;
    }

    /**
     * Creates a 3D vector with the given components.
     *
     * @param x x-component
     * @param y y-component
     * @param z z-component
     */
    public Vec(double x, double y, double z) {
        this.n = 3;
        this.data = new double[n];
        this.data[0] = x;
        this.data[1] = y;
        this.data[2] = z;
    }

    /**
     * Copy constructor. Creates a deep copy of the given vector.
     *
     * @param original vector to copy
     */
    public Vec(Vec original) {
        this.n = original.length();

        this.data = new double[this.n];
        for (int i = 0; i < this.n; i++) {
            this.data[i] = original.data[i];
        }
    }

    /**
     * Creates a vector by copying the provided component array.
     *
     * @param data component values (copied)
     */
    public Vec(double[] data) {
        n = data.length;

        this.data = new double[this.n];
        for (int i = 0; i < this.n; i++) {
            this.data[i] = data[i];
        }
    }

    /**
     * Returns the x-component (component 0).
     *
     * <p>
     * Precondition: dimension ≥ 1.
     * </p>
     *
     * @return x-component value
     */
    public double x() {
        return this.data[0];
    }

    /**
     * Returns the y-component (component 1).
     *
     * <p>
     * Precondition: dimension ≥ 2.
     * </p>
     *
     * @return y-component value
     */
    public double y() {
        return this.data[1];
    }

    /**
     * Returns the vector dimension (number of components).
     *
     * @return dimension n
     */
    public int length() {
        return n;
    }

    /**
     * Computes the dot product with another vector.
     *
     * @param that other vector
     * @return this · that
     * @throws IllegalArgumentException if dimensions differ
     */
    public double dot(Vec that) {
        if (this.length() != that.length()) {
            throw new IllegalArgumentException("dimensions disagree");
        }
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum = sum + (this.data[i] * that.data[i]);
        }
        return sum;
    }

    /**
     * Computes the 3D cross product a × b.
     *
     * <p>
     * Note: This method ignores the receiver and could be static; it is kept as
     * an instance method for historical reasons.
     * </p>
     *
     * @param a left operand (3D)
     * @param b right operand (3D)
     * @return a × b
     * @throws IllegalArgumentException if either vector is not 3-dimensional
     */
    public Vec cross3(Vec a, Vec b) {
        if (a.length() == 3 && b.length() == 3) {
            Vec axb = new Vec(3);
            axb.data[0] = a.data[1] * b.data[2] - a.data[2] * b.data[1];
            axb.data[1] = a.data[2] * b.data[0] - a.data[0] * b.data[2];
            axb.data[2] = a.data[0] * b.data[1] - a.data[1] * b.data[0];

            return axb;
        } else {
            throw new IllegalArgumentException("Vec must be Vec3");
        }
    }

    /**
     * Returns the Euclidean norm (magnitude) of this vector.
     *
     * @return ||this||
     */
    public double mag() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Computes the Euclidean distance between this vector and another vector.
     *
     * @param that other vector
     * @return ||this − that||
     * @throws IllegalArgumentException if dimensions differ
     */
    public double distanceTo(Vec that) {
        if (this.length() != that.length()) {
            throw new IllegalArgumentException("dimensions disagree");
        }
        return this.minus(that).mag();
    }

    /**
     * Component-wise addition.
     *
     * @param that other vector
     * @return this + that
     * @throws IllegalArgumentException if dimensions differ
     */
    public Vec plus(Vec that) {
        if (this.length() != that.length()) {
            throw new IllegalArgumentException("dimensions disagree");
        }
        Vec c = new Vec(this.length());
        for (int i = 0; i < n; i++) {
            c.data[i] = this.data[i] + that.data[i];
        }
        return c;
    }

    /**
     * Component-wise subtraction.
     *
     * @param that other vector
     * @return this − that
     * @throws IllegalArgumentException if dimensions differ
     */
    public Vec minus(Vec that) {
        if (this.length() != that.length()) {
            throw new IllegalArgumentException("dimensions disaggree");
        }
        Vec c = new Vec(n);
        for (int i = 0; i < n; i++) {
            c.data[i] = this.data[i] - that.data[i];
        }
        return c;
    }

    /**
     * Returns the i-th component (0-based index).
     *
     * @param i component index in [0, n)
     * @return value of component i
     * @throws ArrayIndexOutOfBoundsException if i is out of bounds
     */
    public double cart(int i) {
        return data[i];
    }

    @Deprecated
    /**
     * Scales this vector by a scalar factor.
     *
     * <p>
     * Deprecated: use {@link #scale(double)} instead.
     * </p>
     *
     * @param factor scalar factor
     * @return factor · this
     */
    public Vec times(double factor) {
        Vec c = new Vec(n);
        for (int i = 0; i < n; i++) {
            c.data[i] = factor * data[i];
        }
        return c;
    }

    /**
     * Scales this vector by a scalar factor.
     *
     * @param factor scalar factor
     * @return factor · this
     */
    public Vec scale(double factor) {
        Vec c = new Vec(n);
        for (int i = 0; i < n; i++) {
            c.data[i] = factor * data[i];
        }
        return c;
    }

    /**
     * Returns the unit vector in the same direction as this vector.
     *
     * @return this normalized to unit length
     * @throws ArithmeticException if this is the zero vector
     */
    public Vec norm() {
        if (this.mag() == 0.0) {
            throw new ArithmeticException("zero-vector has no direction");
        }
        return this.scale(1.0 / this.mag());
    }

    /**
     * Returns the additive inverse of this vector (component-wise negation).
     *
     * @return −this
     */
    public Vec negate() {
        Vec negated = new Vec(n);
        for (int i = 0; i < n; i++) {
            negated.data[i] = -1 * this.data[i];
            ;
        }
        return negated;
    }

    /**
     * Reflects a 2D vector across the specified axis.
     *
     * <p>
     * If this vector is not 2D, it is returned unchanged.
     * </p>
     *
     * @param axis 0 to reflect across the x-axis (invert y), any other value to
     *             reflect across the y-axis (invert x)
     * @return reflected vector
     */
    public Vec reflect2D(int axis) {
        if (this.n != 2) {
            return this;
        }
        Vec reflected = new Vec(n);
        if (axis == 0) {
            reflected.data[0] = this.data[0];
            reflected.data[1] = -this.data[1];
        } else {
            reflected.data[0] = -this.data[0];
            reflected.data[1] = this.data[1];
        }
        return reflected;
    }

    /**
     * Returns a human-readable representation of the vector in the form
     * (c0, c1, ..., c{n-1}).
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('(');
        for (int i = 0; i < n; i++) {
            s.append(data[i]);
            if (i < n - 1) {
                s.append(", ");
            }
        }
        s.append(')');
        return s.toString();
    }
}
