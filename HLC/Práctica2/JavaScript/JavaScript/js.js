// Variables para los elementos del documento
const texto = document.querySelector(".campoModificar");
const botonModificar = document.querySelector(".botonModificar");
const listaModificar = document.querySelector(".listaModificar");
const textoModificable = document.querySelector(".textoModificable");

function cambiarTexto() {
    textoModificable.textContent = texto.value;
}

botonModificar.addEventListener("click", cambiarTexto);

function cambiarColor(index) {
    let bgcol = "white";
    let col = "black"
    switch (index) {
        case 0:
            bgcol = "black";
            col = "white";
            break;
        case 1:
            bgcol = "#daa0a0";
            col = "black";
            break;
        case 2:
            bgcol = "#bedaa0";
            col = "black";
            break;
        case 3:
            bgcol = "#a0a0da";
            col = "black";
            break;
    }
    listaModificar.style.color = col;
    textoModificable.style.color = col;

    listaModificar.style.backgroundColor = bgcol;
    textoModificable.style.backgroundColor = bgcol;
}

listaModificar.addEventListener("click", function () {
    cambiarColor(listaModificar.selectedIndex)
});

// Para mostrar y ocultar texto
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
titulos[2].addEventListener("click", function () {
    toggleInfo(2)
});
titulos[3].addEventListener("click", function () {
    toggleInfo(3)
});

