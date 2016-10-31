package org.vaadin.addons.magnifier;

import org.vaadin.addons.magnifier.client.MagnifierServerRpc;
import org.vaadin.addons.magnifier.client.MagnifierState;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

// This is the server-side UI component that provides public API for Magnifier
@JavaScript({ "lib/jquery-3.1.1.min.js", "Magnifier.js" })
public class Magnifier extends AbstractJavaScriptComponent {

	private static final long serialVersionUID = 571299685547099480L;

    private final int selectedIndex = -1;

    private static long INSTANCE_COUNT = 0;
    private final long instanceId;

    @SuppressWarnings("serial")
    private final MagnifierServerRpc rpc = new MagnifierServerRpc() {

        @Override
        public void click(final String url, final boolean initialSelection) {
            final int selection = getState(false).imageUrl.indexOf(url);

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
        this.instanceId = INSTANCE_COUNT++;
        setId("magnifier-" + this.instanceId);
        registerRpc(this.rpc);
    }

    public Magnifier(final String imageUrl) {
        this();
        setImageUrl(imageUrl);
    }

    /**
     * Sets the image URL. Causes a reinitialization.
     *
     * @param url the URL
     */
    public void setImageUrl(final String url) {
        getState().imageUrl = url;
    }

    public void syncWith(final Magnifier magnifier) {
        getState().syncedMagnifierId = "magnifier-" + magnifier.instanceId;
    }

    /**
     * Sets the zoom factor of the magnifier. A zoom factor of 1 means the image in the magnifier has native width and if the image shown in the panel is
     * smaller than the native image due to resizing the window, 1 is producing a zoom effect.
     * 
     * @param zoomFactor The zoom factor of this magnifier, has to be greater than 0.
     */
    public void setZoomFactor(final float zoomFactor) {
        if (zoomFactor <= 0) {
            return;
        }
        getState().zoomFactor = zoomFactor;
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


//public class Magnifier extends com.vaadin.ui.AbstractComponent {
//
//    private int clickCount = 0;
//
//    // To process events from the client, we implement ServerRpc
//    private MagnifierServerRpc rpc = new MagnifierServerRpc() {
//
//        // Event received from client - user clicked our widget
//        public void clicked(MouseEventDetails mouseDetails) {
//            
//            // Send nag message every 5:th click with ClientRpc
//            if (++clickCount % 5 == 0) {
//                getRpcProxy(MagnifierClientRpc.class)
//                        .alert("Ok, that's enough!");
//            }
//            
//            // Update shared state. This state update is automatically 
//            // sent to the client. 
//            getState().text = "You have clicked " + clickCount + " times";
//        }
//    };
//
//    public Magnifier() {
//
//        // To receive events from the client, we register ServerRpc
//        registerRpc(rpc);
//    }
//
//    // We must override getState() to cast the state to MagnifierState
//    @Override
//    protected MagnifierState getState() {
//        return (MagnifierState) super.getState();
//    }
//}
