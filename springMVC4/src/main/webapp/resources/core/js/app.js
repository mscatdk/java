$(function() {
    $("#customerSearch").keypress(function (e) {
    	console.log("Test me key");
        if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
            $('#customerSearchSubmit').click();
            return false;
        } else {
            return true;
        }
    });
});