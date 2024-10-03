<template>
  <div>
    <!-- ПОИСК ПО АВТОРУ И НАЗВАНИЮ -->
    <div class="flex justify-center w-full">
      <div class="book-search-wrapper">
        <input v-model="filterParams.author" type="text" class="book-search-input" placeholder="Поиск по автору" />
        <input v-model="filterParams.name" type="text" class="book-search-input" placeholder="Поиск по названию" />
        <button class="filled-button" type="button" @click="loadBooks">
          Найти книги
        </button>
        <button class="outline-button" type="button" @click="clearFilterParams">
          Сбросить фильтры
        </button>
      </div>
    </div>

    <!-- ФИЛЬТРЫ-->
    <div class="book-filters">
      <div>
        <div class="font-bold pb-3">Категории</div>
        <div v-for="item in categories" :key="item.id" class="pb-2">
          <label>
            <input
              v-model="filterParams.categories"
              :value="item.id"
              type="checkbox"
              class="pr-1"
              name="categoryGroup"
            >
            <span class="pl-1">{{ item.name }}</span>
          </label>
        </div>
      </div>

      <div>
        <div class="font-bold pb-3">Жанр</div>
        <div v-for="item in ['Роман', 'Сказка', 'Поэма', 'Повесть', 'Пьеса']" :key="item" class="pb-2">
          <label>
            <input
              v-model="filterParams.genre"
              :value="item"
              type="radio"
              name="genreGroup"
              class="pr-1"
            >
            <span class="pl-1">{{ item }}</span>
          </label>
        </div>
      </div>

      <div>
        <div class="font-bold pb-3">Язык</div>
        <div v-for="item in LANGUAGES" :key="item.id" class="pb-2">
          <label>
            <input
              v-model="filterParams.language"
              :value="item.id"
              type="radio"
              name="languageGroup"
              class="pr-1"
            >
            <span class="pl-1">{{ item.name }}</span>
          </label>
        </div>
      </div>
    </div>

    <div>
      <div v-if="isBooksLoading">
        Загружаем книги
      </div>

      <div v-else class="book-list">
        <div
          v-for="(book) in books"
          :key="JSON.stringify(book)"
          class="book"
        >
          <BookCover :book-id="book.id" disabled />

          <div class="book-name text-2xl font-bold pt-5">
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

          <div class="open-book cursor-pointer" type="button" @click="openBookDetails(book.id)">
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
import { useBooks, useCategories } from '../hooks'
import { BookCover } from '../components'

const { categories, loadCategories } = useCategories()
const {
  isBooksLoading,
  books,
  filterParams,
  loadBooks,
  clearFilterParams
} = useBooks()

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
}

.book-search-input {
  @apply bg-gray-50 border-2 border-blue-50 rounded-md p-2 mb-8;
  flex-grow: 1;
  top: 0;
}

.book-filters {
  @apply flex gap-20 pb-3 py-6;
  min-width: 240px;
  top: 0;
}

.book-list {
  padding-top: 24px;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
  max-width: 1480px;
}

.book {
  @apply bg-gray-50 border-2 border-blue-50 rounded-md hover:scale-105 hover:transition-all;
  padding: 20px;
  align-self: start;
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
  @apply flex items-center justify-center bg-blue-100 rounded-lg p-2 truncate text-xs;
}

.open-book {
  @apply p-1 mt-4 text-transparent;
}

.book:hover .open-book {
  @apply text-blue-900;
}
</style>
