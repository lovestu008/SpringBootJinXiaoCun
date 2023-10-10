layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



    /**
     * 监听submit事件
     * 实现营销机会的添加与更新
     */
    form.on("submit(updatePurchase)", function (data) {
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });
        // 请求的地址
        var url = ctx + "/purchase/update";
        data.field.goodsName=$("[name='goodsName']").find("option:selected").text()
        // 发送ajax请求
        $.post(url, data.field, function (result) {
            // 操作成功
            if (result.code == 200) {
                // 提示成功
                layer.msg("操作成功！");
                // 关闭加载层
                layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                // 刷新⽗⻚⾯，重新渲染表格数据
                parent.location.reload();
            } else {
                layer.msg(result.msg);
            }
        });
        return false; // 阻⽌表单提交
    });
    $("#closeBtn").click(function (){
        var index =parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
    $.ajax({
        type:"get",
        url: ctx+"/purchase/selectAllGoodsNameById",
        data:{},
        success:function (data){
            console.log(data);
            if (data!=null){
                var goodsId=$("#goodsId").val();
                for (var i=0;i<data.length;i++){
                    if (goodsId==data[i].goodsName){
                        /*console.log(data[i].goodsId)*/
                        opt ="<option value='"+data[i].goodsId+"'selected >"+data[i].goodsName+"</option>";
                    }else {
                        opt ="<option value='"+data[i].goodsId+"'>"+data[i].goodsName+"</option>";
                    }
                    $("#goodsName").append(opt);

                }
            }
            //渲染下拉框的内容
            layui.form.render("select");
        }
    })
});
