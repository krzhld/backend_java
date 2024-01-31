package edu.project4.pojo;

public enum ImageFormat {
    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");
    private final String format;

    ImageFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
