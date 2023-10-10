    layui.use(['layer','echarts','jquery'], function () {
    var $ = layui.jquery;
    var echarts = layui.echarts;

    /**
     * 销量报表
     */
    $.ajax({
        type:"get",
        url:ctx + "/report/statisticsSales",
        dataType:"json",
        success:function (data) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('make'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '商品总销量前五统计'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                color: ["#2f89cf"],
                legend: {
                    left: 'center',
                    data: ['总销量']
                },
                grid: {
                    left: "0%",
                    top: "50px",
                    right: "0%",
                    bottom: "4%",
                    containLabel: true
                },
                xAxis: {
                    type: 'value'
                },
                yAxis: {
                    type: 'category',
                    data: data.data1
                },
                series: [
                    {
                        name: '总销量',
                        type: 'bar',
                        data: data.data2
                    }
                ]
            };
            myChart.setOption(option);
        }
            });
        /**
         * 进货报表
         */
        /*$.post("/report/statisticsinGoods",
            function (data) {
                var myChart = echarts.init(document.getElementById('make1'));
                // 指定图表的配置项和数据
                option = {
                    title: {
                        text: '近6个月进货报表',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['进货']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: data.data.data1
                    },
                    yAxis: {
                        type: 'value',
                    },
                    series: [
                        {
                            name: '进货量',
                            type: 'line',
                            data: data.data.data2,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        }
                    ]
                };
                myChart.setOption(option);
            },"json");

        /!**
         * 退货报表
         *!/
        $.post("/report/statisticsoutGoods",
            function (data) {
                var myChart = echarts.init(document.getElementById('make2'));
                // 指定图表的配置项和数据
                option = {
                    title: {
                        text: '近6个月退货报表',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['退货']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: data.data.data3
                    },
                    yAxis: {
                        type: 'value',
                    },
                    series: [
                        {
                            name: '退货量',
                            type: 'line',
                            data: data.data.data4,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        }
                    ]
                };
                myChart.setOption(option);
            },"json");

        /!**
         * 各种报表
         *!/
        $.post("/report/profitStatement",
            function (data) {
                var myChart = echarts.init(document.getElementById('make3'));
                // 指定图表的配置项和数据
                option = {
                    title: {
                        text: '半年利润报表',
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: ['利润', '退货支出', '销售收入']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    yAxis: [
                        {
                            type: 'category',
                            axisTick: {
                                show: false
                            },
                            data: data.data.data1
                        }
                    ],
                    series: [
                        {
                            name: '利润',
                            type: 'bar',
                            label: {
                                show: true,
                                position: 'inside'
                            },
                            emphasis: {
                                focus: 'series'
                            },
                            data: data.data.data3
                        },
                        {
                            name: '销售收入',
                            type: 'bar',
                            stack: '总量',
                            label: {
                                show: true
                            },
                            emphasis: {
                                focus: 'series'
                            },
                            data: data.data.data2
                        },
                        {
                            name: '退货支出',
                            type: 'bar',
                            stack: '总量',
                            label: {
                                show: true,
                                position: 'left'
                            },
                            emphasis: {
                                focus: 'series'
                            },
                            data: data.data.data4
                        }
                    ]
                };
                myChart.setOption(option);
            },"json");*/


    });
    // 基于准备好的dom，初始化echarts实例