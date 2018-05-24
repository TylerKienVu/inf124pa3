$(document).ready(function(){
    console.log(window.location.pathname);
    if(window.location.pathname == "/pa3/") {
        window.onload = function() {
            loadRocks();
        };
    }
    else if (window.location.pathname.includes("details.jsp")) {
        window.onload = function() {
            loadDetails();
        };
    }
});

//takes care of form validation
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        else {
          event.preventDefault();
        }
        form.classList.add('was-validated');
      }, true);
    });
  }, false);
})();

function loadRocks() {
    console.log("loadRocks");
    
    $.ajax({url: "rocks", success: function(result){
        console.log("success: loadRocks");
        var rocks = result.split("history");
//        console.log(result.split("history"))
        var historyArray = rocks[1].split("\n");
        var productsArray = rocks[0].split("\n");
        
        for(var x in productsArray){
            $("#product-list").append(productsArray[x]);
        }
        
        for(var x in historyArray){
            $("#product-view-history").append(historyArray[x])
        }
    }});     
}

function loadDetails() {
    console.log("loadDetails");
    var url_string = window.location.href;
    var url = new URL(url_string);
    var rock_id = url.searchParams.get("rock_id");
    
    $.ajax({url: "../details?rock_id=" + rock_id, success: function(result){
        console.log("success: loadDetails");
        $(".product-detail-container").append(result);
        
        //Add listener for add cart button after details load
        $("#add-to-cart").click(function(){
//           console.log("click");
           addToCart();
        });        
    }});   
}

function addToCart() {
    console.log("addToCart");
    var url_string = window.location.href;
    var url = new URL(url_string);
    var rock_id = url.searchParams.get("rock_id");
    
    $.ajax({url: "../cart?rock_id=" + rock_id, success: function(result){
        console.log("success: addToCart");
        $("#cart-alert-container").html("<div class=\"alert alert-success\" role=\"alert\">Item added to cart. Your cart has a total of " +
                result + " items.</div>");
    }});   
}