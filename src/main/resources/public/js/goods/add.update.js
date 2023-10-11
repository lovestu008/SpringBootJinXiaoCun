layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



    /**
     * 商品单位下拉框展示
     */
    $.ajax({
        type:"post",
        url:ctx+"/goodsUnit/allGoodsUnits",
        success:function (data){
            if (data!== null) {
                $.each(data, function(index, item) {
                    if($("input[name='goodsUnit']").val()==item.id){
                        $("#unit").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
                    }else{
                        $("#unit").append("<option value='"+item.id+"' >"+item.name+"</option>");
                    }

                });
            }
            //重新渲染
            form.render("select")
        }
    })



    /**
     * 监听表单保存按钮 submit事件
     */
    form.on("submit(addOrUpdateGoods)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        var url=ctx + "/goods/add";
        if($("input[name='id']").val()){
            url=ctx + "/goods/update";
        }
        $.post(url, data.field, function (res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            } else {
                layer.msg(
                        res.msg, {
                            icon: 5
                        }
                    );
            }
        });
        //阻止表单提交
        return false;
    });



    //商品类别设置弹出框
    $("#reloadGoodsType").click(function (){
        var url  =  ctx+"/goods/toGoodsTypePage?typeId="+$("input[name='typeId']").val();
        var title="商品管理-商品类别";
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","400px"],
            maxmin:true,
            content : url
        });
    })


    /**
     * 关闭弹出层
     */
    $("#closeDlg").click(function (){
        // iframe 页面关闭 添加parent
        parent.layer.closeAll();
    })


});

