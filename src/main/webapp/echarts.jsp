<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
    <head>

        <script charset="utf-8" src="${pageContext.request.contextPath}/back/static/jquery.min.js"></script>
            <%--第一步:引入ecahrts.js文件--%>
        <script charset="utf-8" src="${pageContext.request.contextPath}/back/echarts/echarts.js"></script>
    </head>
    <body>
        <!-- 第二步:为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="main" style="width: 600px;height:400px;"></div>
        <script>
            /*初始化init echarts表格*/
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            //发送ajax查询数据
            $.ajax({
                url:"${pageContext.request.contextPath}/back/echats1.json",
                dataType:"JSON",
                type:"get",
                success:function (result) {
                    //设置当前echarts表格的配置
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: 'ECharts 入门示例'
                        },
                        tooltip: {},
                        legend: {
                            data:['销量']
                        },
                       /* xAxis: {
                            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                        },
                        yAxis: {},*/
                        series: [{
                            name: '销量',
                            type: 'map',
                            data: result.sales.data
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            });


        </script>
    </body>
</html>



