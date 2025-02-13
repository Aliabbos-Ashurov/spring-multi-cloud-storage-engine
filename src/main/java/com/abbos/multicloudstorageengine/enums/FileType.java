package com.abbos.multicloudstorageengine.enums;

/**
 * @author Aliabbos Ashurov
 * @since 10/February/2025  17:07
 **/
public enum FileType {
    JPEG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif"),
    PDF("application/pdf"),
    TXT("text/plain"),
    HTML("text/html"),
    XML("application/xml"),
    JSON("application/json"),
    MP4("video/mp4"),
    MP3("audio/mpeg"),
    ZIP("application/zip"),
    CSV("text/csv"),
    OCTET_STREAM("application/octet-stream"); // Default fallback

    private final String contentType;

    FileType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
