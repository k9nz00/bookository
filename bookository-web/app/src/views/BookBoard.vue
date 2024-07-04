<template>
  <div class="board mx-auto">

    <div class="flex justify-center w-full">
      <div class="book-search-wrapper">
        <input type="text" class="book-search-input" placeholder="Поиск по названию книги" />
        <button class="book-search-button" type="button">
          Найти книгу
        </button>
        <button class="book-add-button" type="button">
          Добавить книгу
        </button>
      </div>
    </div>


    <div class="content-wrapper">
      <div class="book-list">
        <div
          v-for="(book, index) in books"
          :key="book.id + index"
          class="book"
        >
          <div class="cover">
            <img src="../assets/test_book_cover.jpg" alt="test_book_cover">
          </div>

          <div class="book-name text-2xl font-bold">
            {{ book.name }}
          </div>

          <div class="flex flex-col gap-2">
            <div class="text-lg mb-3">
              {{ book.author }}
            </div>

            <div class="category-list">
              <div v-for="category in book.categories" :key="category.id" class="category">
                {{ category }}
              </div>
            </div>
          </div>

          <button class="open-book" type="button">
            Подробнее
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

import { getBooks } from '../api/index.js'
import { TEST_BOOKS } from '../constants.js'

const books = ref(TEST_BOOKS)
// const loadBooks = () => {
//   getBooks()
//     .then(data => (books.value = data))
//     .catch(error => {
//       console.log(error)
//       books.value = TEST_BOOKS
//     })
// }
//
// onMounted(() => {
//   loadBooks()
// })
</script>

<style scoped lang="postcss">
.board {
  @apply p-4 h-full;
}

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
  width: 80%;
  position: sticky;
  top: 0;
}

.content-wrapper {
  @apply mx-auto;
  width: 100%;
  max-width: 1480px;
  height: 90vh;
  overflow: scroll;
}

.book-list {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
}

.book {
  @apply bg-gray-50 border-2 border-blue-50 rounded-md;
  padding: 20px;
}

.cover {
  padding: 32px;
  border-radius: 12px;
  max-height: fit-content;
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
  @apply bg-blue-600 rounded-md p-2 w-full text-white mt-4;
}
</style>
