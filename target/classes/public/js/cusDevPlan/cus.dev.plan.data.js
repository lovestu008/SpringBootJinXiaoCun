layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 计划项数据展示
     */
    var tableIns = table.render({
        elem: '#cusDevPlanList',
        url : ctx+'/cus_dev_plan/list?sid='+$("input[name='id']").val(),
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "cusDevPlanListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'planItem', title: '计划项',align:"center"},
            {field: 'exeAffect', title: '执⾏效果',align:"center"},
            {field: 'planDate', title: '执⾏时间',align:"center"},
            {field: 'createDate', title: '创建时间',align:"center"},
            {field: 'updateDate', title: '更新时间',align:"center"},
            {title: '操作',fixed:"right",align:"center", minWidth:150,
                templet:"#cusDevPlanListBar"}
        ]]
    });

    /**
     * 头部工具栏监听
     */
    table.on('toolbar(cusDevPlans)',function (obj){
       switch(obj.event){
           case "add":
               openAddOrUpdateCusDevPlanDialog();
               break;
           case "success":
               updateSaleChanceDevResult(2);
               return;
           case "failed":
               updateSaleChanceDevResult(3);
               return;
       };
    });

    /**
     * 行监听事件
     */
    table.on("tool(cusDevPlans)",function (obj) {
        var layEvent = obj.event;
        //监听编辑事件
        if (layEvent === "edit"){
            openAddOrUpdateCusDevPlanDialog(obj.data.id);
        }else if (layEvent == "del"){
            console.log(obj.data);
            delCusDevPlanDialog(obj.data.id);
        }
    });


    /**
     * 打开计划项数据页面
     */
    function openAddOrUpdateCusDevPlanDialog(id) {
        var url = ctx+"/cus_dev_plan/addOrUpdateCusDevPlanPage?sid="+$("input[name='id']").val();
        var title = "计划项管理-添加计划项";
        if (id){
            url = url + "&id=" + id;
            title = "计划项管理-更新计划项";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["500px","300px"],
            maxmin:true,
            content : url
        });
    }

    /**
     * 删除操作
     */
    function delCusDevPlanDialog(id){
        layer.confirm("您确定要删除选中的记录吗？",{
            btn:["确认","取消"],
        },function (index) {
            //关闭确认框
            layer.close(index);
            //发送ajax请求删除记录
            $.ajax({
                type:"post",
                url:ctx+"/cus_dev_plan/delete",
                data:{"id":id},
                dataType: "json",
                success:function (result) {
                    if (result.code == 200) {
                        layer.msg("删除成功",{icon: 6});
                        // 加载表格
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
        });
    }


    /*更新营销机会开发状态*/
    function updateSaleChanceDevResult(devResult){
        //获取当前营销机会的ID
        var sid = $("input[name='id']").val();
        layer.confirm("确认执行当前操作?",{icon:3,title:"计划项维护"},function (index){
            $.post(ctx+"/sale_chance/updateSaleChanceDevResult",{id:sid,devResult:devResult},function (result) {
                if (result.code == 200){//操作成功
                    layer.msg("操作成功");
                    //关闭弹出层
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload()
                }else {
                    layer.msg(result.msg,{icon:5});
                }
            });
        });

    }

});
