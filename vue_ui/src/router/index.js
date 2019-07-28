import Vue from 'vue';
import Router from 'vue-router';
import HelloWorld from '@/components/HelloWorld';
import SqlQuery from '@/components/SqlQuery';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'SqlQuery',
      component: SqlQuery
    },
    {
      path: '/1',
      name: 'HelloWorld',
      component: HelloWorld
    },
  ]
})
