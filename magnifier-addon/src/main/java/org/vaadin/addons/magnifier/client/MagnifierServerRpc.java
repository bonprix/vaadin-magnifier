package org.vaadin.addons.magnifier.client;

import com.vaadin.shared.communication.ServerRpc;

/**
 * RPC-Interface that will get called from the javascript file
 *
 */
// ServerRpc is used to pass events from client to server
public interface MagnifierServerRpc extends ServerRpc {

    // Widget click is clicked
	public void click(final String url, final boolean initialSelection);

}
