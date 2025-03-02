package com.abbos.multicloudstorageengine.context;

import java.util.EnumMap;
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

    private final String key;
    private final Class<?> type;

    MetadataKey(String key, Class<?> type) {
        this.key = key;
        this.type = type;
    }

    /**
     * Converts a map of metadata keys and values to a string-based map.
     * @param enumMap the source map with {@code MetadataKey} keys
     * @return a new map with string keys and string values
     */
    public static Map<String, String> toStringMap(Map<MetadataKey, Object> enumMap) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<MetadataKey, Object> entry : enumMap.entrySet()) {
            map.put(entry.getKey().key, entry.getValue().toString());
        }
        return map;
    }

    /**
     * Converts a string-based map to a map of metadata keys and values.
     * @param stringMap the source map with string keys
     * @return a new map with {@code MetadataKey} keys and object values
     * @throws IllegalArgumentException if a key in the string map does not match any {@code MetadataKey}
     */
    public static Map<MetadataKey, Object> fromStringMap(Map<String, String> stringMap) {
        Map<MetadataKey, Object> map = new EnumMap<>(MetadataKey.class);
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            map.put(MetadataKey.valueOf(entry.getKey()), entry.getValue());
        }
        return map;
    }

    public String getKey() {
        return key;
    }

    public Class<?> getType() {
        return type;
    }
}
