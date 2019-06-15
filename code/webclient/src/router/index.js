import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home.vue'
import About from '@/views/About.vue'
import Choice from '@/views/Choice.vue'
import Report from '@/views/Report.vue'
import A1 from "@/views/A1.vue"
import A2 from "@/views/A2.vue"
import B1 from "@/views/B1.vue"
import B2 from "@/views/B2.vue"
import B3 from "@/views/B3.vue"
import BE from "@/views/BE.vue"
import C1 from "@/views/C1.vue"
import C2 from "@/views/C2.vue"
import C3 from "@/views/C3.vue"
import CE from "@/views/CE.vue"
import D from "@/views/D.vue"


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
      path: '/choice',
      name: 'choice',
      component: Choice
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
    {
      path: '/:action/b1',
      name: 'b1',
      component: B1,
      props: true
    },
    {
      path: '/:action/b2',
      name: 'b2',
      component: B2,
      props: true
    },
    {
      path: '/:action/b3',
      name: 'b3',
      component: B3,
      props: true
    },
    {
      path: '/:action/be',
      name: 'be',
      component: BE,
      props: true
    },
    {
      path: '/:action/c1',
      name: 'c1',
      component: C1,
      props: true
    },
    {
      path: '/:action/c2',
      name: 'c2',
      component: C2,
      props: true
    },
    {
      path: '/:action/c3',
      name: 'c3',
      component: C3,
      props: true
    },
    {
      path: '/:action/ce',
      name: 'ce',
      component: CE,
      props: true
    },
    {
      path: '/:action/d',
      name: 'd',
      component: D,
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

