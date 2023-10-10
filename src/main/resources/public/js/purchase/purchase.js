layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;



    //初始化数据表格
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
        console.log($("[name='provider']").val());
        /**
         * 表格重载
         *  多条件查询
         */
        tableIns.reload({
            // 设置需要传递给后端的参数
            where: { //设定异步数据接口的额外参数，任意设
                // 通过文本框，设置传递的参数
                provider: $("[name='provider']").find("option:selected").text() // 供应商
                ,goodsName: $("[name='goodsName']").find("option:selected").text() // 商品名称
            }

            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        });

    });
    /**
     * 加载供应商下拉框
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
            console.log(data);
            if (data!=null){
                var providerId=$("#providerId").val();
                for (var i=0;i<data.length;i++){
                    if (providerId==data[i].providerId){
                        var opt ="<option value='"+data[i].providerId+"'selected >"+data[i].provider+"</option>";

                    }else {
                        var opt ="<option value='"+data[i].providerId+"'>"+data[i].provider+"</option>";
                    }
                    $("#provider").append(opt);

                }
            }
            //渲染下拉框的内容
            layui.form.render("select");
        }
    })
    $.ajax({
        type:"get",
        url: ctx+"/purchase/selectAllGoodsName",
        success:function (data){
            //console.log(data)
            if (data!=null){
                var goodsId=$("#goodsId").val();
                for (var i=0;i<data.length;i++){
                    if (goodsId==data[i].goodsId){
                        console.log(data[i].goodsId)
                        var opt ="<option value='"+data[i].goodsId+"'selected >"+data[i].goodsName+"</option>";
                    }else {
                        var opt ="<option value='"+data[i].goodsId+"'>"+data[i].goodsName+"</option>";
                    }
                    $("#goodsName").append(opt);

                }
            }
            //渲染下拉框的内容
            layui.form.render("select");
        }
    })
    /**
     * 监听头部工具栏
     */
    table.on('toolbar(purchases)',function (data) {
        // 判断lay-event属性
        if (data.event == "input") { // 添加操作
            // 打开添加/更新进货信息                                    的对话框
            openAddPurchaseDialog();

        }
    });
    function openAddPurchaseDialog() {
        var title = "<h3>进货明细 - 进货信息添加</h3>"
        var url = ctx + "/purchase/toAddPurchasePage";
        layui.layer.open({
            title:title,
            content:url,
            area:["500px","550px"],
            type:2,
            maxmin:true
        });
    }

    /**
     * 监听行工具栏
     */
    table.on('tool(purchases)',function (data) {
        // 判断lay-event属性
        if (data.event == "edit") { // 修改角色
            // 打开添加/更新角色的对话框
            openUpdatePurchaseDialog(data.data.id);
        } else if (data.event == "return") {
            // 删除角色
            deletePurchase(data.data.id);
        }
    });
    function openUpdatePurchaseDialog(id) {
        var title = "<h3>进货明细 - 进货信息更改</h3>"
        var url = ctx + "/purchase/toUpdatePurchasePage?id="+id;
        layui.layer.open({
            title:title,
            content:url,
            area:["500px","550px"],
            type:2,
            maxmin:true
        });
    }
    function deletePurchase(id) {
        // 弹出确认框，询问用户是否确认删除
        layer.confirm('确定要删除该记录吗？',{icon:3, title:"进货明细"}, function (index) {
            // 关闭确认框
            layer.close(index);

            // 发送ajax请求，删除记录
            $.ajax({
                type:"post",
                url:ctx + "/purchase/delete",
                data:{
                    id:id
                },
                success:function (result) {
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

});
