import BookBoard from './views/BookBoard.vue'
import BookCardView from './components/BookCardView.vue'
import AddBookCard from './components/AddBookCard.vue'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', component: BookBoard },
  { path: '/books/:shelfId/:bookId', component: BookCardView },
  { path: '/books/:shelfId', component: AddBookCard },
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})
