package com.abbos.multicloudstorageengine.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum representing metadata keys for multi-cloud storage operations.
 * Each key is associated with a string identifier and an expected value type.
 *
 * @author Aliabbos Ashurov
 * @since 01/March/2025
 */
public enum MetadataKey {
    CONTENT_TYPE("content-type", String.class),
    CONTENT_LENGTH("content-length", Long.class),
    CREATION_DATE("creation-date", String.class),
    OWNER("owner", String.class),
    TAG("tag", String.class),
    TRACE_ID("trace-id", String.class);

    private static final Map<String, MetadataKey> map = new HashMap<>();

    static {
        for (MetadataKey value : values()) {
            map.put(value.key, value);
        }
    }

    private final String key;
    private final Class<?> type;

    MetadataKey(String key, Class<?> type) {
        this.key = key;
        this.type = type;
    }

    public static MetadataKey find(String key) {
        return map.get(key);
    }

    public String getKey() {
        return key;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MetadataKey{" +
                "key='" + key + '\'' +
                ", type=" + type +
                '}';
    }
}
