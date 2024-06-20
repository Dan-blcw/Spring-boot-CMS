/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global myContextPath */

function genPagging(totalRecords, pageSize) {
    var startRecord = parseInt($('input#startService').val());
    var pageCurent = Math.ceil((startRecord + 1) / pageSize);
    var totalPage = Math.ceil(totalRecords / pageSize);
    $('.p-pagging').text('Trang ' + pageCurent + '/' + totalPage);
    var html = "";
    if (pageCurent > 2) {
        html += '<li><a href="#" class="aPage" data-id="' + (pageCurent - 1) + '">Trước</a></li>';
        html += '<li><a href="#" class="aPage" data-id="1">1</a></li>';
    }
    var start = (pageCurent > 2) ? (pageCurent - 1) : 1;
    if (start > 3)
        html += '<li><a href="javascript:void(0)">...</a></li>';
    for (var i = start; i < pageCurent + 3 && i <= totalPage; i++) {
        if (i === pageCurent)
            html += '<li class="active"><a href="javascript:void(0)">' + i + '</a></li>';
        else
            html += '<li><a href="#" class="aPage" data-id="' + i + '">' + i + '</a></li>';
    }
    if (pageCurent + 3 < totalPage) {
        html += '<li><a href="javascript:void(0)">...</a></li>';
        html += '<li><a href="#" class="aPage" data-id="' + totalPage + '">' + totalPage + '</a></li>';
    }
    if (pageCurent < totalPage)
        html += '<li><a href="#" class="aPage" data-id="' + (pageCurent + 1) + '">Sau</a></li>';
    /*for(var i = 1; i <= totalPage; i++){
     if(i == pageCurent) html += '<li class="active"><a href="javascript:void(0)">' + i + '</a></li>';
     else html += '<li><a href="#" class="aPage" data-id="' + i + '">' + i + '</a></li>';
     }*/
    $('.ul-pagging').html(html);
}
var name_control = '';
function ShowFileForm(cID, sValue) {
    name_control = cID;
    var finder = new CKFinder();
    finder.basePath = '../';
    finder.selectActionFunction = refreshPage;
    finder.popup();
    return false;
}

function ShowFile() {
    var finder = new CKFinder();
    finder.basePath = '../';
    finder.selectActionFunction = refreshFile;
    finder.popup();

    return false;
}

function refreshFile(arg) {
    addFile(arg);
}



function checkPhone(phone) {
    if (phone !== '') {
        var firstNumber1 = phone.substring(0, 1);
        var firstNumber2 = phone.substring(0, 2);
        if (firstNumber1 === '0' && phone.length === 10) {
            return true;
        } else if (firstNumber2 === '84' && phone.length === 11) {
            return true;
        }
        return false;
    }
    return false;
}

$("input[data-type='currency']").on({
    keyup: function () {
        formatCurrency($(this));
    },
    blur: function () {
        formatCurrency($(this), "blur");
    }
});
function formatCurrency(input) {
    var input_val = input.val();
    // don't validate empty input
    if (input_val === "") {
        return;
    }
    // original length
    var original_len = input_val.length;
    // initial caret position 
    var caret_pos = input.prop("selectionStart");
    input_val = formatNumber(input_val);
    // send updated string to input
    input.val(input_val);
    // put caret back in the right position
    var updated_len = input_val.length;
    caret_pos = updated_len - original_len + caret_pos;
    input[0].setSelectionRange(caret_pos, caret_pos);
}

function formatNumber(n) {
    return n.replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

function refreshPage(arg) {
    var obj = document.getElementById(name_control);
    if (name_control.indexOf('File') > -1 || name_control.indexOf('Img') > -1 || name_control.indexOf('Logo') > -1)
        obj.value = '~' + arg;
    else
        obj.value = arg;
    //arg = '~' + arg;
    $('#' + name_control).val(arg);
    var info = $('#' + name_control).parent().parent();
    if (info.length) {
        info.find('img').attr('src', arg);
    }
}


//var timeOut = 1000 * 60 * 100; // 30 minutes
//var lastActivity = new Date().getTime();
//function checkTimeOut() {
//    if (new Date().getTime() > lastActivity + timeOut) {
////        alert("OK");
////        console.log("date 2: " +lastActivity + timeOut);
////        console.log("date: " + new Date().getTime());
//        window.location.href = myContextPath + "/login";
//    } else {
//        window.setTimeout(checkTimeOut, 1000); // check once per second
//    }
//}
//checkTimeOut();
