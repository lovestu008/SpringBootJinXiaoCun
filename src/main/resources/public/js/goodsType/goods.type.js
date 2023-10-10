layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treeTable = layui.treetable;

    // 渲染表格
    treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',//主键id
        treePidName: 'pId',//父级类别id
        elem: '#munu-table',//绑定ftl中的表
        url: ctx+'/goodsType/list',//后端地址
        toolbar: "#toolbarDemo",
        cols: [[
            {type: 'numbers'},
            {field: 'name', minWidth: 100, title: '类别名称'},
            {
                field: 'state', width: 80, align: 'center', templet: function (d) {
                    if (d.state == 1) {  //节点类型
                        return '<span class="layui-badge layui-bg-blue">父节点</span>';
                    }
                    if (d.state == 0) {  //节点类型
                        return '<span class="layui-badge layui-bg-gray">子节点</span>';
                    }
                }, title: '类型'
            },
            {templet: '#auth-state', width: 180, align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    /**
     * 监听头部工具栏
     */
    table.on('toolbar(munu-table)', function (data) {
        // 判断lay-event属性
        if (data.event == "expand") {
            // 全部展开
            treeTable.expandAll("#munu-table");

        } else if (data.event == "fold") {
            // 全部折叠
            treeTable.foldAll("#munu-table");
        }
    });

    /**
     * 监听行工具栏
     */
    table.on('tool(munu-table)',function (data) {
        // 判断lay-event属性
        if (data.event == "add") {
            // 添加类别
            openAddModuleDialog(data.data.id);

        } else if (data.event == "del") {
            // 删除类别
            deleteModule(data.data.id);
        }
    });


    /**
     * 打开添加类别的对话框
     * @param id
     * @param parentId 父菜单ID
     */
    function openAddModuleDialog(id) {
        var title = "<h3>类别添加</h3>";
        var url = ctx + "/goodsType/addGoodsTypePage?id="+id;

        layui.layer.open({
            type:2,
            title:title,
            content:url,
            area:["700px","450px"],
            maxmin:true
        });
    }



    /**
     * 删除类别
     * @param id
     */
    function deleteModule(id) {
        // 弹出确认框询问用户是否确认删除
        layer.confirm('您确认删除该记录吗？',{icon:3, title:"资源管理"}, function (data) {
            // 如果确认删除，则发送ajax请求
            $.post(ctx+ "/goodsType/delete",{id:id},function (result) {
                // 判断是否成功
                if (result.code == 200) {
                    layer.msg("删除成功！",{icon:6});
                    // 刷新页面
                    window.location.reload();
                } else {
                    layer.msg(result.msg,{icon:5});
                }
            });
        });
    }
    
});