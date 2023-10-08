import BookBoard from './views/BookBoard.vue'
import BookCardView from './components/BookCardView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', component: BookBoard },
  { path: '/books/:shelfId/:bookId', component: BookCardView },
  { path: '/books/:shelfId', component: BookCardView },
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})
