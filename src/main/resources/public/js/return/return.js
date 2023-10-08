layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;



    //初始化数据表格
    var tableIns = table.render({
        elem: '#returnList', // 表格绑定的ID
        url : ctx + '/return/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分⻚
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "returnTable",
        cols : [[
            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: 'ID',  sort: true, fixed: 'left'}
            ,{field: 'goodsName', title: '商品名称', align:'center'}
            ,{field: 'provider', title: '供应商', align:'center'}
            ,{field: 'retNum', title: '退货数量', align:'center'}
            ,{field: 'allretPrice', title: '退货总价格', align:'center'}
            ,{field: 'retTime', title: '退货时间', align:'center'}
            ,{field: 'operatePerson', title: '操作人', align:'center'}
            ,{field: 'remark', title: '备注', align:'center'}
        ]]
    });
    /**
     * 搜索按钮的点击事件
     */
    $(".search_btn").click(function () {
        //console.log($("[name='provider']").val());
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
        url: ctx+"/return/selectAllProvider",
        success:function (data){
            //console.log(data)
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
        url: ctx+"/return/selectAllGoodsName",
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
});
