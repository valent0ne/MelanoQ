import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home.vue'
import About from '@/views/About.vue'
import New from '@/views/New.vue'
import Report from '@/views/Report.vue'
import A1 from "@/views/A1.vue"
import A2 from "@/views/A2.vue"

Vue.use(Router)

export default new Router({
  linkExactActiveClass: 'active',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },
    {
      path: '/new',
      name: 'new',
      component: New
    },
    {
      path: '/report',
      name: 'report',
      component: Report
    },
    {
      path: '/:action/a1',
      name: 'a1',
      component: A1,
      props: true
    },
    {
      path: '/:action/a2',
      name: 'a2',
      component: A2,
      props: true
    },
  ]
})


/*
const router = new VueRouter({
  routes: [
    { path: '/search', component: SearchUser, props: (route) => ({ query: route.query.q }) }
  ]
})

The URL /search?q=vue would pass {query: 'vue'} as props to the SearchUser component.
*/

