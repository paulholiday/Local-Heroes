$(document).ready(function() {
    $('.tag').click(function() {
       var tagName = $(this).html();
       $(this).addClass('selected');

       $('.result').not('.result[herotags*="' +tagName+'"]').css('display', 'none');

    });
    
    $('.clearButton').click(function() {
        $('.tag').removeClass('selected');
        $('.result').css('display', 'block');
    })
});

