window.org_vaadin_addons_magnifier_Magnifier = function() {
	
	// Can't use "this" inside any function
    var self = this; 
    var elem = self.getElement();  
    elem.itself = self;
    
    self.imageUrl = "";
    self.zoomImageUrl = "";
    self.zoomFactor = 1;
    self.syncedMagnifierId = "";
    
    // Configure Parent Member Fields
    //----------------------------------------------

    // Setup the layout
    elem.magnifierContainer = $("<div>").css({
        "position" : "relative",
        "cursor"   : "none",
        "width"  : "100%",
        "height" : "100%"
    });
      
    elem.magnifier = $("<div>").css({
        "position"            : "absolute",
	    "height"              : "11em",
		"width"               : "11em",
		"border-radius"       : "50%",
		"box-shadow"          : "3px 3px 5px #111",	
        "background"          : 'url(' + self.zoomImageUrl + ') no-repeat',	
        "display"             : "none",
        "margin"              : "0px 0px 0px 0px"
    });
       
    elem.smallImage = $("<img>").attr({
        "src" : self.imageUrl
    }).css({      
    	"display"	   : "block",
    	"margin-left"  : "auto", 
    	"margin-right" : "auto", 
        "max-width"    : "100%",
        "max-height"   : "100%"        
    });

    $(elem.magnifierContainer).append(elem.smallImage, elem.magnifier);
    $(elem).append(elem.magnifierContainer);   

    // Magnifier
    // ----------------------------------
    
    // Syncs this magnifier with another magnifier
    this.syncedMagnifier   = null;

    // Because we cant influence when the elements are created by vaadin we have to
    // notice the id of the to-sync magnifier and constantly try to get an element by this
    // id until we finally found one.
    this.tryToSync = function() {
    	if (self.syncedMagnifier == null && self.syncedMagnifierId != '') {
    		var otherMagnifier = document.getElementById(self.syncedMagnifierId); 

    		if (otherMagnifier != null) {
    			self.syncedMagnifier = otherMagnifier; // Setup the sync
    		}
    	}
    };
    
    // Sets the position of the magnifier relative to the parent
    this.setPosition = function(x, y) {
        // Get native image resolution
        var nativeImage = new Image();
		nativeImage.src = $(elem.smallImage).attr("src");
        var nativeWidth  = nativeImage.width * self.zoomFactor;
		var nativeHeight = nativeImage.height * self.zoomFactor;

		elem.magnifier.css({
			"background-size" : "" + nativeWidth + "px " + nativeHeight + "px",
    	});

        // Calculate magnifier position and background-image offset
        var imageWidth   = $(elem.smallImage).width();
        var imageHeight  = $(elem.smallImage).height();
        var imageMargin  = ($(elem.smallImage).outerWidth(true) - $(elem.smallImage).outerWidth(false))/2;

		console.log(imageMargin);

        var magnifierImagePosX =  (elem.magnifier.width()/2  - x * (nativeWidth / imageWidth));
        var magnifierImagePosY =  (elem.magnifier.height()/2 - y * (nativeHeight / imageHeight));

        $(elem.magnifier).css({ 
            "left"               : (x - elem.magnifier.width()/2) + imageMargin, 
            "top"                : y - elem.magnifier.width()/2,
            "backgroundPosition" : magnifierImagePosX + "px " + magnifierImagePosY + "px"
        });
    };

    // Sets the visibility of the magnifier
    this.setVisible = function(bVisible) {
        if (bVisible) {
            $(elem.magnifier).fadeIn("fast");
        } else {
            $(elem.magnifier).fadeOut("fast");
        }
    }
    
    // Sets the image of the magnifier/image
    this.setImageUrl = function(strImageUrl, strZoomImageUrl) {
    	self.imageUrl = strImageUrl;
    	self.zoomImageUrl = strZoomImageUrl;
    	self.updateImage();
    }
    
    // Updates the src attributes of all elements that are using the image
    this.updateImage = function() {
    	// SmallImage
    	$(elem.smallImage).attr({
    		"src" : self.imageUrl
    	});
    	
    	// Magnifier background (ZoomImage)
    	$(elem.magnifier).css({
            "background" : 'url(' + self.zoomImageUrl + ') no-repeat'	
        });
    }
    
    // Parent Functionality
    //-----------------------------------------------
    
    // Add Event listening
    elem.addEventListener("mousemove", function(event) {
    		self.tryToSync();
    		
            var cursorX = event.pageX - $(elem.smallImage).offset().left;
            var cursorY = event.pageY - $(elem.smallImage).offset().top;
        
            // Check visibility
            if ((cursorX >= 0 && cursorX < $(elem.smallImage).width()) 
                    && (cursorY >= 0 && cursorY < $(elem.smallImage).height())) {
                self.setVisible(true);
                if (self.syncedMagnifier != null) {
                    self.syncedMagnifier.itself.setVisible(true);
                }
            } else {
               self.setVisible(false);
               if (self.syncedMagnifier != null) {
                    self.syncedMagnifier.itself.setVisible(false);
               }
            }
    
            // Set position
            self.setPosition(cursorX, cursorY);
            if (self.syncedMagnifier != null) {
                self.syncedMagnifier.itself.setPosition(cursorX, cursorY);
            }
    });	
    
    // Vaadin Server-side State Change
    //-----------------------------------------------------------
    this.onStateChange = function(e) {
    	var state = self.getState();
    	self.setImageUrl(state.imageUrl, state.zoomImageUrl);
    	self.syncedMagnifierId = state.syncedMagnifierId;
    	self.zoomFactor = state.zoomFactor;
    }

};