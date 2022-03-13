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






