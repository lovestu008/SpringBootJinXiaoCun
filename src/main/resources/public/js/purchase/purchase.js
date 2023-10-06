layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        elem: '#purchaseList', // 表格绑定的ID
        url : ctx + '/purchase/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分⻚
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "purchaseTable",
        cols : [[
            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: 'ID',  sort: true, fixed: 'left'}
            ,{field: 'goodsName', title: '商品名称', align:'center'}
            ,{field: 'provider', title: '供应商', align:'center'}
            ,{field: 'inpPrice', title: '进货价格', align:'center'}
            ,{field: 'inpNum', title: '进货数量', align:'center'}
            ,{field: 'allInpPrice', title: '进货总价格', align:'center'}
            ,{field: 'inpTime', title: '进货时间', align:'center'}
            ,{field: 'operatePerson', title: '操作人', align:'center'}
            ,{field: 'remark', title: '备注', align:'center'}
            ,{title:'操作',templet:'#purchaseListBar', fixed: 'right', align:'center', minWidth:150}
        ]]
    });
    /**
     * 搜索按钮的点击事件
     */
    $(".search_btn").click(function () {

        /**
         * 表格重载
         *  多条件查询
         */
        tableIns.reload({
            // 设置需要传递给后端的参数
            where: { //设定异步数据接口的额外参数，任意设
                // 通过文本框，设置传递的参数
                provider: $("[name='provider']").val() // 供应商
                ,goodsName: $("[name='goodsName']").val() // 商品名称
            }
            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        });

    });
    /**
     * 加载角色下拉框
     *
     * 配置远程搜索, 请求头, 请求参数, 请求类型等
     *
     * formSelects.config(ID, Options, isJson);
     *
     * @param ID        xm-select的值
     * @param Options   配置项
     * @param isJson    是否传输json数据, true将添加请求头 Content-Type: application/json; charset=UTF-8
     */
    $.ajax({
        type:"get",
        url: ctx+"/purchase/selectAllProvider",
        success:function (data){
            if (data!=null){
                var providerId=$("#providerId").val();
                for (var i=0;i<data.length;i++){
                    if (providerId==data[i].id){
                        opt ="<option value='"+data[i].id+"'selected >"+data[i].uname+"</option>";
                    }else {
                        opt ="<option value='"+data[i].id+"'>"+data[i].uname+"</option>";
                    }
                    $("#provider").append(opt);

                }
            }
        }
    })
});
