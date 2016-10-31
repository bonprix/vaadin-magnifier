package org.vaadin.addons.magnifier.client;

import com.vaadin.shared.ui.JavaScriptComponentState;

public class MagnifierState extends JavaScriptComponentState {

	private static final long serialVersionUID = -2374185162037353601L;

	public String imageUrl = new String();

    public String syncedMagnifierId = new String();

    public float zoomFactor = 1.5f;

}
