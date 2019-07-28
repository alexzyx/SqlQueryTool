<template>
    <el-container>
        <el-aside width="200px">
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
        </el-aside>
        <el-container>
            <el-main>
            </el-main>
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
            options:[]
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
