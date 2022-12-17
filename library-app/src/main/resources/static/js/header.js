/**
 * 写页面头部
 */
function writeHeader() {
    document.write('<div class="nav-div" style="display: grid">\n' +
        '            <ul class="nav" style="margin: auto">\n' +
        '                <li class="nav-item">\n' +
        '                    <a class="nav-link active" aria-current="page" href="/user.html">User</a>\n' +
        '                </li>\n' +
        '                <li class="nav-item">\n' +
        '                    <a class="nav-link" href="/book.html">Book</a>\n' +
        '                </li>\n' +
        '                <li class="nav-item">\n' +
        '                    <a class="nav-link" href="#">Link</a>\n' +
        '                </li>\n' +
        '                <li class="nav-item">\n' +
        '                    <a class="nav-link disabled">Disabled</a>\n' +
        '                </li>\n' +
        '            </ul>\n' +
        '        </div>');
}