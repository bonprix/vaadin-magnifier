package org.vaadin.addons.magnifier.demo;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.magnifier.Magnifier;
import org.vaadin.addons.magnifier.demo.model.ImageUrl;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("Magnifier Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private static final List<ImageUrl> IMAGE_URLS = Arrays.asList(new ImageUrl("9/3/3/3/1869333.jpg"), new ImageUrl("4/6/1/3/12104613.jpg"),
                                                                   new ImageUrl("4/6/1/3/15154613.jpg"));

    @WebServlet(
        value = "/*",
        asyncSupported = true)
    @VaadinServletConfiguration(
        productionMode = false,
        ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(final VaadinRequest request) {

        // Create Magnifier and set Size and ImageURL
        final Magnifier magnifier = new Magnifier();
        magnifier.setHeight(600, Unit.PIXELS);
        magnifier.setWidth(400, Unit.PIXELS);
        // Set the ZoomFactor
        magnifier.setZoomFactor(1.5f);

        // Left side Images
        final VerticalLayout imageLayout = new VerticalLayout();
        imageLayout.setMargin(true);
        imageLayout.setSpacing(true);

        boolean firstTime = true;
        Image image = null;

        for (final ImageUrl imageUrl : DemoUI.IMAGE_URLS) {
            // Left side images
            image = new Image(null, new ExternalResource(imageUrl.getImageUrl()));
            image.setHeight(300, Unit.PIXELS);
            image.setWidth(200, Unit.PIXELS);
            image.addClickListener((event) -> {
                magnifier.setImageUrl(imageUrl.getImageUrl());
                magnifier.setZoomImageUrl(imageUrl.getImageZoomUrl());
            });
            imageLayout.addComponents(image);

            // set default magnifier image
            if (firstTime) {
                magnifier.setImageUrl(imageUrl.getImageUrl());
                // OPTIONAL: Set the ZoomImageUrl
                magnifier.setZoomImageUrl(imageUrl.getImageZoomUrl());
                firstTime = false;
            }
        }

        final VerticalLayout magnifierLayout = new VerticalLayout();
        magnifierLayout.setStyleName("demoContentLayout");
        magnifierLayout.setSizeFull();
        magnifierLayout.addComponent(magnifier);
        magnifierLayout.setComponentAlignment(magnifier, Alignment.MIDDLE_CENTER);

        final HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
        hsplit.setFirstComponent(imageLayout);
        hsplit.setSecondComponent(magnifierLayout);

        // Set the position of the splitter as percentage
        hsplit.setSplitPosition(20, Sizeable.UNITS_PERCENTAGE);

        setContent(hsplit);
    }
}