package com.abbos.multicloudstorageengine.context;


import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * An immutable pair of two values, supporting serialization and functional operations.
 * This class is designed for use in multi-cloud storage contexts to hold key-value metadata or similar paired data.
 *
 * @param <L> the type of the left element
 * @param <R> the type of the right element
 * @author Aliabbos Ashurov
 * @since 01/March/2025
 */
public class Pair<L, R> implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = -2139667479971038690L;

    private final L left;
    private final R right;
    private boolean lazy;

    private Pair(L left, R right, boolean lazy) {
        this.left = left;
        this.right = right;
        this.lazy = lazy;
    }

    /**
     * Creates a new pair with the specified left and right values.
     *
     * @param left  the left element, may be null
     * @param right the right element, may be null
     * @param <L>   type of the left element
     * @param <R>   type of the right element
     * @return a new {@code Pair} instance
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right, false);
    }

    /**
     * Creates a new pair with non-null left and right values.
     *
     * @param left  the left element, must not be null
     * @param right the right element, must not be null
     * @param <L>   type of the left element
     * @param <R>   type of the right element
     * @return a new {@code Pair} instance
     * @throws NullPointerException if either left or right is null
     */
    public static <L, R> Pair<L, R> ofNonNull(L left, R right) {
        Objects.requireNonNull(right, "Right value cannot be null");
        Objects.requireNonNull(left, "Left value cannot be null");
        return new Pair<>(left, right, false);
    }

    /**
     * Returns a new pair with the elements swapped.
     *
     * @return a new {@code Pair} with right as left and left as right
     */
    public Pair<R, L> swap() {
        return new Pair<>(right, left, false);
    }

    /**
     * Combines the left and right elements into a single value using the provided function.
     *
     * @param biFunc the function to combine the elements
     * @param <T>    the type of the result, must be serializable
     * @return the result of applying the function
     */
    public <T extends Serializable> T combine(BiFunction<? super L, ? super R, ? extends T> biFunc) {
        return biFunc.apply(left, right);
    }

    /**
     * Transforms the left element using the provided function.
     *
     * @param func the function to apply to the left element
     * @param <T>  the type of the new left element
     * @return a new {@code Pair} with the transformed left element
     */
    public <T> Pair<T, R> mapLeft(Function<? super L, ? extends T> func) {
        return new Pair<>(func.apply(left), right, false);
    }

    /**
     * Transforms the right element using the provided function.
     *
     * @param func the function to apply to the right element
     * @param <T>  the type of the new right element
     * @return a new {@code Pair} with the transformed right element
     */
    public <T> Pair<L, T> mapRight(Function<? super R, ? extends T> func) {
        return new Pair<>(left, func.apply(right), false);
    }

    @Override
    public String toString() {
        return "Pair{left=" + left + ", right=" + right + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Pair<?, ?> o)) return false;
        return Objects.equals(left, o.left) && Objects.equals(right, o.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public boolean isLazy() {
        return lazy;
    }
}


