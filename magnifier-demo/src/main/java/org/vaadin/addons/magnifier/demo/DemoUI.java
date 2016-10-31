package org.vaadin.addons.magnifier.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.magnifier.Magnifier;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("Magnifier Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
    	
    	   	
        // Initialize our new UI component
        final Magnifier magnifier = new Magnifier();
        magnifier.setImageUrl("http://rfp.laudert.de/previews/assets/prev_l/9/3/3/3/1869333.jpg");
        magnifier.setHeight(600, Unit.PIXELS);
        magnifier.setWidth(400, Unit.PIXELS);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(magnifier);
        layout.setComponentAlignment(magnifier, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
}