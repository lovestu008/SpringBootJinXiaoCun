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
            ,{title:'操作',templet:'#GoodsListBar', fixed: 'right', align:'center', minWidth:150}
        ]]
    });




    // 多条件搜索
    $(".search_btn").click(function(){


        table.reload("goodsTable",{
            where: {
                goodsName: $("input[name='goodsName']").val()//商品名称
            },
            page: {
                curr: 1 //重新从第 1 页开始
            }
        })
    });


});
