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

          <div class="flex flex-col justify-between">
            <div class="space-y-4">
              <div class="text-xl font-bold">{{ book.author }}, {{ book.name }}</div>

              <p>{{ book.annotation }}</p>

              <div class="flex gap-2 pt-5">
                <div
                  v-for="category in book.categories"
                  :key="category.id"
                  class="rounded-lg border border-white px-2"
                >
                  {{ category.name }}
                </div>

                <div class="rounded-lg border border-white px-2">
                  {{ book.genre }}
                </div>

                <div class="rounded-lg border border-white px-2">
                  {{ book.language }}
                </div>
              </div>
            </div>

            <BookDownloader
              class="pt-5"
              :book="book"
              :book-content="book.bookContentInfo"
            />
          </div>
        </div>

      <a href="/" class="underline underline-offset-4">Назад к списку книг</a>
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
  return route.params.bookId || ''
})
onMounted(() => {
  loadBook(bookId.value)
})
</script>

