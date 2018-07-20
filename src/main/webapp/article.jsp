<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/10
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="datagrid">
</table>
<div id="del"></div>

<!-- 提交按钮 -->
<div id="tool" style="display: none">
    <a class="easyui-linkbutton" href="javascript:void(0)" id="sub"
       data-options="iconCls:'icon-ok',onClick:upsubm">提交</a> <a
        class="easyui-linkbutton" href="javascript:void(0)" id="remove"
        data-options="iconCls:'icon-remove',onClick:remove">重置</a>
</div>


<script type="text/javascript">
$(function(){


    //定义数据表格
    $('#datagrid').datagrid({
        loadMsg : "Loading..",//远程加载展示字段
        showFooter : true,
        fitColumns:true,//自动展开/收缩列的大小，以适应网格的宽度
        //title : 'PictMessages',//标题
        striped : true,//展示斑马线
        remoteSort : false,
        ctrlSelect:true,//可多选
        rownumbers : true,// 显示行号
        selectOnCheck : true,//选中行是否可以选中框
        checkOnSelect:true,//如果为true，当用户点击行的时候该复选框就会被选中或取消选中。
        //如果为false，当用户仅在点击该复选框的时候才会呗选中或取消
        //singleSelect : false,//如果为true，则只允许选择一行
        columns : [ [  {
            field : 'ckbox',
            checkbox : true,
        },
            {
                field : 'aid',
                title : '编号',
                width : 100,
                height:100,
                align : 'center',
                sortable : true,//配合remoteSort:false使用
            },

            {
                field : 'title',
                title : '文章描述',
                width : 100,
                height:100,
                align : 'center',
            },

            {
                field : 'publishdate',
                title : '上传时间',
                width : 100,
                height:100,
                align : 'center',

            },

            {
              field:'newdate',
              title:'修改时间',
              width:100,
              height:100,
              align:'center',
            },

            {
                field:'pstatus',
                title:'状态',
                width:100,
                height:100,
                align:'center'
            },

            {
                field :'master.id',
                title : '上师',
                width : 100,
                height:100,
                align : 'center',
                formatter:function(val,row,index){
                    return "<img src='${pageContext.request.contextPath}/"+val+"' style='width:200px;height:100px'/>"
                }
            },

            {
                field:'pp',
                title:'操作',
                width:100,
                height:100,
                align:'center',
                formatter:function(val,row,index){
                    //在这里由于无法展示easyui样式,所以需要把删除按钮重新定义样式
                    return '<a href="javascript:(void(0))" onclick="deleteImg('+row.pid+')">删除</a>';
                },

            },
        ]
        ],
        //加入分页控件
        pagination : true,//底部展示工具栏
        pagePosition : 'bottom',//分页栏的位置
        pageNumber : 1,//默认展示第几页
        pageSize : 5,//默认每页展示的条数
        pageList : [ 5, 10, 15, 20 ],//可选择每页展示数据

        onLoadSuccess:function () {//在数据加载成功的时候触发
            $('.del').linkbutton({
                text:'Delete',
                iconCls:'icon-remove',
            })
        },
        url : '${pageContext.request.contextPath}/article/queryAllArticle.do',//查询所有轮播信息
        toolbar:[
            {
                text:'Update',
                iconCls:'icon-edit',
                handler:function () {//回调函数
                    $('#del').dialog({
                        width:500,
                        height:400,
                        title:'update messages',
                        collapsible:true,
                        draggable:true,
                        buttons:'#tool',//页脚工具栏
                    });
                }
            },
            {
                text:'Add',
                iconCls:'icon-add',
                handler:function () {
                    $('#dg').dialog({
                        width:600,
                        height:320,
                        title:'add messages',
                        collapsible:true,
                        draggable:true,
                        buttons:'#tool'
                    });
                    $('#fom').form({//初始化表单插件
                        url:'${pageContext.request.contextPath}/pict/uploadOneImager.do',
                        success:function (data) {
                            $('#imgs').prop('src','${pageContext.request.contextPath}/img/'+data);
                            $('#btn').prop('value',data)//给隐藏域赋值
                            $.messager.show({
                                width:200,
                                height:100,
                                title:'我的消息',
                                msg:'头像上传成功',//显示的消息文本
                                timeout:2000,//如果定义为0，消息窗体将不会自动关闭，除非用户关闭他。如果定义成非0的树，消息窗体将在超时后自动关闭。默认：4秒。
                                showType:'fade' //定义将如何显示该消息
                            });

                        }
                    });
                    $('#fom1').form({//初始化表单插件
                        url:'${pageContext.request.contextPath}/pict/addPict.do',
                        success: function () {
                            $('#dg').dialog('close');
                            $('#datagrid').datagrid('reload')
                        }
                    });
                }
            }
        ]
    });
});
//清空表单的数据
function remove() {
    $('#fom1').form('clear');
}

//表单提交
function upsubm2() {
    $('#fom').submit();
}

//上传轮播图
function upsubm(){
    alert('upsubm')
    $('#fom1').submit();//提交表单
}
function deleteImg(pid){
    var row =$('#datagrid').datagrid('getSelected');
    alert('pid     ='+pid)
    if(row){
        $.messager.confirm('confirm', 'querydelete', function(r){
            if (r){
                $.post('${pageContext.request.contextPath}/pict/deletePict.do',{pid:row.pid},function(result){
                   alert('Join result=    '+result);
                    if(result){
                        $.messager.show({
                            title:'success',
                            msg:result
                        });
                        $('#datagrid').datagrid('reload');
                    }else{
                        $.messager.show({
                            title:'error',
                            msg:result.err,
                        });

                    }
                },'json');
            }
        });
    }
}


</script>
<!--添加轮播信息和上传头像-->
<div style="display:none;padding:30px ;background: #bababa" id="dg">

    <div style="float: left">
        <div style="border: #fff788 solid 1px;width: 200px;height: 100px">
            <img style='width: 200px;height:100px;' src="${pageContext.request.contextPath}/img/header_logo.gif" id="imgs"/>
        </div>
        <div style="margin-top: 10px;">
            <form id="fom" method="post" enctype="multipart/form-data">
                <input class="easyui-filebox" name="srcFile" style="width:150px">
                <a href="javascript:void(0)" class="easyui-linkbutton" style="float: right"
                   data-options="iconCls:'icon-add',onClick:upsubm2">提交图像</a>
            </form>
        </div>
    </div>

    <div style="float: right;padding-top: auto">
        <form id="fom1">
            <!--隐藏属性-->
            <input id="btn" type="hidden" name="srcFiles" value="dog.jpg"/>
            <br/>
            <div style="margin-top: 10px;">
                轮播图描述:<input name="por" class="easyui-ValidateBox" /><br/>
            </div>
            <hr/>
            <br/>
            <div style="margin-top: 10px;">
                轮播图状态:<select id="status" class="easyui-combobox" name="pstatus" style="width:200px;height:30px">
                <option value="1">展示</option>
                <option value="0">不展示</option>
            </select>
                <br/>
            </div>
        </form>
    </div>
</div>
<!--结束-->
</body>
</html>
