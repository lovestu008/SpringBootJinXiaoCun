layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treeTable = layui.treetable;

    // 渲染表格
    treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',
        treePidName: 'pId',
        elem: '#munu-table',
        url: ctx+'/menu/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose:true,
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'name', minWidth: 100, title: '菜单名称'},
            {field: 'icon', title: '菜单模块'},
            {field: 'aclValue', title: '权限码'},
            {field: 'state', title: '节点类型'},
            {field: 'url', title: '菜单url'},
            {
                field: 'grade', width: 80, align: 'center', templet: function (d) {
                    if (d.grade == 0) {
                        return '<span class="layui-badge layui-bg-blue">目录</span>';
                    }
                    if(d.grade==1){
                        return '<span class="layui-badge-rim">菜单</span>';
                    }
                    if (d.grade == 2) {
                        return '<span class="layui-badge layui-bg-gray">按钮</span>';
                    }
                }, title: '类型'
            },
            {templet: '#auth-state', width: 180, align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    /*
        监听头部工具栏
    */
    table.on('toolbar(munu-table)',function (obj) {
        switch (obj.event){
            case "expand":
                treeTable.expandAll('#munu-table');
                break;
            case "fold":
                treeTable.foldAll("#munu-table");
                break;
            case "add":
                // 添加目录 层级=0 父菜单=-1
                openAddMenuDialog(0, -1)
        };
    });
    /**
     * 监听行工具栏
     */
    table.on('tool(munu-table)',function (data) {
        // 判断lay-event属性
        if (data.event == "add") {
            // 添加子项
            // 判断当前的层级（如果是三级菜单，就不能添加）
            if (data.data.grade == 2) {
                layer.msg("暂不支持添加四级菜单！",{icon:5});
                return;
            }
            // 一级|二级菜单   grade=当前层级+1，parentId=当前资源的ID
            openAddMenuDialog(data.data.grade+1, data.data.id);

        } else if (data.event == "edit") {
            // 修改资源
            openUpdateMenuDialog(data.data.id);

        } else if (data.event == "del") {
            var id = data.data.id;
            // 删除资源
            layer.confirm("您确定要删除选中的记录吗?",{
                icon: 3,title:"菜单管理"
            },function (index) {
                layer.close(index);
                $.post(ctx+"/menu/delete",{id:id},function (data) {
                    if (data.code == 200){
                        layer.msg("操作成功");
                        window.location.reload();
                    }else {
                        layer.msg(data.msg,{icon:5});
                    }
                });
            });
        }
    });
    /**
     * 打开添加资源的对话框
     * @param grade 层级
     * @param parentId 父菜单ID
     */
    function openAddMenuDialog(grade,parentId){
        var title = "<h3>资源管理 - 添加资源</h3>";
        var url = ctx + "/menu/toAddMenuPage?grade=" + grade + "&parentId=" + parentId;
        layui.layer.open({
            type:2,
            title:title,
            content:url,
            area:["700px","450px"],
            maxmin:true
        });
    }

    /**
     * 打开修改资源的对话框
     * @param id
     */
    function openUpdateMenuDialog(id) {
        var title = "<h3>资源管理 - 修改资源</h3>";
        var url = ctx + "/Menu/toUpdateMenuPage?id=" + id;

        layui.layer.open({
            type:2,
            title:title,
            content:url,
            area:["700px","450px"],
            maxmin:true
        });
    }

    /**
     * 删除操作
     * @param id
     */


    /*//询问用户是否删除
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
    });*/

});