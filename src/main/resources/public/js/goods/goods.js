layui.use(['element','table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table


    /*zTree*/
    $.ajax({
        type:"post",
        url:ctx+"/goodsType/queryAllGoodsTypes",
        dataType:"json",
        success:function (data) {
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                view:{
                    showLine: false
                },
                callback: {
                    onClick: zTreeOnClick
                }
            };
            $.fn.zTree.init($("#goodsTypeTree"), setting, data);
        }
    })

    function zTreeOnClick(event, treeId, treeNode) {
        // 获取店家节点对应类型id
        var typeId =  treeNode.id;
        table.reload("goodsListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                goodsName: $("input[name='goodsName']").val(),
                typeId:typeId
            }
        })
        // 设置商品类别查询条件到隐藏域
        $("input[name='typeId']").val(typeId);
    };



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
            case "goodsType":
                addGoodsTypeManagerTab();

        };
    });







    function addGoodsTypeManagerTab(){
        //新增一个Tab项
        window.parent.layui.element.tabAdd('bodyTab', {
            title: '新选项'+ (Math.random()*1000|0)
            ,content: '内容'+ (Math.random()*1000|0)
            ,id: new Date().getTime()
        })
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
        }else{
            if(null !=$("input[name='typeId']").val()){
                url=url+"?typeId="+$("input[name='typeId']").val();
            }
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
                        if(data.code==200){
                            layer.msg("操作成功！");
                            tableIns.reload();
                        }else{
                            layer.msg(data.message, {icon: 5});
                        }
                    });
                })
            }
        });
















});
