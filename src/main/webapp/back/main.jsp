<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
    <%--需要easyui相关文件--%>
    <head>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/icon.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/default/easyui.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/IconExtension.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript">
            /*创建菜单栏*/
                $(function(){
                    //加载数据库中的菜单信息
                    $.post('${pageContext.request.contextPath}/back/menu.json',function(menus){
                        //menus是一个一级菜单集合  每一个一级菜单都是手风琴的面板
                            $.each(menus,function (i,menu) {
                                //让面板的content展示二级菜单内容
                                  var content = "";
                                $.each(menu.children,function (i,child) {
                                    //构建我们的content
                                    content+="<div class=\"easyui-linkbutton\" onclick=\"addTab('"+child.name+"','"+child.iconCls+"','"+child.href+"')\" data-options=\"iconCls:'"+child.iconCls+"'\" style=\"border:1px solid green;width:90%;margin:5 5 5 5\">"+child.name+"</div>";
                                })
                                //添加面板
                                $("#m").accordion("add",{
                                    title:menu.name,
                                    iconCls:menu.iconCls,
                                    content:content
                                })
                            })
                    },"JSON");
                  /* $.post("${pageContext.request.contextPath}/back/menu.json",function(data){
                        var content="";
                        for(index in data){
                            content="<center>";
                            $.each(data[index].menus,function(i,n){
                                content +='<a class=\"easyui-linkbutton \" data-options=\"iconCls:\''+n.iconCls+'\',text:\''+n.title+'\'\" onClick=\"addTab(\''+n.title+'\',\''+n.iconCls+'\',\''+n.href+'\')\" '+
                                    ' style=\"width: 90%;height: 35px;margin-top:4px;margin-bottom:2px;border: 1px solid green;border-radius:10px\"></a> ';
                            });
                            content +="</center>";
                            $("#m").accordion("add",{
                                title:data[index].title,
                                multiple:false,
                                iconCls:data[index].iconCls,
                                selected:false,
                                content:content
                            });
                        };
                    });*/
                });
                function addTab(title,iconCls,href){
                    //判断面板是否存在
                    if(!$("#tt").tabs('exists',title)){
                        //创建新的tab
                        $("#tt").tabs('add',{
                            title:title,
                            iconCls:iconCls,
                            href:"${pageContext.request.contextPath}/"+href,
                            closable:true
                        })
                    }else{
                        $("#tt").tabs('select',title);
                    }
                }
        </script>
    </head>
    <body class="easyui-layout">
        <div data-options="region:'north',split:true" style="height:60px;background-color:#5C160C ">
            <div style="font-size: 24px;color: white;font-family: 楷体;float: left;padding-top: 10px;padding-left: 20px">驰名法洲后台管理系统</div>
            <div style="font-size: 24px;color: white;font-family: 楷体;float: right;padding-top: 10px;padding-right: 20px">欢迎您,XXXX
                <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >修改密码</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >退出系统</a>
            </div>
        </div>
        <div data-options="region:'south',split:true" style="height:40px;background-color:#5C160C ">
            <div style="text-align: center;font-family: 楷体;color: white;font-size: 15px">&copy; 百知教育 yuxb@zparkhr.com.cn</div>
        </div>
        <div data-options="region:'west',title:'导航菜单',split:true" style="width:200px;">
            <div id="m" class="easyui-accordion" data-options="fit:true"></div>
        </div>
        <div data-options="region:'center'" >
            <div id="tt" class="easyui-tabs" data-options="fit:true,pill:true,narrow:true" style="width:500px;height:250px;">
                <div title="主页" data-options="iconCls:'icon-house'"></div>
            </div>
        </div>
    </body>
</html>
