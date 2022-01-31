// Código para mostrar y ocultar información
const titulo1 = $("#t1");
const titulo2 = $("#t2");
const contenido1 = $("#c1");
const contenido2 = $("#c2");

titulo1.on("click", function (event) {
    if (contenido1.is(":hidden")) {
        contenido1.show();
        titulo1.text(titulo1.text().replace(" (clic para mostrar)", ""));
    } else {
        contenido1.hide();
        titulo1.text(titulo1.text() + " (clic para mostrar)");
    }
});

titulo2.on("click", function (event) {
    if (contenido2.is(":hidden")) {
        contenido2.show();
        titulo2.text(titulo2.text().replace(" (clic para mostrar)", ""));
    } else {
        contenido2.hide();
        titulo2.text(titulo2.text() + " (clic para mostrar)");
    }
});
