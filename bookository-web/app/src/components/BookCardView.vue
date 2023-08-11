<template>
  <div class="card-view p-10 flex items-center justify-center">
    <div v-if="loading">Загружается...</div>

    <div v-else-if="!loading && !book">Нет такой книги</div>

    <form
      v-else
      action=""
      class="card-grid"
    >
      <img
        class="cover"
        :src="cover"
        alt=""
      >

      <div class="app-input-label">
        <label for="name">Название</label>
        <input
          id="name"
          type="text"
          class="app-input"
          :value="book.name"
        >
      </div>

      <div class="app-input-label">
        <label for="author">Автор</label>
        <input
          id="author"
          type="text"
          class="app-input"
          :value="book.author"
        >
      </div>

      <div class="app-input-label">
        <label for="genre">Жанр</label>
        <input
          id="genre"
          type="text"
          class="app-input"
          :value="book.genre"
        >
      </div>

      <div class="app-input-label">
        <label for="category">Категории</label>
        <AppSelect
          class="app-input"
          :options="categories"
          :selected="selectedCategory"
        />
      </div>

      <textarea
        class="app-input annotation leading-normal"
        :value="book.annotation"
      />
    </form>
  </div>
</template>

<script setup>
import defaultBoard from '../default-board.js'
import { computed, ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'

import AppSelect from './AppSelect.vue'

const languages = [{
  name: 'Русский',
  code: 'RU'
},
{
  name: 'Английский',
  code: 'ENG'
}]

const BOOK_MODEL = {
  type: '',
  annotation: '',
  name: '',
  author: '',
  genre: '',
  category: '',
  language: '',
  id:'',
  shelfId: '',
  color: ''
}

const route = useRoute()
const bookId = route.params.bookId

const loading = ref(false)
const book = ref(null)
const getBook = () => {
  return fetch('http://192.168.1.110:8020/api/books/1')
    .then((response) => response.json())
    .then((response) => (book.value = response || null))
}

const selectedCategory = ref('')
const categories = ref([])
const getCategories = () => {
  return fetch('http://192.168.1.110:8020/api/book-categories')
    .then((response) => response.json())
    .then((response) => (categories.value = response))
}

const cover = computed(() => {
  return `data:image/gpeg;base64,${book.value.bigPreview}` || ''
})

onMounted(() => {
  // Promise.all([getBook(), getCategories()])
  //   .catch((error) => {
  //     console.log(error)
  //   }).finally(() => {
  //     loading.value = false
  //   })

  book.value = defaultBoard.shelves[0].cards[0]

  categories.value = [
    {
      name: 'Русская литература',
      id: '1'
    },
    {
      name: 'Зарубежная литература',
      id: '2'
    },
  ]
})
</script>

<style>
.card-grid {
  @apply grid gap-x-5 gap-y-4;
  grid-template-rows: repeat(10, 1fr);;
  grid-template-columns: 1fr 2fr;
}

.card-view .cover {
  @apply border border-blue-300 rounded-md;
  background: url('../assets/vue.svg') no-repeat center;
  background-size: 50%;
  grid-row-start: 1;
  grid-row-end: 5;
}

.app-input-label {
  @apply grid grid-flow-col items-center gap-4;
  grid-template-columns: 1fr 5fr;
  @apply text-sm font-bold;
  text-align: left;
}

.app-input {
  @apply p-2 font-normal;
  @apply border border-blue-300 rounded-md;
}

.annotation {
  grid-row-start: 5;
  grid-row-end: 9;
  grid-column: span 2 / span 2;
}
</style>
