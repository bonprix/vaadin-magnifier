# vaadin-magnifier Add-on for Vaadin 7

The Magnifier component is a client-side Widget. 

![screenshot](assets/screenshot1.png)

### Features:
- magnifying glass effect on images
- set Magnifier ImageUrl and size
- set ZoomFactor 
- OPTIONAL: set ZoomImageUrl (Sets the HighRes ZoomImage URL if available)

## Online demo
As soon as our demo server is available, the demo link will be displayed here.

## Usage

### Maven

```xml
<dependency>
    <groupId>org.vaadin.addons</groupId>
	<artifactId>magnifier</artifactId>
	<version>1.0</version>
</dependency>

<repository>
   <id>vaadin-addons</id>
   <url>http://maven.vaadin.com/vaadin-addons</url>
</repository>
```

No widgetset required.

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/vaadin-magnifier

## Building and running demo

git clone https://github.com/bonprix/vaadin-magnifier
mvn clean install
cd demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/
 
## Release notes

### Version 1.0
- client-side component

## Known issues

- please report issues and help us to make this even better ;)

## Roadmap

This component is developed as a part of a bonprix project with no public roadmap or any guarantees of upcoming releases. 

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under MIT License. For license terms, see LICENSE.txt.

vaadin-magnifier is written by members of Bonprix Handelsgesellschaft mbh:
- Torben Meißner (https://github.com/torbon)

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

```java

// Initialize our new UI component
final Magnifier magnifier = new Magnifier();
// set the magnifier image 
magnifier.setImageUrl("http://rfp.laudert.de/previews/assets/prev_m/9/3/3/3/1869333.jpg");
// set the Height of the magnifier image
magnifier.setHeight(600, Unit.PIXELS);
// set the Width of the magnifier image 
magnifier.setWidth(400, Unit.PIXELS);          
// OPTIONAL: Set the ZoomImageUrl. This should be a higher quality image. Used for a good and clean magnifying effect
magnifier.setZoomImageUrl("http://rfp.laudert.de/previews/assets/prev_l/9/3/3/3/1869333.jpg");
// Sets the zoom factor of the magnifier. A zoom factor of 1 means the image in the magnifier has native width 
// and if the image shown in the panel is smaller than the native image due to resizing the window, 1 is producing a zoom effect.
magnifier.setZoomFactor(1.5f);


```

