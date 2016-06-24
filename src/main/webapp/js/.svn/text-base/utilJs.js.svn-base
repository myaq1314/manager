/**
 * author: wang lin
 * date: 2015
 * @file
 */

// for (var i = 0; i < elements.length; i++) {
// $(function () {    });
// printObject(param);     return;

// 弹出消息提示框
function msg(str) {
    alert(str);
}

// 打印 json object 的内容   print the content of a json object
function printObject(param) {
    msg(objToJsonString(param));
}

// 将 json object 转换成 json 字符串  convert a json object to json string
function objToJsonString(param, recursiveFlag) {
    if (isNull(param)) {
        return null;
    }

    var result = '';

    if (param instanceof Array) { // 输入参数 param 是 js Array
        result += '[' + '\n';

        for (var i = 0; i < param.length; i++) {   // 转换 array 为 json string
            var theValue = param[i];    // msg( typeof theValue )

            if (isNull(theValue)) {
                result += 'null';
            } else if (typeof theValue === 'number' || typeof theValue === 'string'
                    || typeof theValue === 'boolean') {   // 如果 theValue 不是对象，而是 number 或 string 或 boolean
                if (typeof theValue === 'string') {
                    theValue = '\"' + theValue + '\"';
                }
                result += theValue;
            } else {
                // 递归去转换 json object
                // arguments.callee 是指向正在执行的函数的指针
                var recursion = arguments.callee(theValue, true);
                result += recursion;
            }

            result +=  ',\n';
        }

        if (result.lastIndexOf(',') !== -1) {
            // 删除遍历后，多添加的一个逗号
            result = result.substring(0, result.lastIndexOf(','));
            result +=  '\n';
        }

        result += ']';
    } else {  // 输入参数 param 是 json object
        result = '{' + '\n';

        var space = '    ';
        if (recursiveFlag) {
            space += '    ';
        }

        for (var key in param) {
            if (param.hasOwnProperty(key)) {
                if (typeof key === 'number' || typeof key === 'string'
                        || typeof key === 'boolean') {
                    var value = param[key];

                    if (isNull(value)) {
                        result += space + '\"' + key + '\"' + ': null';
                        result += ',\n';
                        continue;
                    }

                    if (typeof value === 'number' || typeof value === 'string'
                                || typeof value === 'boolean') {   // 如果 value 不是对象，而是 number 或 string 或 boolean
                        if (typeof value === 'string') {
                            value = '\"' + value + '\"';
                        }

                        result += space + '\"' + key + '\"' + ': ' + value;
                    } else if (typeof value === 'object') {   // 如果 value 的类型是 object。如果是 function 和 undefined，不考虑
                        if (value instanceof Array) {   // 转换 array 为 json string
                            result += space + '\"' + key  + '\"' + ': ';    // msg( key+"    "+ value )
                            result += '[' + '\n';

                            space += space;

                            for (var i = 0; i < value.length; i++) {
                                var theValue = value[i];    // msg( typeof theValue )

                                if (isNull(theValue)) {
                                    result += space + 'null';
                                } else if (typeof theValue === 'number' || typeof theValue === 'string'
                                        || typeof theValue === 'boolean') {   // 如果 theValue 不是对象，而是 number 或 string 或 boolean
                                    if (typeof theValue === 'string') {
                                        theValue = space + '\"' + theValue + '\"';
                                    }
                                    result += space + theValue;
                                } else {  // 递归去转换 json object
                                    // arguments.callee 是指向正在执行的函数的指针
                                    var recursion = arguments.callee(theValue, true);
                                    result += space + recursion;
                                }

                                result +=  ',\n';
                            }

                            if (result.lastIndexOf(',') !== -1) {
                                // 删除遍历后，多添加的一个逗号
                                result = result.substring(0, result.lastIndexOf(','));
                                result +=  '\n';
                            }

                            result += space + ']';
                        } else {    // 递归去转换 json object
                            if (isNull(value)) {
                                var recursion = 'null';
                            }

                            // arguments.callee 是指向正在执行的函数的指针
                            var recursion = arguments.callee(value, true);
                            result += space + '\"' + key + '\"' + ': ' + recursion;
                        }
                    }
                }
            }
            result += ',\n';
        }

        if (result.lastIndexOf(',') !== -1) {
            result = result.substring(0, result.lastIndexOf(','));  // 删除遍历后，多添加的一个逗号
            result +=  '\n';
        }

        if (recursiveFlag) {
            result += space + '}';
        } else {
            result += '}';
        }
    }

    return result;
}

// 用 id 获取 js 对象的函数
function jsGet(id) {
    return document.getElementById(id);
}

// 用 id 获取 jquery 对象的函数
function jqGet(id) {
    return $('#' + id);
}

