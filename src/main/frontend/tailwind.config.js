/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["../resources/templates/**/*.{html,js}"],
    theme: {
        extend: {},
    },
    daisyui: {
      themes: true
    },
    plugins: [require("daisyui")],
}