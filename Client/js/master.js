function hideElem(elem) {
    var element = document.getElementById(elem);
    element.classList.add("invisible");
}
function showElem(elem) {
    var element = document.getElementById(elem);
    element.classList.remove("invisible");
}
