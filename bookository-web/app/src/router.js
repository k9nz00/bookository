import MainView from './views/MainView.vue'
import BookView from './views/BookView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', component: MainView },
  { path: '/books/:bookId', component: BookView },
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})
