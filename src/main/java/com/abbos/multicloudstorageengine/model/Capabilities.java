package com.abbos.multicloudstorageengine.model;

import java.util.Objects;

/**
 * Represents the capabilities of a storage provider, indicating support for asynchronous and reactive operations.
 * This immutable class is used to define and query the operational features supported by a provider.
 *
 * @author Aliabbos Ashurov
 * @since 01/March/2025 16:26
 */
public final class Capabilities {
    private final boolean supportAsync;
    private final boolean supportReactive;

    /**
     * Constructs a Capabilities instance with the specified support flags.
     *
     * @param supportAsync    true if asynchronous operations are supported, false otherwise
     * @param supportReactive true if reactive operations are supported, false otherwise
     */
    private Capabilities(boolean supportAsync, boolean supportReactive) {
        this.supportAsync = supportAsync;
        this.supportReactive = supportReactive;
    }

    /**
     * Creates a Capabilities instance with no supported features.
     *
     * @return a new Capabilities instance with all support disabled
     */
    public static Capabilities none() {
        return new Capabilities(false, false);
    }

    /**
     * Creates a Capabilities instance with support for asynchronous operations only.
     *
     * @return a new Capabilities instance with async support enabled
     */
    public static Capabilities onAsync() {
        return new Capabilities(true, false);
    }

    /**
     * Creates a Capabilities instance with support for reactive operations only.
     *
     * @return a new Capabilities instance with reactive support enabled
     */
    public static Capabilities onReactive() {
        return new Capabilities(false, true);
    }

    /**
     * Creates a Capabilities instance with support for both asynchronous and reactive operations.
     *
     * @return a new Capabilities instance with both async and reactive support enabled
     */
    public static Capabilities both() {
        return new Capabilities(true, true);
    }

    /**
     * Checks if asynchronous operations are supported.
     *
     * @return true if async operations are supported, false otherwise
     */
    public boolean isSupportAsync() {
        return supportAsync;
    }

    /**
     * Checks if reactive operations are supported.
     *
     * @return true if reactive operations are supported, false otherwise
     */
    public boolean isSupportReactive() {
        return supportReactive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capabilities that = (Capabilities) o;
        return supportAsync == that.supportAsync && supportReactive == that.supportReactive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(supportAsync, supportReactive);
    }

    @Override
    public String toString() {
        return "Capabilities{async=" + supportAsync + ", reactive=" + supportReactive + "}";
    }
}
