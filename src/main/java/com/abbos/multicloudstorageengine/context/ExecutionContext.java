package com.abbos.multicloudstorageengine.context;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Defines an immutable context for storage operations, encapsulating timeout, retry limits,
 * priority, and metadata key-value pairs. This class is designed to provide a thread-safe,
 * immutable configuration for storage-related tasks in a multi-cloud environment.
 *
 * <p>Instances of this class can be created using the {@link Builder} class or the static factory
 * methods {@link #of(Duration, int, Pair)}, {@link #of(Duration, int, List)}, and {@link #ofDefaults()}.
 *
 * @author Aliabbos Ashurov
 * @since 01/March/2025
 */
public final class ExecutionContext {
    private final Duration timeout;
    private final int maxRetries;
    private final Priority priority;
    private final List<Pair<MetadataKey, Object>> metadata;

    /**
     * Priority levels for storage operations.
     */
    public enum Priority {
        LOW, HIGH, MEDIUM
    }

    private ExecutionContext(Builder builder) {
        this.timeout = Objects.requireNonNull(builder.timeout, "Timeout cannot be null");
        this.maxRetries = builder.maxRetries;
        this.priority = builder.priority != null ? builder.priority : Priority.MEDIUM; // Default
        this.metadata = builder.metadata != null
                ? Collections.unmodifiableList(builder.metadata)
                : Collections.emptyList();
        validate();
    }

    /**
     * Validates the configuration parameters to ensure they meet the required constraints.
     *
     * @throws IllegalArgumentException if maxRetries is negative, timeout is zero or negative,
     *                                  or metadata contains invalid key-value type pairings
     */
    private void validate() {
        if (maxRetries < 0) {
            throw new IllegalArgumentException("Max retries cannot be negative");
        }
        if (timeout.isNegative() || timeout.isZero()) {
            throw new IllegalArgumentException("Timeout must be positive");
        }
        for (Pair<MetadataKey, Object> pair : metadata) {
            if (!pair.getLeft().getType().isInstance(pair.getRight())) {
                throw new IllegalArgumentException(
                        "Invalid value type for " + pair.getLeft() + ": expected " + pair.getLeft().getType()
                );
            }
        }
    }

    /**
     * Creates a new {@code ExecutionContext} with the specified timeout, max retries, and a single metadata pair.
     *
     * @param timeout    the maximum duration for the operation
     * @param maxRetries the maximum number of retry attempts
     * @param pair       a single metadata key-value pair
     * @return a new {@code ExecutionContext} instance
     * @throws NullPointerException     if timeout or pair is null
     * @throws IllegalArgumentException if validation fails
     */
    public static ExecutionContext of(Duration timeout, int maxRetries, Pair<MetadataKey, Object> pair) {
        return of(timeout, maxRetries, Collections.singletonList(pair));
    }

    /**
     * Creates a new {@code ExecutionContext} with the specified timeout, max retries, and metadata list.
     *
     * @param timeout    the maximum duration for the operation
     * @param maxRetries the maximum number of retry attempts
     * @param metadata   a list of metadata key-value pairs
     * @return a new {@code ExecutionContext} instance
     * @throws NullPointerException     if timeout is null
     * @throws IllegalArgumentException if validation fails
     */
    public static ExecutionContext of(Duration timeout, int maxRetries, List<Pair<MetadataKey, Object>> metadata) {
        return new Builder()
                .timeout(timeout)
                .maxRetries(maxRetries)
                .metadata(metadata)
                .build();
    }

    /**
     * Creates a new {@code ExecutionContext} with default values: 5-second timeout,
     * 3 retries, and medium priority, with no metadata.
     *
     * @return a new {@code ExecutionContext} instance with default settings
     */
    public static ExecutionContext ofDefaults() {
        return new Builder()
                .timeout(Duration.ofSeconds(5))
                .maxRetries(3)
                .priority(Priority.MEDIUM)
                .build();
    }

    /**
     * Builder class for constructing {@code ExecutionContext} instances in a fluent manner.
     */
    public static class Builder {
        private Duration timeout;
        private int maxRetries;
        private Priority priority;
        protected List<Pair<MetadataKey, Object>> metadata;

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder maxRetries(final int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder metadata(List<Pair<MetadataKey, Object>> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder addPair(MetadataKey key, Object value) {
            if (this.metadata == null) {
                this.metadata = new ArrayList<>();
            }
            this.metadata.add(Pair.of(key, value));
            return this;
        }

        public ExecutionContext build() {
            return new ExecutionContext(this);
        }
    }

    public Duration getTimeout() {
        return timeout;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public Priority getPriority() {
        return priority;
    }

    public List<Pair<MetadataKey, Object>> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "ExecutionContext{" +
                "timeout=" + timeout +
                ", maxRetries=" + maxRetries +
                ", priority=" + priority +
                ", metadata=" + metadata +
                '}';
    }
}