<template>
  <div>
    <div class="flex justify-center w-full">
      <div class="book-search-wrapper">
        <input v-model="params.name" type="text" class="book-search-input" placeholder="Поиск по названию" />
        <input v-model="params.author" type="text" class="book-search-input" placeholder="Поиск по автору" />
        <button class="book-search-button" type="button" @click="loadBooks">
          Найти книгу
        </button>
<!--        <button class="book-add-button" type="button">-->
<!--          Добавить книгу-->
<!--        </button>-->
      </div>
    </div>

    <div v-if="isBooksLoading">
      Загружаем книги
    </div>
    <div v-else class="content-wrapper">
      <!-- Фильтры-->
      <div class="book-filters flex flex-col gap-2">
        <span class="font-bold">Категории</span>
        <template v-for="item in categories" :key="item.id">
          <label><input type="checkbox" class="pr-1"><span class="pl-1">{{ item.name }}</span></label>
        </template>

        <span class="font-bold">Язык</span>
        <template v-for="item in LANGUAGES" :key="item.id">
          <label><input type="checkbox" class="pr-1"><span class="pl-1">{{ item.name }}</span></label>
        </template>

        <button class="book-search-button" type="button">
          Применить
        </button>
        <button class="book-add-button" type="button">
          Сбросить фильтры
        </button>
      </div>
      <div class="book-list">
        <div
          v-for="(book) in books"
          :key="JSON.stringify(book)"
          class="book"
        >
          <BookCover :book-id="book.id" />

          <div class="book-name text-2xl font-bold">
            {{ book.name }}
          </div>

          <div class="flex flex-col gap-2">
            <div class="text-lg mb-3">
              {{ book.author || 'Автор неизвестен' }}
            </div>

            <div class="category-list">
              <div v-for="category in book.categories" :key="category.id" class="category">
                {{ category.name }}
              </div>
            </div>
          </div>

          <div class="open-book" type="button" @click="openBookDetails(book.id)">
            Подробнее
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'

import { LANGUAGES } from '../constants.js'
import { useRouter } from 'vue-router'
import { useCategories, useBooks } from '../hooks'
import { BookCover } from '../components'

const { categories, loadCategories }  = useCategories()
const { isBooksLoading, books, params, loadBooks } = useBooks()

const router = useRouter()
const openBookDetails = (id) => {
  router.push(`/books/${ id }`)
}

onMounted(() => {
  loadBooks()
  loadCategories()
})
</script>

<style scoped lang="postcss">
.book-search-wrapper {
  @apply flex justify-start items-start;
  gap: 12px;
  width: 100%;
  max-width: 1480px;
}

.book-search-button {
  @apply bg-blue-600 rounded-lg text-white;
  padding: 8px;
  height: 44px;
}

.book-add-button {
    @apply border-2 border-blue-600 rounded-lg text-blue-600;
    padding: 8px;
    height: 44px;
}

.book-search-input {
  @apply bg-gray-50 border-2 border-blue-50 rounded-md p-2 mb-8;
  flex-grow: 1;
  position: sticky;
  top: 0;
}

.book-filters {
  padding-top: 24px;
  min-width: 240px;
  position: sticky;
  top: 0;
}

.content-wrapper {
  @apply mx-auto flex gap-4;
  width: 100%;
  max-width: 1480px;
  height: 90vh;
  overflow: scroll;
}

.book-list {
  padding-top: 24px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
}

.book {
  @apply bg-gray-50 border-2 border-blue-50 rounded-md hover:scale-105 hover:transition-all;
  width: 280px;
  padding: 20px;
}

.book:hover {
  @apply shadow-md;
}

.category-list {
  display: flex;
  gap: 4px;
  flex-wrap: wrap;
}

.category {
  @apply flex items-center justify-center bg-blue-100 rounded-full p-2 truncate text-xs;
}

.open-book {
  @apply p-1 text-blue-600 mt-4 text-transparent;
}

.book:hover .open-book {
  @apply text-blue-600;
}
</style>
