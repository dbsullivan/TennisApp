/**
 * Created by Dave on 11/21/2015.
 */
$(document).ready(function() {

//	1. add tooltips to each of the input fields that indicate what the user should do - for example: enter your email

    $( "#leagueStartDate" ).tooltip({
        content: "Select the date here",
        items: "input",
        position: {my: "left+15 center", at: "right center"}
    });

    $( "#leagueEndDate" ).tooltip({
        content: "Select the date here",
        items: "input",
        position: {my: "left+15 center", at: "right center"}
    });

// 2. change the  textbox to a datepicker
// 3. within the  datepicker, allow the user to select the month and the year via a drop down list

    $("#leagueStartDate").datepicker({changeMonth:true, changeYear:true, dateFormat: 'yy-mm-dd'});
    $("#leagueEndDate").datepicker({changeMonth:true, changeYear:true, dateFormat: 'yy-mm-dd'});

})