layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 营销机会列表展示
     */
    var tableIns = table.render({
        elem: '#saleChanceList', // 表格绑定的ID
        url : ctx + '/sale_chance/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分⻚
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "saleChanceListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'chanceSource', title: '机会来源',align:"center"},
            {field: 'customerName', title: '客户名称', align:'center'},
            {field: 'cgjl', title: '成功⼏率', align:'center'},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'linkMan', title: '联系⼈', align:'center'},
            {field: 'linkPhone', title: '联系电话', align:'center'},
            {field: 'description', title: '描述', align:'center'},
            {field: 'createMan', title: '创建⼈', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'uname', title: '指派⼈', align:'center'},
            {field: 'assignTime', title: '分配时间', align:'center'},
            {field: 'state', title: '分配状态', align:'center',templet:function(d){
                    return formatterState(d.state);
                }},
            {field: 'devResult', title: '开发状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center",
                minWidth:150}
        ]]
    });
    /**
     * 格式化分配状态
     * 0 - 未分配
     * 1 - 已分配
     * 其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterState(state){
        if(state==0) {
            return "<div style='color: #c5c576'>未分配</div>";
        } else if(state==1) {
            return "<div style='color: green'>已分配</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }
    }
    /**
     * 格式化开发状态
     * 0 - 未开发
     * 1 - 开发中
     * 2 - 开发成功
     * 3 - 开发失败
     * @param value
     * @returns {string}
     */
    function formatterDevResult(value){
        if(value == 0) {
            return "<div style='color: #bebe4b'>未开发</div>";
        } else if(value==1) {
            return "<div style='color: #00FF00;'>开发中</div>";
        } else if(value==2) {
            return "<div style='color: #00B83F'>开发成功</div>";
        } else if(value==3) {
            return "<div style='color: red'>开发失败</div>";
        } else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }

    /**
     * 绑定搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        tableIns.reload({
            where: { //设定异步数据接⼝的额外参数，任意设
                customerName: $("[name='customerName']").val(), // 客户名
                createMan: $("[name='createMan']").val(), // 创建⼈
                state: $("#state").val() // 状态
            }
            ,page: {
                curr: 1 // 重新从第 1 ⻚开始
            }
        }); // 只重载数据
    });
    /**
     * 头部工具栏，监听事件
     */
    table.on('toolbar(saleChances)', function (data) {
        // data.event：对应的元素上设置的lay-event属性值
        // console.log(data);
        // 判断对应的事件类型
        if (data.event == "add") {
            // 添加操作
            openAddOrUpdateSaleChanceDialog();

        } else if (data.event == "del") {
            // 删除操作
            deleteSaleChance(data);
        }
    })
    /**
     * 表格⾏ 监听事件
     * saleChances为table标签的lay-filter 属性值
     */
    table.on('tool(saleChances)',function (obj) {
        var data = obj.data;//获得当前行数据
        var layEvent = obj.event;// 获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        //判断事件类型
        if (layEvent === 'edit'){//编辑操作
            //获取当前要修改的行的id
            var saleChanceId = data.id;
            //点击表格行的编辑按钮，打开更新营销机会的对话框
            openAddOrUpdateSaleChanceDialog(saleChanceId);
        }else if (layEvent == "del"){//删除操作
            //询问是否删除
            layer.confirm("确定要删除这条数据吗？",{icon:3,title:"营销机会数据管理"},function (index) {
                //关闭窗口
                layer.close(index);
                //发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url : ctx+"/sale_chance/delete",
                    data:{
                        ids:data.id
                    },
                    dataType:"json",
                    success:function (result) {
                        if (result.code == 200){
                            //加载表格
                            tableIns.reload();
                        }else {
                            layer.msg(result.msg,{icon:5});
                        }
                    }
                });
            });
        }
    });


    function openAddOrUpdateSaleChanceDialog(saleChanceId) {
        var title = "<h2>营销机会管理 - 机会添加 </h2>";
        var url = ctx + "/sale_chance/addOrUpdateSaleChancePage";
        //通过id判断是添加还是修改操作
        if (saleChanceId){
            //如果id不为空，则为修改操作
            title = "<h2>营销机会管理 - 机会更新 </h2>";
            url = url + "?id="+saleChanceId;
        }
        layui.layer.open({
            title:title,
            type: 2,
            content:url,
            area:['500px','620px'],
            maxmin:true
        });
    }

    /**
     * 删除营销机会数据
     * @param data
     */
    function deleteSaleChance(data) {
        // 获取数据表格选中的行数据   table.checkStatus('数据表格的ID属性值');
        var checkStatus = table.checkStatus("saleChanceListTable");
        console.log(checkStatus);
        // 获取所有被选中的记录对应的数据
        var saleChanceData = checkStatus.data;
        // 判断用户是否选择的记录 (选中行的数量大于0)
        if(saleChanceData.length < 1){
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
            for (var i = 0;i<saleChanceData.length;i++){
                if (i<saleChanceData.length-1){
                    ids = ids+saleChanceData[i].id + "&ids=";
                }else {
                    ids = ids +saleChanceData[i].id;
                }
            }
            //发送ajax请求删除记录
            $.ajax({
                type:"post",
                url:ctx+"/sale_chance/delete",
                data:ids,
                dataType: "json",
                success:function (result) {
                    if (result.code == 200) {
                        // 加载表格
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
        });
    }

});

