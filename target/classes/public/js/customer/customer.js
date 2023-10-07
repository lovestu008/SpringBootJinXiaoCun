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
            ,{field: 'isDel', title: '是否删除', align:'center'}
            ,{title:'操作',templet:'#customer', fixed: 'right', align:'center', minWidth:150}
        ]]
    });
    /**
     * 格式化开发状态
     * @param value
     * @returns {string}
     */
    function formatterDevResult(value){
        /**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         */
        if(value==0){
            return "<div style='color: yellow'>未开发</div>";
        }else if(value==1){
            return "<div style='color: #00FF00;'>开发中</div>";
        }else if(value==2){
            return "<div style='color: #00B83F'>开发成功</div>";
        }else if(value==3){
            return "<div style='color: red'>开发失败</div>";
        }else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }


    /**
    绑定搜索事件
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
                customerName: $("[name='customerName']").val() // 客户名称
                ,createMan: $("[name='createMan']").val() // 创建人
                ,devResult:$("#devResult").val() // 开发状态
            }
            ,page: {
                curr: 1 // 重新从第 1 页开始
            }
        });

    });

    /**
     * 行监听事件
     */
    table.on('tool(saleChances)', function (data) {
        // 判断类型
        if (data.event == "dev") { // 开发

            // 打开计划项开发与详情页面
            openCusDevPlanDialog('计划项数据开发',data.data.id);

        } else if (data.event == "info") { // 详情

            // 打开计划项开发与详情页面
            openCusDevPlanDialog('计划项数据维护',data.data.id);

        }
    });

    /**
     * 打开开发计划对话框
     */
    function openCusDevPlanDialog(title, id) {
        // iframe层
        layui.layer.open({
            // 类型
            type: 2,
            // 标题
            title: title,
            // 宽高
            area: ['750px', '550px'],
            // url地址
            content: ctx + "/cus_dev_plan/toCusDevPlanDataPage?sid="+id,
            // 可以最大化与最小化
            maxmin:true
        });
    }

});
