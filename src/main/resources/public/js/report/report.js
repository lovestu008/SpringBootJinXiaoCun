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
                    text: '商品总销量前五统计',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                color: ["#2f89cf"],
                legend: {
                    left: 'left',
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
         * 进货分布构成
         * 发送ajax请求，查询饼状图所需的数据
         */
        $.ajax({
            type:"get",
            url:ctx + "/report/statisticsInGoods",
            dataType:"json",
            success:function (data) {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('make1'));

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '当月进货分布构成',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        left: 'center',
                        top: 'bottom',
                        data: data.data1
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {
                                show: true,
                                type: ['pie', 'funnel']
                            },
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    series: [
                        {
                            name: '半径模式',
                            type: 'pie',
                            radius: [20, 110],
                            center: ['25%', '50%'],
                            roseType: 'radius',
                            label: {
                                show: false
                            },
                            emphasis: {
                                label: {
                                    show: true
                                }
                            },
                            data: data.data2
                        },
                        {
                            name: '面积模式',
                            type: 'pie',
                            radius: [30, 110],
                            center: ['75%', '50%'],
                            roseType: 'area',
                            data: data.data2
                        }
                    ]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });

        /**
         * 退货报表
         */
        /*
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