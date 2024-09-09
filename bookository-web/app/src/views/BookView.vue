<template>
  <div class="app-page text-white">
    <div v-if="isBookLoading">
      Загружается...
    </div>

    <div v-else-if="hasBookLoadingError">
      Нет такой книги
    </div>

    <div v-else-if="book" class="app-fields-container">
        <!-- ОБЛОЖКА MOBILE -->
        <BookCover
          :book-id="bookId"
          is-mobile
          disabled
        />

        <div class="flex gap-5">
          <!-- ОБЛОЖКА DESKTOP -->
          <BookCover
            :book-id="bookId"
            disabled
          />

          <div class="w-full space-y-4">
            <div>{{ book.name }}</div>
            <div>{{ book.author }}</div>
            <div>{{ book.genre }}</div>
            <div>{{ book.language }}</div>

            <div class="flex gap-5">
              <div
                v-for="category in book.categories"
                :key="category.id"
                class="category-label"
              >
                {{ category.name }}
              </div>
            </div>

            <BookDownloader
              :book="book"
              :book-content="book.bookContentInfo"
            />
          </div>
        </div>

        <p>{{ book.annotation }}</p>
      </div>
  </div>
</template>

<script setup>
import { onMounted, computed } from 'vue'
import { useBooks } from '../hooks'
import { useRoute } from 'vue-router'
import {
  BookDownloader,
  BookCover
} from '../components'

const route = useRoute()
const { book, loadBook, isBookLoading, hasBookLoadingError } = useBooks()

const bookId = computed(() => {
  return route.params.bookId
})
onMounted(() => {
  loadBook(bookId.value)
})
</script>

<style scoped>
.category-label {
  @apply border-2 border-blue-600 rounded-xl pr-1 pl-1;
  width: fit-content;
}

</style>
