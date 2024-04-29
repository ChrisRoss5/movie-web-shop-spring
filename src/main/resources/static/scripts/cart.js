const movieEls = document.querySelectorAll(".movie");
const emptyCartMessageEl = document.querySelector("#empty-cart-message");
const cartEl = document.querySelector("#cart");
const emptyCartEl = document.querySelector("#empty-cart");
const finalPriceEl = document.querySelector("#final-price");

prepareMovies();
window.addEventListener("storage", prepareMovies);

if (cartEl) emptyCartEl.onclick = () => clearCart();

function getCart() {
  const cart = localStorage.getItem("cart");
  return cart ? JSON.parse(cart) : [];
}

function updateCart(cart) {
  localStorage.setItem("cart", JSON.stringify(cart));
  prepareMovies();
}

function clearCart() {
  localStorage.removeItem("cart");
  toggleCartEl(false);
}

function prepareMovies() {
  const cart = getCart();
  let finalPrice = 0;

  for (const movieEl of movieEls) {
    const addOneMovieEl = movieEl.querySelector(".add-one");
    const editCountEl = movieEl.querySelector(".edit-count");
    const movieTotalPriceEl = movieEl.querySelector(".movie-total-price");
    const countEl = editCountEl.querySelector(".count");
    const removeFromCartEl = movieEl.querySelector(".remove-from-cart");
    const { id, price } = movieEl.dataset;

    let PurchaseMovie = cart.find((m) => m.movieId === id);
    toggleEditCountEl(movieEl, !!PurchaseMovie);
    if (PurchaseMovie) {
      countEl.textContent = PurchaseMovie.quantity.toString();
      const movieTotalPrice = price * PurchaseMovie.quantity;
      updateElPrice(movieTotalPriceEl, movieTotalPrice);
      finalPrice += movieTotalPrice;
    }
    if (cartEl) {
      movieEl.classList[PurchaseMovie ? "remove" : "add"]("hidden");
      removeFromCartEl.onclick = () => {
        cart.splice(cart.indexOf(PurchaseMovie), 1);
        updateCart(cart);
      };
    } else
      addOneMovieEl.onclick = () => {
        PurchaseMovie = { movieId: id, quantity: 1 };
        cart.push(PurchaseMovie);
        updateCart(cart);
      };

    editCountEl.querySelectorAll(".btn").forEach((btn) => {
      btn.onclick = () => {
        PurchaseMovie.quantity += btn.textContent === "+" ? 1 : -1;
        if (PurchaseMovie.quantity === 0) cart.splice(cart.indexOf(PurchaseMovie), 1);
        updateCart(cart);
      };
    });
  }

  updateElPrice(finalPriceEl, finalPrice);
  toggleCartEl(cart.length > 0);
}

function toggleEditCountEl(movieEl, show) {
  const addOneMovieEl = movieEl.querySelector(".add-one");
  const editCountEl = movieEl.querySelector(".edit-count");
  if (!cartEl) addOneMovieEl.style.display = show ? "none" : "";
  editCountEl.style.display = show ? "" : "none";
}

function toggleCartEl(show) {
  if (!cartEl) return;
  emptyCartMessageEl.style.display = show ? "none" : "";
  cartEl.style.display = show ? "" : "none";
}

function updateElPrice(el, price) {
  if (el) el.textContent = price.toFixed(2);
  if (el === finalPriceEl && price === 0) toggleCartEl(false);
}
