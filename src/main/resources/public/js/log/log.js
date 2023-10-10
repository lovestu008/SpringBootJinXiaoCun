layui.use(['laydate','table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        table = layui.table;
    laydate.render({
        elem: '#startTime'
    });
    laydate.render({
        elem: '#endTime'
    });
    /**
     * 列表展示
     */
    var tableIns = table.render({
        elem: '#logList', // 表格绑定的ID
        url : ctx + '/log/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分⻚
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "logListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'日志编号',fixed:"true"},
            {field: 'content', title: '日志内容',align:"center"},
            {field: 'time', title: '操作时间', align:'center'},
            {field: 'type', title: '操作类型', align:'center'},
            {field: 'uname', title: '操作用户', align:'center'},
        ]]
    });

    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        tableIns.reload({
            where: { //设定异步数据接⼝的额外参数，任意设
                type: $("[name='type']").val(), // 操作类型
                uname: $("[name='uname']").val(), // 登录⼈
                startTime: $("[name='startTime']").val(),// 时间
                endTime: $("[name='endTime']").val()
            }
            ,page: {
                curr: 1 // 重新从第 1 ⻚开始
            }
        });// 只重载数据
    });
    /**
     * 头部工具栏，监听事件
     */
    table.on('toolbar(log)', function (data) {
        // data.event：对应的元素上设置的lay-event属性值
        // console.log(data);
        // 判断对应的事件类型
        if (data.event == "del") {
            // 删除操作
            deleteLog(data);
        }
    })
    /**
     * 删除日志数据
     * @param data
     */
    function deleteLog(data) {
        // 获取数据表格选中的行数据   table.checkStatus('数据表格的ID属性值');
        var checkStatus = table.checkStatus("logListTable");
        console.log(checkStatus);
        // 获取所有被选中的记录对应的数据
        var logData = checkStatus.data;
        // 判断用户是否选择的记录 (选中行的数量大于0)
        if(logData.length < 1){
            layer.msg("请选择要删除的记录");
            return;
        }
        //询问用户是否删除
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            //关闭确认框
            layer.close(index);
            var ids = "ids=";
            //遍历获取对应的id
            for (var i = 0;i<logData.length;i++){
                if (i<logData.length-1){
                    ids = ids+logData[i].id + "&ids=";
                }else {
                    ids = ids +logData[i].id;
                }
            }
            //发送ajax请求删除记录
            $.ajax({
                type:"post",
                url:ctx+"/log/delete",
                data:ids,
                success:function (result) {
                    console.log(result.code);
                    if (result.code == 200) {
                        // 提示成功
                        layer.msg("删除成功！",{icon:6});
                        // 刷新表格
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
        });
    }

});