// 创建一个 js 对象     retrieve a js object
function getObject() {
    return {};
}

// 创建一个 js 数组      retrieve a js array
function getArray() {
    return [];
}

// 往 json object 中添加键值对     put content into a json object
function put(json, key, value) {
    json[key] = value;
}

// 往 js 数组中添加元素      add a value to the js array
function add(array, value) {
    array.push(value);
}

// 从 json object 或 js 数组中获得元素    retrieve item from a js array or a json object
function get(obj, indexOrKey) {
    if (obj instanceof Array) {
        return obj[indexOrKey];
    } else if (obj instanceof Object) {
        return obj[indexOrKey];
    }
}

// 删除 json object 或 js 数组中的元素       remove the key-value in a json object or the value in a js array
function remove(obj, indexOrKey) {
    if (obj instanceof Array) {
        obj.splice(indexOrKey, 1);  // 1 means delete 1 item from this array
    } else if (obj instanceof Object) {
        delete obj[indexOrKey];
    }
}

// 判断 json object 中是否包含某个 key      does the json object contain the key?
function containsKey(param, key) {   // printObject(param)
    key += '';   // json object 的 key 都是 string，所以要将参数可以先转换为 string，再做判断
    for (var st in param) {
        if (param.hasOwnProperty(st)) {
            if (st === key) {
                return true;
            }
        }
    }
    return false;
}

// 判断 js 数组中是否包含某元素      does the js array contain the value?
function contains(array, value) {
    for (var i = 0; i < array.length; i++) {
        if (value === get(array, i)) {
            return true;
        }
    }
    return false;
}

// 删除字符串左右的空格      trim the spaces on the left and right of a js string
function trim(st) {
    return $.trim(st);
}

// 删除字符串左右的空格    trim the spaces on the left and right of a js string
function jsTrim(st) {
    return st.replace(/(^\s*)|(\s*$)/g, '');
}

