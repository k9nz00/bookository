import MainView from './views/MainView.vue'
import BookCardView from './components/BookDetails.vue'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', component: MainView },
  { path: '/books/:bookId', component: BookCardView },
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})
