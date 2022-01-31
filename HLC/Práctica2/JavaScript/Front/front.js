// Código para mostrar y ocultar información
const titulos = document.querySelectorAll("article-title");
const contenido = document.querySelectorAll("article-content");

function toggleInfo(id) {
    let state = contenido[id].style.display;
    if (state === 'none') {
        contenido[id].style.display = "block";
        titulos[id].textContent =  titulos[id].textContent.replace("(clic para mostrar)", "");
    } else {
        titulos[id].textContent += " (clic para mostrar)";
        contenido[id].style.display = "none";
    }
}

titulos[0].addEventListener("click", function () {
    toggleInfo(0)
});
titulos[1].addEventListener("click", function () {
    toggleInfo(1)
});
