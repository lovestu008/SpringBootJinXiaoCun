layui.use(['element','table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table



    //表格展示
    var tableIns = table.render({
        id:'goodsTable'
        , elem: '#goodsList'// 容器元素的ID属性值
        , height: 'full-110'// 容器的高度 full-差值
        ,cellMinWidth:95// 单元格最小的宽度
        /*, title: '商品数据表'*/
        , url: ctx + '/goods/goodsList' //数据接口
        , page: true //开启分页
        ,limit:10// 默认每页显示的数量
        ,limits:[10,15,20,25]// 每页页数的可选项
        , toolbar: '#toolbarDemo'// 开启头部工具栏
        , cols: [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:100, align:'center'},
            {field: 'typeName', title: '商品类别', minWidth:100, align:'center'},
            {field: 'unitName', title: '单位', minWidth:100, align:'center'},
            {field: 'purchasingPrice', title: '采购价', minWidth:100, align:'center'},
            {field: 'sellingPrice', title: '出售价', minWidth:100, align:'center'},
            {field: 'minNum', title: '库存下限', minWidth:100, align:'center'},
            {field: 'producer', title: '生产厂商', align:'center',minWidth:150},
            ,{title:'操作',templet:'#goodsListBar', fixed: 'right', align:'center', minWidth:150}
        ]]
    });




    // 多条件搜索
    $(".search_btn").click(function(){
        tableIns.reload({
            // 设置需要传递给后端的参数name
            where: {
                name: $("[name='goodsName']").val()//商品名称
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        })
    });




    //头工具栏事件
    table.on('toolbar(goods)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddOrUpdateGoodsDialog();//添加页面
                break;
            case "delete":
                deleteAllCheckedGoods();//批量删除

        };
    });


    /**
     * 批量删除选中数据
     */
    function deleteAllCheckedGoods(data){
        // 获取数据表格选中的行数据   table.checkStatus('数据表格的ID');
        var checkStatus = table.checkStatus("goodsTable");
        console.log(checkStatus)
        // 获取所有被选中的记录对应的数据
        var GoodsData =  checkStatus.data;
        // 判断用户是否选择的记录 (选中行的数量大于0)
        if (GoodsData.length < 1){
            layer.msg("请选择要删除的记录！",{icon:5});
            return;
        }
        // 询问用户是否确认删除
        layer.confirm('您确定要删除选中的记录吗？',{icon:3, title:'营销机会管理'}, function (index) {
            // 关闭确认框
            layer.close(index);

            // 传递的参数是数组   ids=1&ids=2&ids=3
            var ids = "ids=";
            // 循环选中的行记录的数据
            for(var i = 0; i < GoodsData.length; i++) {
                if(i < GoodsData.length -1) {
                    ids = ids + GoodsData[i].id + "&ids="
                } else {
                    ids = ids + GoodsData[i].id;
                }
            }
            // 发送ajax请求，执行删除营销机会
            $.ajax({
                type:"post",
                url:ctx+"/goods/allDelete",
                data:ids, //传参是数组
                success:function (result){
                    // 判断删除结果
                    if (result.code == 200) {
                        // 提示成功
                        layer.msg("删除成功！",{icon:6});
                        // 刷新表格
                        tableIns.reload();
                    } else {
                        // 提示失败
                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });
    }


    /**
     * 打开添加/修改页面
     */
    function openAddOrUpdateGoodsDialog(uid){
        var title="商品管理-添加商品";
        var url  =  ctx+"/goods/toAddOrUpdateGoodsPage";

        if(uid){
            url = url+"?id="+uid;
            title="商品管理-更新商品";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["800px","550px"],
            maxmin:true,
            content : url
        });
    }


        /**
         * 行监听
         */
        table.on("tool(goods)", function(obj){
            var layEvent = obj.event;
            if(layEvent === "edit") {
                openAddOrUpdateGoodsDialog(obj.data.id);
            }else if(layEvent === "del") {
                layer.confirm('确定删除当前商品？', {icon: 3, title: "商品管理"}, function (index) {
                    $.post(ctx+"/goods/delete",{id:obj.data.id},function (data) {
                        //判断删除结果
                        if(data.code==200){
                            //提示成功
                            layer.msg("操作成功！");
                            //刷新表格
                            tableIns.reload();
                        }else{
                            //提示失败
                            layer.msg(data.message, {icon: 5});
                        }
                    });
                })
            }
        });
















});