// 提交一个不含参数的 post 请求      submit a post request without param
function submitPostWithoutParam(url) {
    var temp = document.createElement('form');
    temp.action = url;
    temp.method = 'post';
    temp.style.display = 'none';
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

// 提交一个包含参数的 post 请求    submit a post request with param
function submitPost(url, param) {     // param is a json object
    var temp = document.createElement('form');
    temp.action = url;
    temp.method = 'post';
    temp.style.display = 'none';

    for (var st in param) {
        if (param.hasOwnProperty(st)) {
            var opt = document.createElement('textarea');
            opt.name = st;
            opt.value = param[st];
            temp.appendChild(opt);
        }
    }

    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

// 获取地址栏链接中的参数
function getQueryArg(argName) {
    var reg = new RegExp('(^|&)' + argName + '=([^&]*)(&|$)');
    var r = window.location.search.substr(1).match(reg);

    if (r != null) {
        return  unescape(r[2]);
    } else {
        return null;
    }
}

// 页面跳转到某地址，不含参数       forward page to a url without param
function pageForwardWithoutParam(url) {
    window.location.href = url;
}

// 页面跳转到某地址，包含参数     forward page to a url with param
function pageForward(url, param) {   // msg( arguments.length )
    if (arguments.length !== 1) {
        var str = '?';

        for (var st in param) {  // param is a json object
            if (param.hasOwnProperty(st)) {
                str += st + '=' + param[st];   // msg( param.length )
                str += '&';
            }
        }
        str = str.substring(0, str.length - 1);   // 删除遍历后多添加的一个 &    // msg( url + str )

        window.location.href = url + str;
    }
}

// 清空 form 里的各个控件     reset the inputs of a form
function resetForm(id) {
    jsGet(id).reset();
}

// 提交一个ajax 请求    submit an ajax request
// url 是请求的地址；param是请求的参数，是一个 json object
// callbackFunctionStr 是回调函数名的字符串，如 “alert()” 函数的回调函数名的字符串就是 “alert”
// callbackFunctionStr is the name string of the callback function,
// for example, callbackFunctionStr of the function alert() is 'alert'
function theAjaxSubmit(url, param, callbackFunctionStr) {
    $.ajax({
        type: 'POST',
        url: url,
        data: param,    // param is a json object
        dataType: 'json',
        async: false,
        error: function (data) {
            if (data !== null && data !== undefined) {
                if (data.responseJSON !== undefined && data.responseJSON.msg !== undefined) {
                    msg(data.responseJSON.msg);
                } else {
                    if (data.status !== null && data.status !== undefined) {
                        if (Number(data.status) > 0 && data.msg !== null && data.msg !== undefined) {
                            msg(data.msg);
                        } else if (Number(data.status) === 0) {
                            // uuap超时跨域访问问题
                        } else {
                            msg('操作异常');
                        }
                    }
                }
            } else {
                msg('操作异常');
            }
           // msg('error: ' + textStatus + '\n' + errorThrown);
        },
        success: function (theData) {
            if (callbackFunctionStr != null && callbackFunctionStr !== '') {
                var funcName = callbackFunctionStr + '(' + objToJsonString(theData) + ')';
                eval(funcName);
            }
        }
    });
}

// 将 form 里的所有 input 序列化，作为提交表单时的参数
// this function returns a json object of the inputs of a form
function serializeForm(formId) {
    var param = {};
    var elements = document.forms[formId];   // msg( elements == null )

    for (var i = 0; i < elements.length; i++) {
        if (elements[i].name !== '') {
            if (elements[i].id !== 'file') {   // do not submit the input of type 'file'
                var trimmedValue = trim(elements[i].value);
                param[elements[i].name] = trimmedValue;  // trim the input value before submit
                elements[i].value = trimmedValue;    // set the trimmed value back into the input
            }
        }
    }
    return param;
}

// 判断 obj 是否为 undefined 或 null 或 trim 后，是否是空字符串 或者 是不是 NaN
function isNull(obj) {
    if (typeof obj === 'number') {  // number 类的 NaN 也返回 true
        if (isNaN(parseInt(obj, 10)) || isNaN(parseFloat(obj))) {
            return true;
        }
    } else if (typeof obj === 'undefined' || obj == null || trim(obj) === '') {
        return true;
    } else {
        return false;
    }
}

// 校验字符串不能为空   validate empty string
function validateEmptyStr(value) {
    if (value === '') {
        return false;
    } else {
        return true;    // 不返回 true ，这个函数得到的返回值就是 undefined
    }
}

// 校验 长度不能大于 maxSize        validate the max length of the value
function validateMaxLength(value, maxSize) {   // var result1 = validateMaxLength(value);
    if (value.length > maxSize) {
        return false;
    } else {
        return true; // 如果不返回 true  得到的方法的返回值就是 undefined
    }
}

// 验证 数字、26个英文字母 或者 下划线 组成的字符串
function validateChar(value) {
    var reg = /^\w+$/;
    if (reg.test(value)) {
        return true;
    } else {
        return false;
    }
}

// 验证 0 或 正整数
function validateZeroAndNumber(value) {
    var reg = /^(0|[1-9]\d*)$/;
    if (reg.test(value)) {
        return true;
    } else {
        return false;
    }
}

// 验证 正整数
function validateNumber(value) {
    var reg = /^\+?[1-9][0-9]*$/;
    if (reg.test(value)) {
        return true;     // 是正整数
    } else {
        return false;    // 不是正整数
    }
}

// 验证 0 或 正整数
function validateZeroOrNumber(value) {
    if (value === 0 || value === '0') {
        return true;     // 是 0 或 正整数
    }

    return validateNumber(value);
}

// 验证只包含数字的字符串
function validateNumberStr(value) {
    var reg = /^[0-9]*$/;
    if (reg.test(value)) {
        return true;     // 是只包含数字的字符串
    } else {
        return false;    // 不是只包含数字的字符串
    }
}

// 验证 正浮点数, 小数点后最多有 maxLength 位小数
function validateDouble(value, maxLength) {
    var reg = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;  // 正浮点数

    if (reg.test(value)) {  // 输入的是一个正浮点数
        value += '';
        var arr = value.split('.');
        var digit = arr[1];   // 小数部分
        var number = arr[0];   // 整数部分

        if (!isNull(digit)) {  // 如果包含小数部分
            if (digit.length <= maxLength) {  // 小数点后最多有 maxLength 位小数
                return true;
            } else {   // 小数点后多余 maxLength 位小数
                return false;
            }
        }
        return true;
    } else {  // 输入的不是正浮点数
        return false;
    }
}

// 生成列表的表头
// listTitleParam 是包含表头列名键值对的参数
function generateTableHead(listTitleParam) {
    var html = '';

    for (var key in listTitleParam) {
        if (listTitleParam.hasOwnProperty(key)) {
            html += '    <th width="100" >' + listTitleParam[key] + '</th>';
        }
    }
    return html;
}

// 生成列表，并显示这个列表
// divId 是展示列表的 div 的 id
// list 包含的是结果集的数据
// listTitleParam 是包含表头列名键值对的参数
// formatterParam 是包含格式化列的数值的方法名的键值对的参数
// checkboxParam 是 多选按钮 列的相关参数
// opColumnParam 是 功能 列的相关参数
// sumParam 是求和信息相关的参数
function generateTable(divId, list, listTitleParam, formatterParam,
                    checkboxParam, opColumnParam, sumParam) {
    var count = sumParam.count;  // 总的查询结果行数
    var page = sumParam.page;     // 总的查询结果页数
    var pageSize = sumParam.pageSize;   // 每页的行数
    var howManyPages = sumParam.howManyPages;  // 一共有多少页

    var html = '';

    // 列表上面的汇总信息
    html += '<p class="tip">一共有<span class="strip">' + count + '</span>条查询结果   ';

    var info = sumParam.info;
    if (!isNull(info)) {
        html += info;
    }
    html += '</p>';

    html += '<table align="center" border="1" cellpadding="0" cellspacing="0" class="list-table-style" >';

    // 生成列表的表头
    html += '   <tr>';
    var tempCheckboxArg = checkboxParam.checkboxArg;    // printObject(checkboxParam);
    var checkboxArgToUse = '';

    if (isNull(tempCheckboxArg) !== true && tempCheckboxArg !== null) {
        checkboxArgToUse = tempCheckboxArg;
    } else {
        checkboxArgToUse = 'id';
    }
    // msg( checkboxArgToUse )

    if (checkboxParam.needCheckbox === true) {   // printObject(checkboxParam   );
        html += '        <th align="center" width="50" >';
        html += '           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
            + '<input type=\'checkbox\'  id=\'checkAll\' '
            + ' onclick=\'checkAll()\'  /> 全选&nbsp;';
        html += '        </th>';
    }

    // 是否显示 序号 列
    if (sumParam.needSn === true) {
        html += '        <th align="center" width="50" >序号</th>';
    }

    html += generateTableHead(listTitleParam);

    // 生成 功能 那一列的表头
    if (opColumnParam !== null) {
        html += '        <th width="100" >功能</th>';
    }
    html += '   </tr>';

    // 以下 生成列表的内容
    for (var i = 0; i < list.length; i++) {   // printObject( list )
        // 隔行 变色 显示
        if (i % 2 === 0) {
            html += '<tr align="center" class="odd">';
        }

        // 是否需要在列表左侧显示多选按钮列
        if (checkboxParam.needCheckbox === true) {
            html += '    <td><input type=\'checkbox\' name=\'checkbox\' value="' + list[i][checkboxArgToUse]
                     + '" id=\'checkbox' + i + '\' onclick=\'checkAllSwitch(this)\'/></td>';
        }

        // 是否显示 序号 列
        if (sumParam.needSn === true) {   // msg( ( page - 1 ) * pageSize )
            var startRow = (page - 1) * pageSize;
            html += '    <td >' + (startRow + i + 1) + '</td>';
        }

        html += formatListContent(formatterParam, list[i], listTitleParam);

        // 生成 功能 那一列
        if (opColumnParam !== null) {    // printObject(opColumnParam)
            html += '    <td>';
            for (var item in opColumnParam) {
                if (opColumnParam.hasOwnProperty(item)) {
                    html += '<div>';
                    html += eval(opColumnParam[item] + '(' + objToJsonString(list[i]) + ')');
                    html += '</div>';
                }
            }
            html += '    </td>';
        }
        html += '</tr>';
    }
    html += '</table>';

    // 以下 生成分页条
    if (true) {
        html += '<div class="page">';
        html += '<div class="kkpager clearfix">';

        // 生成 分页按钮
        // html += '<a id="pageFirst" href=\"javascript:void(0)\" onclick=\"goToPage(\'first\')" >首页</a>';
        // html += '<a id="pagePrevious" href=\"javascript:void(0)\" onclick=\"goToPage(\'previous\')" >上一页</a>';
        // html += '<span class="curr">' + page + '</span>';
        // html += '<a id="pageNext" href=\"javascript:void(0)\" onclick=\"goToPage(\'next\')" >下一页</a>';
        // html += '<a id="pageLast" href=\"javascript:void(0)\" onclick=\"goToPage(\'last\')" >尾页</a>';
        html += generatePagerButtons(page, pageSize, howManyPages);

        html += '<span class="disabled" >共' + howManyPages + '页</span>';
        html += '<span class="disabled">每页' + pageSize + '条数据</span>';
        html += '</div>';

        html += '<div class="go-to-page-div">';
        html += '<span class="go-to-page-span">';
        html += '   <input type="button" id="pagerButton" class="go-to-page-button" onclick="goToPage( )" value="确认"/>';
        html += '   转到<input type="text" id="pagerInput" class="go-to-page-input"/>页';
        html += '</span></div>';
        html += '</div>';

        // 保存分页参数
        html += '<input type="hidden" id="page" value=' + page + ' >';
        html += '<input type="hidden" id="pageSize"  value=' + pageSize + ' >';
        html += '<input type="hidden" id="howManyPages" value=' + howManyPages + '>';
    }

    // 展示列表和分页条
    jqGet(divId).html(html);
}

// page是当前的页数   pageSize是每页的行数     howManyPages是总页数
function generatePagerButtons(page, pageSize, howManyPages) {
    var html = '';

    page = parseInt(page, 10);
    pageSize = parseInt(pageSize, 10);
    howManyPages = parseInt(howManyPages, 10);

    // 首页
    if (page === 1) {
        html += '<a id="pageFirst" class="ui-page-no">首页</a>';
    } else {
        html += '<a id="pageFirst" class=\'ui-page-next\' href=\"javascript:void(0)\" '
            + 'onclick=\"goToPage(\'first\')\">首页</a>';
    }

    // 上一页
    if (page === 1) {
        html += '<a id="pagePrevious" class="ui-page-no" >上一页</a>';
    } else {
        html += '<a id="pagePrevious" class=\'ui-page-next\' href=\"javascript:void(0)\" '
            + 'onclick=\"goToPage(\'previous\')" >上一页</a>';
    }

    // 第 1 页
    if (howManyPages === 1) {
        html += '<a class="curr" >1</a>';  // class="ui-page-no"
    } else if (page === 1) {
        html += '<a class="curr" class="click">1</a>';

        // 第2页  至  第5页
        for (var i = 2; i <= (howManyPages < 5 ? howManyPages : 5); i++) {
            html += '<a class=\'ui-page-next\' href=\"javascript:void(0)\" onclick=\"goToPage( ' + i + ' )\" >'
                + i + '</a>';
        }
    } else if (page === howManyPages) {
        for (var i = (howManyPages < 5 ? 1 : (howManyPages - 4)); i < page; i++) {
            html += '<a class=\'ui-page-next\' href=\"javascript:void(0)\" onclick=\"goToPage( ' + i + ' )\" >'
                + i + '</a>';
        }
        html += '<a class="curr" class="click">' + page + '</a>';
    } else if (page === 2) {
        html += '<a class=\'ui-page-next\' href=\"javascript:void(0)\" onclick=\"goToPage( 1 )\" >1</a>';
        html += '<a class="curr" class="click">' + page + '</a>';
        for (var i = page + 1; i <= (page + 3 > howManyPages ? howManyPages : (page + 3)); i++) {
            html += '<a class=\'ui-page-next\' href=\"javascript:void(0)\" onclick=\"goToPage( ' + i + ' )\" >'
                + i + '</a>';
        }
    } else if (page === (howManyPages - 1)) {
        for (var i = (page - 3 > 0 ? (page - 3) : 1); i < page; i++) {
            html += '<a class=\'ui-page-next\' href=\"javascript:void(0)\" onclick=\"goToPage( ' + i + ' )\" >'
                + i + '</a>';
        }
        html += '<a class="curr" class="click">' + page + '</a>';
        var number = page + 1;
        html += '<a class=\'ui-page-next\' href=\"javascript:void(0)\" onclick=\"goToPage( ' + number + ' )\" >'
            + number + '</a>';
    } else {
        for (var i = (page - 2 > 0 ? (page - 2) : 1); i < page; i++) {
            html += '<a class=\'ui-page-next\' href=\"javascript:void(0)\" onclick=\"goToPage( ' + i + ' )\" >'
                + i + '</a>';
        }
        html += '<a  class="curr" class="click">' + page + '</a>';
        for (var i = page + 1; i <= (page + 2 > howManyPages ? howManyPages : (page + 2)); i++) {
            html += '<a class=\'ui-page-next\' href=\"javascript:void(0)\" onclick=\"goToPage( ' + i + ' )\" >'
                + i + '</a>';
        }
    }

    // 下一页
    if (page === howManyPages) {
        html += '<a id="pageNext" class="ui-page-no">下一页</a>';
    } else {
        html += '<a id="pageNext" class=\'ui-page-next\' href=\"javascript:void(0)\" '
            + 'onclick=\"goToPage(\'next\')" >下一页</a>';
    }

    // 末页
    if (page === howManyPages) {
        html += '<a id="pageLast" class="ui-page-no">尾页</a>';
    } else {
        html += '<a id="pageLast" class=\'ui-page-next\' href=\"javascript:void(0)\" '
            + 'onclick=\"goToPage(\'last\')" >尾页</a>';
    }

    return html;
}

// 设置 分页条，分页的输入框和跳转按钮
function setupPager(sumParam) {
    var count = sumParam.count;  // 总的查询结果行数
    var page = sumParam.page;     // 总的查询结果页数
    var pageSize = sumParam.pageSize;   // 每页的行数
    var howManyPages = sumParam.howManyPages;  // 一共有多少页

    if (page === 1) {   // 当前页是 第一页
        jsGet('pageFirst').removeAttribute('href');
        jsGet('pageFirst').removeAttribute('onclick');
        jsGet('pagePrevious').removeAttribute('href');
        jsGet('pagePrevious').removeAttribute('onclick');
    } else {
        jsGet('pageFirst').href = 'javascript:void(0)';
        jsGet('pageFirst').setAttribute('onclick', 'goToPage("first")');
        jsGet('pagePrevious').href = 'javascript:void(0)';
        jsGet('pagePrevious').setAttribute('onclick', 'goToPage("previous")');
    }

    if (page === howManyPages) {   // 当前页是 最后一页
        jsGet('pageNext').removeAttribute('href');
        jsGet('pageNext').removeAttribute('onclick');
        jsGet('pageLast').removeAttribute('href');
        jsGet('pageLast').removeAttribute('onclick');
    } else {
        jsGet('pageNext').href = 'javascript:void(0)';
        jsGet('pageNext').setAttribute('onclick', 'goToPage("next")');
        jsGet('pageLast').href = 'javascript:void(0)';
        jsGet('pageLast').setAttribute('onclick', 'goToPage("last")');
    }

    // 输入框获得焦点时，显示跳转按钮
    jqGet('pagerInput').focus(function () {
        jqGet('pagerButton').show();
        jqGet('pagerButton').css('left', '70px');
    });

    // 页面获得焦点时，隐藏跳转按钮
    $(document).on('click', function () {
        jqGet('pagerButton').hide();
    });

    // document绑定了事件，去掉 pagerInput 绑定的这个事件
    $('#pagerInput').on('click', function () {
        return false;
    });
}

// 翻页时，更新页面里的分页参数，然后提交分页查询
function setupPagerAndSubmit(whichPage, submitFunctionNameStr) {
    // 将 字符串 转换为 数字，因为后面需要计算和比较
    var currentPage = parseInt(jqGet('page').val(), 10);
    var howManyPages = parseInt(jqGet('howManyPages').val(), 10);
    var pageSize = parseInt(jqGet('pageSize').val(), 10);
    var inputValue = parseInt(jqGet('pagerInput').val().trim(), 10);
    // msg(currentPage+"  "+howManyPages+"  "+pageSize+"  "+inputValue+">>"+ whichPage);

    // 点击 分页输入框，输入页数点击确定按钮 后，提交的翻页操作
    if (isNull(whichPage)) {    // msg(isNull(inputValue))
        jqGet('pagerInput').val('');

        if (isNull(inputValue)) {  // 分页输入框输入的是 空字符串或空格
            // msg('跳转到的页数应该是一个大于 0 的数字');
            return;
        } else {
            if (!validateNumber(inputValue)) {   // 分页输入框输入的不是正整数
                return;
            } else {  // 分页输入框输入的是一个正整数，提交翻页操作
                if (inputValue === currentPage) {
                    return;
                }

                if (inputValue > howManyPages) {
                    inputValue = howManyPages;
                }

                jsGet('page').value = inputValue;  // 更新页面里的分页参数
            }
        }
    } else {  // 点击分页上一页，下一页，首页，尾页，或页数 链接，提交翻页操作
        if (whichPage === 'first') {
            jsGet('page').value = 1;     // 更新页面里的分页参数
        } else if (whichPage === 'last') {
            jsGet('page').value = howManyPages;
        } else if (whichPage === 'next') {
            jsGet('page').value = currentPage + 1;
        } else if (whichPage === 'previous') {
            jsGet('page').value = currentPage - 1;
        } else {    // 点击分页条中的 页数链接，提交翻页操作
            jsGet('page').value = parseInt(whichPage, 10);
        }
    }

    // 提交分页查询
    // 参数 true 表示这是分页查询
    eval(submitFunctionNameStr + '(true)');
}

// 全选列表左侧的多选按钮
function checkAll() {   // msg( jsGet( 'checkAll' ).checked +' ' + jqGet('checkAll').prop('checked') );
    if (jqGet('checkAll').prop('checked') === true) {
        $('input[name="checkbox"]').each(function () { // input[type='checkbox']
            $(this).prop('checked', true);
        });
    } else {
        $('input[name="checkbox"]').each(function () {
            $(this).prop('checked', false);
        });
    }
}

// 判断全选按钮是否需要被选中，并进行相应的切换
function checkAllSwitch(obj) {
    var statusOfThis = obj.checked;

    if (statusOfThis === false) {
        jsGet('checkAll').checked = false;
    } else {
        var flag = true;

        var checkboxName = 'checkbox';    // 全选按钮的 name 属性
        var arr = document.getElementsByName(checkboxName);

        for (var i = 0; i < arr.length; i++) {
            if (arr[i].checked === false) {
                flag = false;
            }
        }

        jsGet('checkAll').checked = flag;
    }
}

// 返回 checkbox 数组 里选中的 checkbox 的值的字符串，格式是 value1,value2,value3,value4
function getCheckedStr(checkboxItems) {
    var result = '';

    if (isNull(checkboxItems)) {
        return result;
    }

    for (var i = 0; i < checkboxItems.length; i++) {
        if (checkboxItems[i].checked === true) {
            result += checkboxItems[i].value + ',';
        }
    }
    result = result.substring(0, result.lastIndexOf(','));  // alert(result)

    return result;
}

// 返回到上一个页面
function returnToFormerPage() {
    window.history.back(-1);   // or history.go(-1);
}

// 格式化 列表中每个单元格的内容
function formatListContent(formatterParam, listContent, listTitleParam) { // printObject( listContent  )
    var html = '';

    for (var key in listTitleParam) {
        if (listTitleParam.hasOwnProperty(key)) {
            var value = listContent[key];      // printObject( listContent );
            var formatterName = formatterParam[key];

            if (isNull(formatterName)) {
                // listTitleParam 的 key 对应的值如果是 null，在 listContent 里，就没有对应的键值对
                // 这种情况要显示一个 "—"
                if (isNull(value)) {
                    value = '—';
                }

                html += '    <td>' + value + '</td>';
            } else {
                var result = eval(formatterName + '( value )');
                html += '    <td>' + result + '</td>';
            }
        }
    }
    return html;
}

// 格式化 资产计划状态
function planStatusFormatter(value) {
    if (value === 0) {
        return '待发行';
    } else if (value === 1) {
        return '<font color=\"blue\">已发行</font>';
    } else if (value === 2) {
        return '<font color=\"red\">撤销</font>';
    }
}

// 格式化 日期和时间    输出格式为  yyyy-mm-dd HH:mm:ss
function dateTimeFormatter(date) {   // printObject( date  )
    var flag = isNull(date);

    if (flag !== true && date !== null) {      //  msg( date.time )
        var dateInput = new Date(date.time);    // printObject( dateInput )

        var year = dateInput.getFullYear();
        var month = dateInput.getMonth() + 1;
        var date = dateInput.getDate();
        var hour = dateInput.getHours();
        var minute = dateInput.getMinutes();
        var second = dateInput.getSeconds();

        if (month < 10) {
            month = '0' + month;
        }

        if (date < 10) {
            date = '0' + date;
        }

        if (hour < 10) {
            hour = '0' + hour;
        }

        if (minute < 10) {
            minute = '0' + minute;
        }

        if (second < 10) {
            second = '0' + second;
        }

        return year + '-' + month + '-' + date + '  ' + hour + ':' + minute + ':' + second;
    } else {
        return '';
    }
}

// 格式化 日期   输出格式为  yyyy-mm-dd
function dateFormatter(date) {    // msg( date )
    if (isNull(date)) {
        return '—';
    } else {    //  msg( date.time )
        var dateInput = new Date(date.time); // printObject( dateInput )

        var year = dateInput.getFullYear();
        var month = dateInput.getMonth() + 1;
        var date = dateInput.getDate();

        if (month < 10) {
            month = '0' + month;
        }

        if (date < 10) {
            date = '0' + date;
        }
        return year + '-' + month + '-' + date;
    }
}

// 格式化 产品类型
function productTypeFormatter(type) {
    if (isNull(type)) {
        type = '';
    }
    type = type.substring(0, 3);    // msg(type);

    if (type === 'DXJ') {
        return '度学金';
    } else if (type === 'DLQ') {
        return '度零钱';
    } else if (type === 'QNR') {
        return '去哪儿';
    } else if (type === 'BTB') {
        return '度贴吧';
    } else {
        return '其他';
    }
}

// 格式化  资产明细的 还款状态
function repayStatusFormatter(status) {  // msg(status)
    if (status === 'N') {
        return '正常';
    } else if (status === 'Y') {
        return '结清';
    } else if (status === 'O') {
        return '逾期';
    } else {
        return status;
    }
}

// 格式化 资产分期明细的 还款状态
function repaymentStatusFormatter(status) {
    if (status === 'N') {
        return '正常(已过账单日，未逾期)';
    } else if (status === 'Y') {
        return '结清';
    } else if (status === 'O') {
        return '逾期';
    } else if (status === 'U') {
        return '未到期(未到账单日)';
    } else {
        return status;
    }
}

// 格式化 还款计划是否变更
function repayPlanChangeFormatter(status) {
    if (status === 1) {
        return '是';
    } else if (status === 2) {
        return '否';
    } else {
        return '—';
    }
}

// 格式化 是否贴息
function isDiscountFormatter(status) {   //  msg( status)
    if (status === 1) {
        return '不贴息';
    } else if (status === 0) {
        return '贴息';
    } else {
        return '—';
    }
}

// 格式化 性别   0=女  1=男
function genderFormatter(value) {   //  msg(value)
    if (value === 1) {
        return '男';
    } else if (value === 0) {
        return '女';
    } else {
        return '—';
    }
}

// 格式化 null 和 undefined 和 '' ，输出 defaultValue
function nullFormatter(value, defaultValue) {
    if (isNull(value)) {
        return defaultValue;
    } else {
        return value;
    }
}

// 格式化 空字符串
function emptyStrFormatter(str) {
    return nullFormatter(str, '—');
}

// 格式化 转让状态
function transferStatusFormatter(status) {
    if (status === 0) {
        return '未转让';
    } else if (status === 1) {
        return '待转让';
    } else if (status === 2) {
        return '已转让';
    } else {
        return '—';
    }
}

// 比较两个时间字符串的大小   格式 yyyy-mm-dd HH:mm:ss
function compareTimeAWithTimeB(strA, strB) {   // msg(strA+"   "+strB )
    var dateA = new Date(strA.replace(/-/g, '/'));
    var dateATimestamp = dateA.getTime();     // 获得 timestamp

    var dateB = new Date(strB.replace(/-/g, '/'));
    var dateBTimestamp = dateB.getTime();   // 获得 timestamp

    if (dateATimestamp > dateBTimestamp) {  // strA 大于 strB
        return 1;
    } else if (dateATimestamp === dateBTimestamp) {  // strA 等于 strB
        return 0;
    } else if (dateATimestamp < dateBTimestamp) {   // strA 小于 strB
        return -1;
    }
}

// 获得今天的日期对应的字符串
function getDateStrOfToday() {
    return jsDateObjectFormatter(new Date());
}

// 格式化 js 的日期对象   输出格式 yyyy-mm-dd
function jsDateObjectFormatter(date) {   // msg( typeof date )
    var flag = isNull(date);

    if (flag !== true && date !== null) {      //  msg( date.time )
        var dateInput = date;          // printObject( dateInput )

        var year = dateInput.getFullYear();
        var month = dateInput.getMonth() + 1;
        var date = dateInput.getDate();

        if (month < 10) {
            month = '0' + month;
        }

        if (date < 10) {
            date = '0' + date;
        }
        return year + '-' + month + '-' + date;
    } else {
        return '';
    }
}

// 格式化 还款方式
function repayTypeFormatter(value) {
    if (value === 'PAYMENT01') {
        return '等额本金';
    } else if (value === 'PAYMENT02') {
        return '等额本息';
    } else if (value === 'PAYMENT03') {
        return '每期还息费，到期还本';
    } else if (value === 'PAYMENT12') {
        return '延期2月等额本金';
    } else if (value === 'PAYMENT13') {
        return '延期3月等额本金';
    } else if (value === 'PAYMENT16') {
        return '延期6月等额本金';
    } else if (value === 'PAYMENT17') {
        return '延期4月等额本金';
    } else if (value === '4P12P00') {
        return '前4个月还4%的本金，后12个月还96%的本金';
    } else if (value === '6012P07') {
        return '前6个月还利息，后12个月还本金和利息';
    } else if (value === '6P12P00') {
        return '前6个月还6%的本金，后12个月还94%的本金';
    } else if (value === '6P12P1288') {
        return '前6个月还12%的本金，后12个月还88%的本金';
    } else if (value === '6PI12PI017') {
        return '前6个月还1.98%的本金和利息，后12个月还98.02%的本金和利息';
    } else if (value === '6PI12PI09') {
        return '前6个月还3.6%的本金和利息，后12个月还96.4%的本金和利息';
    } else if (value === '4P12P00') {
        return '前4个月还4%的本金，后12个月还96%的本金';
    } else if (value === '6012P07') {
        return '前6个月还利息，后12个月还本金和利息';
    } else if (value === '6P12P00') {
        return '前6个月还6%的本金，后12个月还94%的本金';
    } else if (value === '6P12P1288') {
        return '前6个月还12%的本金，后12个月还88%的本金';
    } else if (value === '6PI12PI017') {
        return '前6个月还1.98%的本金和利息，后12个月还98.02%的本金和利息';
    } else if (value === '6PI12PI09') {
        return '前6个月还3.6%的本金和利息，后12个月还96.4%的本金和利息';
    } else if (value === '4P12P0892') {
        return '前4个月还8%的本金，后12个月还92%的本金';
    } else if (value === '6P12P088') {
        return '前6个月还1.8%的本金，后12个月还98.2%的本金';
    } else {
        return '其他';
    }

    // 格式化 assetManager 状态
    function assetManagerStatusFormatter(status) {
        if (status === 1) {
            return '<font color="red">已删除</font>';
        } else if (status === 2) {
            return '正常';
        }
    }

}