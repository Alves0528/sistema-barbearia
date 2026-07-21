const TELEFONE_MASCARA = '(00) 00000-0000';

document.addEventListener('DOMContentLoaded', function () {
    var inputTelefone = document.getElementById('campoTelefone');
    if (inputTelefone) {
        IMask(inputTelefone, { mask: TELEFONE_MASCARA });
    }
});