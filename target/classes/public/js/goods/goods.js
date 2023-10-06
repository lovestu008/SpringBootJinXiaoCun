layui.use(['element','table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        element = layui.element;



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
        , toolbar: '#goodsToolBar'// 开启头部工具栏
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
            {field: 'producer', title: '生产厂商', align:'center',minWidth:150},
            ,{title:'操作',templet:'#GoodsListBar', fixed: 'right', align:'center', minWidth:150}
        ]]
    });





    //搜索
    form.on("submit(doSearch)", function (data) {
        //表格数据重新加载
        console.log(data);
        tableIns.reload({
            where: data.field //额外的参数
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        });
        return false; //关闭跳转页面
    });





    //监听添加按钮
    table.on('toolbar(goodsTable)', function (obj) {
        switch (obj.event) {
            //添加栏触发事件
            case 'add':
                openAddGoodsWindow();//添加方法
                break;
        }
    });




    var mainIndex;//弹出层的索引下标
    var url;//提交路径






    //弹出添加框函数
    function openAddGoodsWindow() {
        mainIndex = layer.open({
            type:1,
            title:"添加商品",
            area:["600px","350px"],
            content:$("#addOrUpdateDiv"),//内容窗口
            success:function () {
                //每次开启清空弹出框数据
                $("#dataFrm")[0].reset();
                url="/goods/addgoods";
            }
        });
    }






    //监听添加和修改窗口的提交按钮
    form.on("submit(doSubmit)", function (data) {
        //表格数据重新加载
        console.log(data);
        $.post(url,data.field,function (result) {
            //判断是否成功
            if (result.status){
                //成功表格刷新
                tableIns.reload();
            }
            //弹出是否成功消息
            layer.msg(result.message);
            //关闭窗口
            layer.close(mainIndex)
        },"json");
        return false; //关闭跳转页面
    });




    //监听修改和删除按钮
    table.on("tool(goodsTable)", function (obj) {
        var data =obj.data;
        switch (obj.event) {
            //修改数据
            case 'update':
                openUpdateGoodsWindow(data);
                break;
            case 'delete':
                deleteGoods(data);
                break;
            case 'selectGoodsCategory':
                selectGoodsCategory(data);
                break;
        }
    });





    //分配角色
    function selectGoodsCategory(data){
        mainIndex = layer.open({
            type:1,
            title:"分配"+data.gname+"的类别",
            area:["500px","400px"],
            content:$("#selectGoodsCategoryDiv"),//内容窗口
            btn: ['确定', '取消'],
            btnAlign: 'c',
            yes: function (index, layero) {
                //获取所有选中节点
                var checkData = table.checkStatus('categoryTable');//获取选中行的数据
                if (checkData.data.length==0){
                    layer.msg("未选中")
                }else{
                    console.log(checkData.data)
                    var idArr =[];
                    for (var i=0;i<checkData.data.length;i++){
                        idArr.push(checkData.data[i].cateid);
                    }
                    var ids = idArr.join(",");
                    $.post("/goods/saveGoodsCategory",{"categoryids":ids,"goodsid":data.gid},function (result) {
                        layer.msg(result.message)
                    },"json");
                    layer.close(index);//关闭提示框
                }
            },
            btn2: function (index, layero) {
            },
            success:function () {
                initCategoryTable(data);
            }
        });
    }





    //初始化角色列表
    function initCategoryTable(data){
        table.render({
            elem: '#categoryTable'//绑定表格组件的id
            , url: '/goods/initGoodsByCategoryId/' //数据接口
            ,where:{
                "id":data.gid
            }
            , cols: [ [
                {type: 'checkbox', fixed: 'center'}
                , {field: 'cateid', title: 'ID', align: 'center'}
                , {field: 'catename', title: '类别名称', align: 'center'}
            ] ]
        });
    }






    //修改
    function openUpdateGoodsWindow(data) {
        mainIndex = layer.open({
            type:1,
            title:"修改类别",
            area:["500px","350px"],
            content:$("#addOrUpdateDiv"),//内容窗口
            success:function () {
                form.val("dataFrm",data);
                url="/goods/updategoods";
            }
        });
    }







    //删除单条数据
    function deleteGoods(data) {
        layer.confirm('是否要删除这条数据吗?', {icon: 3, title: '提示'}, function (index) {
            $.post("/goods/deleteOne", {"id": data.gid}, function (result) {
                if (result.status) {
                    //删除成功后刷新数据表格
                    tableIns.reload();
                }
                layer.msg(result.message);
            }, "json");
            layer.close(index);//关闭提示框
        });
    }

});