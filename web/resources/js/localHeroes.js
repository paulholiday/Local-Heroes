var coordsList = new Array();
var selectedTags = new Array();

$(document).ready(function() {
    var map;
    var titleString = $('.body h1').html();
    var categoryString = titleString.substring(12); //hard-coded value to get Category
    
    getResults();
    function getResults() {
        
        var urlString = 'rest/heroes/' + categoryString;

        $.getJSON(urlString, function(data) {

            $.each(data, function(idx, obj) {

//                $('.scrollableResults').append('<div class="result" id="result-' + idx +'"><h3>' + obj.name + '</h3></div>');
//                if(obj.address !== null) {
//                    $('#result-' + idx).append('<p>' + obj.address.line1 + ', ' + obj.address.county + ', ' + obj.address.postCode + '</p>');
//                }

                var lat = obj.point.latitude;
                var long = obj.point.longitude;

                coordsList.push(lat, long);

            });  

         }).done(function() {
             initialize();
             //getTags();
         });
        
    }
    
    function getTags() {
        var urlString = 'rest/tags/' + categoryString;
        
        $.getJSON(urlString, function (data) {
            $.each(data, function(idx, obj) {
                if(idx === 'result') {
                    $.each(obj, function(index, object) {
                        $('.tags').append('<span class="tag">' + object.name + '</span>');
                    });
                } 
            });
        });
    }
    
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
        
        google.maps.event.addDomListener(window, 'load', initialize);
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

});
