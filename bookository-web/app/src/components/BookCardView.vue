<template>
  <div class="card-view">
    <div class="body">
      <div v-if="!book">Нет такой книги</div>

      <template v-else>
        <div class="flex gap-2 w-full">
          <div class="cover"/>

          <div class="grow space-y-2">
            <div class="input-label">
              <label for="name">Название</label>
              <input
                id="name"
                type="text"
                class="name"
                :value="book.name"
              >
            </div>

            <div class="input-label">
              <label for="author">Автор</label>
              <input
                id="author"
                type="text"
                class="name"
                :value="book.author"
              >
            </div>

            <div class="input-label">
              <label for="genre">Жанр</label>
              <input
                id="genre"
                type="text"
                class="name"
                :value="book.genre"
              >
            </div>

            <div class="input-label">
              <label for="category">Категория</label>
              <input
                id="category"
                type="text"
                class="name"
                :value="book.category"
              >
            </div>

            <div class="input-label">
              <label for="language">Язык оригинала</label>
              <select
                id="language"
                name="language"
                class="app-select"
                :value="book.language"
              >
                <option
                  v-for="language in languages"
                  :key="language.code"
                  :value="language.code"
                  :selected="language.code === book.language"
                >
                  {{ language.name }}
                </option>
              </select>
            </div>
          </div>
        </div>

        <textarea
          class="description"
          :value="book.annotation"
        />
      </template>
    </div>
  </div>
</template>

<script setup>
import defaultBoard from '../default-board.js'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

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

const shelfId = route.params.shelfId
const bookId = route.params.bookId

const book = computed(() => {
  const shelf = defaultBoard.shelves.find(item => item.id === shelfId)

  return shelf?.cards.find(book => book.id === bookId) || null
})
</script>

<style>
.card-view {
  @apply relative bg-white p-10;
}

.card-view .body {
  @apply flex flex-col flex-grow items-start justify-between;
}

.card-view .cover {
  @apply border-2 border-blue-300 rounded-md;
  min-width: 200px;
  background: url('../assets/vue.svg') no-repeat center;
  background-size: 50%;
}

.card-view .name {
  @apply p-2 w-full mr-2 block text-xl font-bold;
  @apply border-2 border-blue-300 rounded-md;
}

.card-view .description {
  @apply relative w-full bg-transparent px-2 ;
  @apply mt-2 h-64 leading-normal;
  @apply border-2 border-blue-300 rounded-md;
}

.app-select {
  @apply p-2 w-full;
  @apply border-2 border-blue-300 rounded-md;
}

.input-label {
  @apply flex items-center;
}

.input-label label{
  width: 150px;
  margin-right: 20px;
  text-align: end;
}
</style>
