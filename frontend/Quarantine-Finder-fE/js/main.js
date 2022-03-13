// preloader
window.onload = function () {
  document.querySelector(".loading-overlay ").style.display = "none";
};

// to top
const scrollBtn = document.querySelector(".scroll-to-top");
const upperbar = document.querySelector(".upperbar");

scrollBtn.style.display = "none";

scrollBtn.addEventListener("click", () => {
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
});

document.addEventListener("scroll", (e) => {
  if (document.documentElement.scrollTop <= 100) {
    scrollBtn.style.display = "none";
  } else {
    scrollBtn.style.display = "block";
  }
});

// start filter range

let min = 10;
let max = 100;

const calcLeftPosition = (value) => (100 / (100 - 10)) * (value - 10);

$("#rangeMin").on("input", function (e) {
  const newValue = parseInt(e.target.value);
  if (newValue > max) return;
  min = newValue;
  $("#thumbMin").css("left", calcLeftPosition(newValue) + "%");
  $("#min").html(newValue);
  $("#line").css({
    left: calcLeftPosition(newValue) + "%",
    right: 100 - calcLeftPosition(max) + "%",
  });
});

$("#rangeMax").on("input", function (e) {
  const newValue = parseInt(e.target.value);
  if (newValue < min) return;
  max = newValue;
  $("#thumbMax").css("left", calcLeftPosition(newValue) + "%");
  $("#max").html(newValue);
  $("#line").css({
    left: calcLeftPosition(min) + "%",
    right: 100 - calcLeftPosition(newValue) + "%",
  });
});

$(".item-header").click(function () {
  $(this).next().slideToggle(500);

  $(this).find(".icon").toggleClass("active");
  $(this).toggleClass("active");
});
