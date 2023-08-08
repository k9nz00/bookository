import BookBoard from './views/BookBoard.vue'
import BookCardView from './components/BookCardView.vue'
import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  { path: '/', component: BookBoard },
  { path: '/books/:shelfId/:bookId', component: BookCardView },
]

export const router = createRouter({
  history: createWebHashHistory(),
  routes,
})
