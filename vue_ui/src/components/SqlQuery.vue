<template>
    <el-container>
        <el-aside width="400px">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>数据库</span>
                <el-button style="float: right; padding: 3px 0" type="text">添加数据库</el-button>
              </div>
              <el-select
                  v-model="schemas"
                  filterable
                  allow-create
                  default-first-option
                  placeholder="请选择数据库">
                  <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
              </el-select>
              <el-tree :data="tables" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
            </el-card>
        </el-aside>
        <el-container>
            <el-main>
                <el-card class="box-card">
                  <div slot="header" class="clearfix">
                    <span>查询</span>
                  </div>
                  <el-input
                      type="textarea"
                      :rows="2"
                      placeholder="请输入sql"
                      v-model="textarea">
                  </el-input>
                  <el-button type="primary">查询</el-button>
                  <el-button type="primary">执行</el-button>
                </el-card>
            </el-main>
            <el-footer>
                <el-card class="box-card">
                  <div slot="header" class="clearfix">
                    <span>结果</span>
                  </div>
                  <el-table
                      :data="tableData"
                      height="250"
                      border
                      style="width: 100%">
                      <el-table-column
                        prop="date"
                        label="日期"
                        width="180">
                      </el-table-column>
                      <el-table-column
                        prop="name"
                        label="姓名"
                        width="180">
                      </el-table-column>
                      <el-table-column
                        prop="address"
                        label="地址">
                      </el-table-column>
                  </el-table>
                  <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage4"
                    :page-sizes="[100, 200, 300, 400]"
                    :page-size="100"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="400">
                  </el-pagination>
                </el-card>
            </el-footer>
        </el-container>
    </el-container>
</template>
<script>
import axios from 'axios';
export default {
    name: 'SqlQuery',
    data() {
        return {
            tables: [{
                label: '1级1',
                children: [{
                    label: '二级 1-1',
                    children: [{
                        label: '三级 1-1-1'
                    }]
                }]
            }],
            defaultProps: {
                children: 'children',
                label: 'label'
            },
            schemas: [],
            options:[],
            tableData: [{
              date: '2016-05-03',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1518 弄'
            }, {
              date: '2016-05-02',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1518 弄'
            }, {
              date: '2016-05-04',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1518 弄'
            }, {
              date: '2016-05-01',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1518 弄'
            }, {
              date: '2016-05-08',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1518 弄'
            }, {
              date: '2016-05-06',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1518 弄'
            }, {
              date: '2016-05-07',
              name: '王小虎',
              address: '上海市普陀区金沙江路 1518 弄'
            }]
        }
    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
      }
    },
    created() {
        axios
            .get('http://localhost:8081/schemas/all')
            .then(response => (console.log(response)))
    },
    components:{
        axios
    }
}
</script>
