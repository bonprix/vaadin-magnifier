package org.vaadin.addons.magnifier.client;

import com.vaadin.shared.ui.JavaScriptComponentState;

public class MagnifierState extends JavaScriptComponentState {

	private static final long serialVersionUID = -2374185162037353601L;
	private float zoomFactor = 1.2f; 
	private String imageUrl = new String();
	private String zoomImageUrl = new String();	
    private String syncedMagnifierId = new String();
    
	public float getZoomFactor() {
		return zoomFactor;
	}

	public void setZoomFactor(float zoomFactor) {
		this.zoomFactor = zoomFactor;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getZoomImageUrl() {
		return zoomImageUrl;
	}

	public void setZoomImageUrl(String zoomImageUrl) {
		this.zoomImageUrl = zoomImageUrl;
	}

	public String getSyncedMagnifierId() {
		return syncedMagnifierId;
	}

	public void setSyncedMagnifierId(String syncedMagnifierId) {
		this.syncedMagnifierId = syncedMagnifierId;
	}

}
