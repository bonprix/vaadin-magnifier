package org.vaadin.addons.magnifier;

import java.util.concurrent.atomic.AtomicLong;

import org.vaadin.addons.magnifier.client.MagnifierServerRpc;
import org.vaadin.addons.magnifier.client.MagnifierState;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

// This is the server-side UI component that provides public API for Magnifier
@JavaScript({ "lib/jquery-3.1.1.min.js", "Magnifier.js" })
public class Magnifier extends AbstractJavaScriptComponent {

	private static final long serialVersionUID = 571299685547099480L;

    private final int selectedIndex = -1;
    
    private static AtomicLong INSTANCE_COUNT = new AtomicLong(0);
    private final long instanceId;

    @SuppressWarnings("serial")
    private final MagnifierServerRpc rpc = new MagnifierServerRpc() {

        @Override
        public void click(final String url, final boolean initialSelection) {
            final int selection = getState(false).getImageUrl().indexOf(url);
            
            // Magnifier.this.selectedIndex = selection;
            // if (!initialSelection) {
            // fireEvent(new ImageSelectionEvent(Magnifier.this, url, selection));
            // }
        }
    };

    /**
     * Creates a new magnifier widget with the given image URLs.
     */
    public Magnifier() {
        this.instanceId = INSTANCE_COUNT.getAndIncrement();
        setId("magnifier-" + this.instanceId);
        registerRpc(this.rpc);
    }

    /**
     * Sets the image URL. Causes a reinitialization.
     *
     * @param url the URL
     */
     public void setImageUrl(final String url) {
        getState().setImageUrl(url);
     }
    
    /**
     * Sets the HighRes ZoomImage URL if available. 
     * So it is possible to load just a smaller image at the beginning and
     * if the magnifier is used, then the HighRes Image will be loaded
     *
     * @param zoomImageUrl the URL
     */
    public void setZoomImageUrl(final String zoomImageUrl) {
        getState().setZoomImageUrl(zoomImageUrl);
    }
    
    /** 
     * When you have more the one Maginifier.
     * 
     * @param magnifier
     */
    public void syncWith(final Magnifier magnifier) {
        getState().setSyncedMagnifierId("magnifier-" + magnifier.instanceId);
    }

    /**
     * Sets the zoom factor of the magnifier. A zoom factor of 1 means the image in the magnifier has native width 
     * and if the image shown in the panel is smaller than the native image due to resizing the window, 1 is producing a zoom effect.
     * 
     * @param zoomFactor The zoom factor of this magnifier, has to be greater than 0.
     */
    public void setZoomFactor(final float zoomFactor) {
        if (zoomFactor <= 0) {
            return;
        }
        getState().setZoomFactor(zoomFactor);
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override
    protected MagnifierState getState() {
        return (MagnifierState) super.getState();
    }

    @Override
    protected MagnifierState getState(final boolean markAsDirty) {
        return (MagnifierState) super.getState(markAsDirty);
    }

}
