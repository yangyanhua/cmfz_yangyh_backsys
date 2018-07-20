<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/easyui-lang-zh_CN.js"></script>


</head>
<body>
<script>
    $('#cc').combotree({
        url: '${pageContext.request.contextPath}/userFiledsData.json',
        required: true,
        checkbox:true,
        onlyLeafCheck:true,
        multiple:true,
    });
    $("#btn").click(function () {
        //获取combotree的选中信息
        var content = $("#cc").combotree("getText");
        console.log(content);
        var vals = $("#cc").combotree("getValues");
        var ss="";
        $.each(vals,function (index,filed) {
            if(index<vals.length-1){
                ss += filed+",";
            }else{
                ss += filed;
            }

        })
        console.log(ss);
        //提交form表单
        $("#fff").form("submit",{
            url:"${pageContext.request.contextPath}/poi/export.do",
            queryParams:{"content":content,"fields":ss}
        });
    });

    $('#userdg').datagrid({
        toolbar: [{
            iconCls: 'icon-edit',
            text:"自定义导出",
            handler: function(){
                $("#dd").dialog({
                    closed:false,
                })
            }
        },'-',{
            text:"批量导入用户",
            iconCls: 'icon-help',
            handler: function(){alert('批量导入用户')}
        },'-',{
            text:"用户模板",
            iconCls: 'icon-help',
            handler: function(){alert('批量导入用户')}
        }]
    });



</script>
<table id="userdg" class="easyui-datagrid">

</table>

<div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="fff" method="post">
        <select id="cc" class="easyui-combotree" style="width:200px;"
                data-options="url:'get_data.php',required:true"></select>
    </form>
    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提交</a>
</div>
</body>
</html>
