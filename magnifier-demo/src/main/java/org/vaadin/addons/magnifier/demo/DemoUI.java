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

    private Magnifier magnifier = new Magnifier();
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
    protected void init(VaadinRequest request) {

        // Create Magnifier and set Size and ImageURL
        this.magnifier = new Magnifier();
        this.magnifier.setHeight(600, Unit.PIXELS);
        this.magnifier.setWidth(400, Unit.PIXELS);
        // Set the ZoomFactor
        this.magnifier.setZoomFactor(1.5f);

        // Left side Images
        VerticalLayout imageLayout = new VerticalLayout();
        imageLayout.setMargin(true);
        imageLayout.setSpacing(true);

        boolean firstTime = true;
        Image image = null;

        for (ImageUrl imageUrl : this.IMAGE_URLS) {
            // Left side images
            image = new Image(null, new ExternalResource(imageUrl.getImageUrl()));
            image.setImmediate(true);
            image.setHeight(300, Unit.PIXELS);
            image.setWidth(200, Unit.PIXELS);
            image.addClickListener((event) -> {
                this.magnifier.setImageUrl(imageUrl.getImageUrl());
                this.magnifier.setZoomImageUrl(imageUrl.getImageZoomUrl());
            });
            imageLayout.addComponents(image);

            // set default magnifier image
            if (firstTime) {
                this.magnifier.setImageUrl(imageUrl.getImageUrl());
                // OPTIONAL: Set the ZoomImageUrl
                this.magnifier.setZoomImageUrl(imageUrl.getImageZoomUrl());
                firstTime = false;
            }
        }

        VerticalLayout magnifierLayout = new VerticalLayout();
        magnifierLayout.setStyleName("demoContentLayout");
        magnifierLayout.setSizeFull();
        magnifierLayout.addComponent(this.magnifier);
        magnifierLayout.setComponentAlignment(this.magnifier, Alignment.MIDDLE_CENTER);

        HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
        hsplit.setFirstComponent(imageLayout);
        hsplit.setSecondComponent(magnifierLayout);

        // Set the position of the splitter as percentage
        hsplit.setSplitPosition(20, Sizeable.UNITS_PERCENTAGE);

        setContent(hsplit);
    }
}