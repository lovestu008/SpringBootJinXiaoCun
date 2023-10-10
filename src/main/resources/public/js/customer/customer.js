layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
     /**
     * 加载数据表格
     */
    var tableIns = table.render({
        id:'customerTable'
        // 容器元素的ID属性值
        ,elem: '#customer'/*#表示绑定tableID的意思*/
        // 容器的高度 full-差值
        ,height: 'full-125'
        // 单元格最小的宽度
        ,cellMinWidth:95
        // 访问数据的URL（后台的数据接口）
        ,url: ctx + "/customer/list"
        // 开启分页
        ,page: true
        // 默认每页显示的数量
        ,limit:10
        // 每页页数的可选项
        ,limits:[10,20,30,40,50]
        // 开启头部工具栏
        ,toolbar:'#toolbarDemo'
        // 表头
        ,cols: [[
            // field：要求field属性值与返回的数据中对应的属性字段名一致
            // title：设置列的标题
            // sort：是否允许排序（默认：false）
            // fixed：固定列
            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: '编号',  sort: true, fixed: 'left'}
            ,{field: 'address', title: '地址', align:'center'}
            ,{field: 'contact', title: '联络人', align:'center'}
            ,{field: 'name', title: '超市名', align:'center'}
            ,{field: 'number', title: '电话号码', align:'center'}
            ,{field: 'remarks', title: '备注', align:'center'}
            ,{field: 'isDel', title: '是否有效', align:'center'}
            ,{title:'操作',templet:'#customerListBar'/*绑定的是script的行工具栏*/, fixed: 'right', align:'center', minWidth:150}
        ]]
    });


    /**
    绑定搜索事件,表格重载，重载走的还是加载的地址
     */
    $(".search_btn").click(function () {

        /**
         * 表格重载
         *  多条件查询
         */
        tableIns.reload({
            // 设置需要传递给后端的参数
            where: { //设定异步数据接口的额外参数，任意设
                // 通过文本框/下拉框的值，设置传递的参数
                address: $("[name='address']").val() // 地址
                ,contact: $("[name='contact']").val() // 联络人
                ,name:$("[name='name']").val() //超市名称
                ,number:$("[name='number']").val() // 电话号码
            }
            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        });

    });


    /**
     * 监听头部工具栏事件
     *  格式：
     *      table.on('toolbar(数据表格的lay-filter属性值)', function (data) {
     *
            })
     */
    table.on('toolbar(customer)', function (data) {
        // data.event：对应的元素上设置的lay-event属性值
         console.log(data);
        // 判断对应的事件类型
        if (data.event == "add") {
            // 添加操作
            openCustomerDialog();

        } else if (data.event == "del") {
            // 删除操作
            deleteCustomer(data);
        }
    });

    /**
     * 打开添加/修改超市客户数据的窗口
     *      如果营销机会ID为空，则为添加操作
     *      如果营销机会ID不为空，则为修改操作
     */
    function openCustomerDialog(customerId) {
        // 弹出层的标题
        var title = "<h3>客户管理 - 添加超市客户</h3>";
        var url = ctx + "/customer/toCustomerPage";

        // 判断超市客户ID是否为空
        if (customerId != null && customerId != '') {
            // 更新操作
            title  = "<h3>客户管理 - 添加超市客户</h3>";
            // 请求地址传递营销机会的ID
            url += '?customerId=' + customerId;
        }

        // iframe层
        layui.layer.open({
            // 类型
            type: 2,
            // 标题
            title: title,
            // 宽高
            area: ['500px', '620px'],
            // url地址
            content: url,
            // 可以最大化与最小化
            maxmin:true
        });
    }

    /**
     *删除超市客户
     * @param data
     */
    function deleteCustomer(data) {
        // 获取数据表格选中的行数据   table.checkStatus('数据表格的ID属性值');
        var checkStatus = table.checkStatus("customerTable");/*saleChanceTable是表格加载大id*/
        console.log(checkStatus);

        // 获取所有被选中的记录对应的数据
        var customerData = checkStatus.data;

        // 判断用户是否选择的记录 (选中行的数量大于0)
        if (customerData.length < 1) {
            layer.msg("请选择要删除的记录！",{icon:5});
            return;
        }

        // 询问用户是否确认删除
        layer.confirm('您确定要删除选中的记录吗？',{icon:3, title:'超市客户管理'}, function (index) {
            // 关闭确认框
            layer.close(index);
            // 传递的参数是数组   ids=1&ids=2&ids=3
            var ids = "ids=";
            // 循环选中的行记录的数据
            for(var i = 0; i < customerData.length; i++) {
                if(i < customerData.length -1) {
                    ids = ids + customerData[i].id + "&ids="
                } else {
                    ids = ids + customerData[i].id;
                }
            }
            //console.log(ids);

            // 发送ajax请求，执行删除营销机会
            $.ajax({
                type:"post",
                url:ctx + "/customer/delete",
                data:ids,
                 // 传递的参数是数组 ids=1&ids=2&ids=3
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


    /**
     * 行工具栏监听事件
     table.on('tool(数据表格的lay-filter属性值)', function (data) {

         });
     */
    table.on('tool(customer)', function (data) {
        // console.log(data);
        // 判断类型
        if (data.event == "edit") { // 编辑操作

            // 得到超市客户的ID
            var providerId = data.data.id;
            // 打开修改超市客户数据的窗口
            openCustomerDialog(providerId)

        } else if (data.event == "del") { // 删除操作
            // 弹出确认框，询问用户是否确认删除
            layer.confirm('确定要删除该记录吗？',{icon:3, title:"超市客户管理"}, function (index) {
                // 关闭确认框
                layer.close(index);

                // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url:ctx + "/customer/delete",
                    data:{
                        ids:data.data.id
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

});
