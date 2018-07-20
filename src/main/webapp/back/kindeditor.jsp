<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>


        <script charset="utf-8" src="kindeditor/kindeditor-all.js"></script>
        <script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
        <script>
            KindEditor.create('#editor_id', {
                    width : '1000px',
                    //图片上传功能
                    //{"error":0,"url":"\/ke4\/attached\/W020091124524510014093.jpg"}
                    uploadJson:"${pageContext.request.contextPath}/editor/add.do",
                    //查询服务器上所有上传过的图片
                    //
                    fileManagerJson:"${pageContext.request.contextPath}/editor/browser.do",
                    allowFileManager:true,
                    filePostName:"file",
                });
        </script>

    <form enctype="text/plain" action="${pageContext.request.contextPath}/editor/upload.do">
        <textarea id="editor_id" name="content" style="width:700px;height:300px;">&lt;strong&gt;HTML内容&lt;/strong&gt;</textarea>
        <input type="submit" />
    </form>

