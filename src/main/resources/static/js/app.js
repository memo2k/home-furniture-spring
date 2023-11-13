$(document).ready(function () {
    $('#hamburger').click(function () {
        $('#hamburger').toggleClass('active');
        $('#header_bottom').toggleClass('active');

        if ($('#hamburger').hasClass('active')) {
            $('body,html').css('overflow','hidden');
        } else {
            $('body,html').css('overflow','scroll');
        }
    })
});