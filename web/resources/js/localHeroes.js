var coordsList = new Array();
var selectedTags = new Array();

$(document).ready(function() {
    var map;
    function initialize() {
        var mapOptions = {
            center: new google.maps.LatLng(51.5219, -0.0717),
            zoom: 14,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("map-canvas"),
                mapOptions);
        for (var i = 0; i < coordsList.length; i = i + 2) {
            addMarker(coordsList[i], coordsList[i + 1]);
        }

    }

    $(".toggleButton").click(function() {
        if ($(this).hasClass("london")) {
            $("body").css("background-image", "url(http://localhost:8080/Local-Heroes/resources/images/edinburgh.jpg)");
            $(this).addClass("edinburgh");
            $(this).removeClass("london");
            switchLocation();
            google.maps.event.addDomListener(window, 'load', switchLocation);
        }
        else {
            $("body").css("background-image", "url(http://localhost:8080/Local-Heroes/resources/images/background.jpg)");
            $(this).addClass("london");
            $(this).removeClass("edinburgh");
            initialize();
            google.maps.event.addDomListener(window, 'load', initialize);
        }
    });



    function switchLocation() {
        var mapOptions = {
            center: new google.maps.LatLng(55.974, -3.165),
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("map-canvas"),
                mapOptions);

    }

    function addMarker(lat, long) {
        var latLong = new google.maps.LatLng(lat, long);
        var marker = new google.maps.Marker({
            position: latLong,
            map: map
        });
    }
    google.maps.event.addDomListener(window, 'load', initialize);

})
