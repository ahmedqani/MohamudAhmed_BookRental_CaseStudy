
let nCount = selector => {
  $(selector).each(function () {
    $(this)
      .animate({
        Counter: $(this).text()
      }, {
        // A string or number determining how long the animation will run.
        duration: 4000,
        // A string indicating which easing function to use for the transition.
        easing: "swing",
        /**
         * A function to be called for each animated property of each animated element. 
         * This function provides an opportunity to
         *  modify the Tween object to change the value of the property before it is set.
         */
        step: function (value) {
          $(this).text(Math.ceil(value));
        }
      });
  });
};

let a = 0;
$(window).scroll(function () {
  // The .offset() method allows us to retrieve the current position of an element  relative to the document
  let oTop = $(".numbers").offset().top - window.innerHeight;
  if (a == 0 && $(window).scrollTop() >= oTop) {
    a++;
    nCount(".rect > h1");
  }
});



/**
 *
 *  sticky navigation
 *
 */

let navbar = $(".navbar");

$(window).scroll(function () {
  // get the complete hight of window
  let oTop = $(".section-2").offset().top - window.innerHeight;
  if ($(window).scrollTop() > oTop) {
    navbar.addClass("sticky");
  } else {
    navbar.removeClass("sticky");
  }
});

//$(document).ready(function(){
//  $("main").on("mouseover mouseout", function(){
//    $(this).toggleClass(".fill2");
//    $("h1").html("yoyo");
//  });
//});

let text = "";
const fruits = ["This", "Page", "Is","Currently", "Under", "Construction", "!"];
fruits.forEach(myFunction);

document.getElementById("box").innerHTML = text;
let box = document.getElementById("box")
function myFunction(item) {
  text += item + " ";
  }

box.animate({
  opacity: [ 0, 0.9, 1 ],
  offset: [ 0, 0.8 ],
  easing: [ 'ease-in', 'ease-out' ],
  transform: 'rotate(180deg)'
}, 1000);



