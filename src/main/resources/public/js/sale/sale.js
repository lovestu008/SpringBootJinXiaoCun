layui.use(['element', 'laydate', 'table', 'layer'], function () {
        var layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            laydate = layui.laydate,
            form = layui.form
        table = layui.table;

        laydate.render({
            elem: '#saleDate'
        });


        $.ajax({
            type: "post",
            url: ctx + "/customer/allCustomers",
            success: function (data) {
                if (data !== null) {
                    $.each(data, function (index, item) {
                        $("#customerId").append("<option value='" + item.id + "' >" + item.name + "</option>");
                    });
                }
                //重新渲染
                form.render("select")
            }
        })

        /**
         * 列表展示
         */
        var tableIns = table.render({
            elem: '#saleList', // 表格绑定的ID
            url: ctx + '/sale/list', // 访问数据的地址
            cellMinWidth: 95,
            page: true, // 开启分⻚
            height: "full-125",
            limits: [10, 15, 20, 25],
            limit: 10,
            toolbar: "#toolbarDemo",
            id: "saleListTable",
            cols: [[
                {type: "checkbox", fixed: "center"},
                {field: 'id', title: '明细编号', minWidth: 50, align: "center"},
                {field: 'saleListId', title: '订单编号', minWidth: 50, align: "center"},
                {field: 'code', title: '商品编码', minWidth: 50, align: "center"},
                {field: 'name', title: '商品名称', minWidth: 100, align: 'center'},
                {field: 'model', title: '商品型号', minWidth: 100, align: 'center'},
                {field: 'price', title: '单价', minWidth: 100, align: 'center'},
                {field: 'num', title: '数量', minWidth: 100, align: 'center'},
                {field: 'unit', title: '单位', minWidth: 100, align: 'center'},
                {field: 'total', title: '总金额', minWidth: 100, align: 'center'},
                {title: '操作', minWidth: 150, templet: '#saleListBar', fixed: "right", align: "center"}
            ]]
        });
        /**
         * 头部工具栏，监听事件
         */
        table.on('toolbar(sales)', function (data) {
            // data.event：对应的元素上设置的lay-event属性值
            // console.log(data);
            // 判断对应的事件类型
            if (data.event == "add") {
                // 添加操作
                openAddOrUpdateSaleDialog();

            }
        })
        /**
         * 表格⾏ 监听事件
         * sales为table标签的lay-filter 属性值
         */
        table.on("tool(sales)", function (obj) {
            var layEvent = obj.event;
            if (layEvent === "edit") {
                openAddOrUpdateSaleDialog(obj.data.id);
            } else if (layEvent === "del") {
                layer.confirm('确定移除当前商品？', {icon: 3, title: "商品选择"}, function (index) {
                    datas.forEach((item, i) => {
                        if (item.id === obj.data.id) {
                            datas.splice(i, 1);
                        }
                    });
                    reloadTableData();
                    top.layer.close(index);
                })
            }
        });


        /**
         * 添加或修改
         * @param id
         */
        function openAddOrUpdateSaleDialog(id) {
            var title = "<h2>商品出库管理 - 添加 </h2>";
            var url = ctx + "/sale/addSalePage";

            //通过id判断是添加还是修改操作
            if (id) {
                var goods;
                for (var i = 0; i < datas.length; i++) {
                    if (datas[i].id == id) {
                        goods = datas[i];
                        break;
                    }
                }
                //如果id不为空，则为修改操作
                title = "<h2>商品出库管理 - 更新 </h2>";
                url = url + "?id=" + id + "&price=" + goods.price + "&num=" + goods.num + "&total=" + goods.total;
            }
            layui.layer.open({
                title: title,
                type: 2,
                content: url,
                area: ['950px', '620px'],
                maxmin: true,
                content: url
            });
        }


        form.on("submit(addSaleList)", function (data) {
            var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            $.post(ctx + "/sale/save", data.field, function (res) {
                if (res.code == 200) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("操作成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                        window.location.href = ctx + "/sale/index";
                    }, 500);
                } else {
                    layer.msg(
                        res.message, {
                            icon: 5
                        }
                    );
                }
            });
            return false;
        });

        var datas = [];

        function getGoodsSelectInfo(gid, gname, code, price, num, model, unit, typeId, flag) {
            console.log(gid, gname, code, price, num, model, unit, typeId, flag)
            if (flag) {
                // 添加操作
                datas.push({
                    "goodsId": gid,
                    "code": code,
                    "name": gname,
                    "price": price,
                    "num": num,
                    "model": model,
                    "unit": unit,
                    "typeId": typeId,
                    "total": price * num
                });
            } else {
                console.log("执行更新...")
                // 更新操作
                datas.forEach((item, i) => {
                    if (item.goodsId == gid) {
                        // 修改价格、数量与总金额即可
                        item.price = price;
                        item.num = num;
                        item.total = price * num;
                    }
                });
            }
            /**
             * 重载表格数据
             */
            reloadTableData();
        }

        function reloadTableData() {
            layui.table.reload("saleListTable", {
                data: datas
            })
            var total = 0;
            for (let i = 0; i < datas.length; i++) {
                total = total + datas[i].total;
            }
            layui.jquery("#amountPayable").val(total);
            layui.jquery("#amountPaid").val(total);
            // 设置选择商品json数据到隐藏域 便于后续表单提交
            layui.jquery("input[name='goodsJson']").val(JSON.stringify(datas));
            /**
             * 重载表格数据
             */
            reloadTableData();
        }
    }
)
;

