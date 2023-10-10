layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



    /**
     * 监听submit事件
     * 实现营销机会的添加与更新
     */
    form.on("submit(returnPurchase)", function (data) {
        // 提交数据时的加载层 （https://layer.layui.com/）
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });
        // 请求的地址
        var url = ctx + "/purchase/return";
        var purchaseId = $("[name='id']").val();
        if (purchaseId !=null && purchaseId !='' ){
            url =ctx +"/purchase/update"
        }
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
});
