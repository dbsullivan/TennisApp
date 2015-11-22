/**
 * Created by Dave on 11/21/2015.
 */
$(document).ready(function() {

//	1. add tooltips to each of the input fields (email, password, birthdate) that indicate what the user should do - for example: enter your email
// with the default, title in the HTML holds the content for tooltip text, and this works:	$("#inputEmail").tooltip();

//  However, to set content to use something other than "title" attribute which is missing in the HTML,
//  then we use the option content: , and items: "input", allowing input elements to have tooltips.


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

// 2. change the birthdate textbox to a datepicker
// 3. within the birthdate datepicker, allow the user to select the month and the year via a drop down list

    $("#leagueStartDate").datepicker({changeMonth:true, changeYear:true});
    $("#leagueEndDate").datepicker({changeMonth:true, changeYear:true});

})