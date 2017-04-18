package org.vaadin.addons.magnifier.demo.model;

public class ImageUrl {

    private final static String IMAGE_URL_FRONT = "http://rfp.laudert.de/previews/assets/";
    private final static String IMAGE_SIZE = "prev_m/";
    private final static String IMAGE_SIZE_ZOOM = "prev_l/";

    private String imageUrlEnd = null;

    public ImageUrl(String imageUrlEnd) {
        this.imageUrlEnd = imageUrlEnd;
    }

    public String getImageUrl() {
        return ImageUrl.IMAGE_URL_FRONT + ImageUrl.IMAGE_SIZE + this.imageUrlEnd;
    }

    public String getImageZoomUrl() {
        return ImageUrl.IMAGE_URL_FRONT + ImageUrl.IMAGE_SIZE_ZOOM + this.imageUrlEnd;
    }
}
