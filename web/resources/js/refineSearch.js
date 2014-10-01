$(document).ready(function() {
    $('.tag').click(function() {
       var tagName = $(this).html();
       $(this).addClass('selected');

       $('.result').not('.result[herotags*="' +tagName+'"]').css('display', 'none');

       //remove marker from map (add to hidden markers array and remove from visiblemarkers array)
       $('.result:hidden').each(function() {
           var markerToHide = getMarkerFromPosition(mapMarkers, new google.maps.LatLng($(this).attr('latitude'), $(this).attr('longitude')));
           
           if(markerToHide !== null) {
               console.log("Removing marker from map");
               markerToHide.setMap(null);
               hiddenMarkers.push(markerToHide);
           }
 
       });
       
    });
    
    $('.clearButton').click(function() {
        $('.tag').removeClass('selected');
        $('.result').css('display', 'block');
        displayAllMarkers();
    })
});

function getMarkerFromPosition(markerList, position) {
    for (var i = 0; i < markerList.length; i++) {
        if(markerList[i].getPosition().equals(position)) {
            return markerList[i];
        }
    }
    return null;
}

function displayAllMarkers(){
    for (var i = 0; i < hiddenMarkers.length; i++) {
        hiddenMarkers[i].setMap(map);
    }
}

