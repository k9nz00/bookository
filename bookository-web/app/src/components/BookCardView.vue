<template>
<div>
  <!-- TODO: navbar -->
  <div class="bg-green-100 w-full h-20 px-40" />

  <div class="app-page">
    <div v-if="loading">Загружается...</div>

    <div v-else-if="!loading && !book">Нет такой книги</div>

    <form v-else action="">
      <div class="app-fields-container">
        <img
          class="book-cover-small"
          :src="cover"
          alt=""
        >

        <div class="flex gap-5">
          <img
            class="book-cover-large"
            :src="cover"
            alt=""
          >

          <div class="w-full space-y-2">
            <div class="app-field-wrapper">
              <label for="name">Название</label>
              <input
                id="name"
                type="text"
                class="app-field"
                :value="book.name"
              >
            </div>

            <div class="app-field-wrapper">
              <label for="author">Автор</label>
              <input
                id="author"
                type="text"
                class="app-field"
                :value="book.author"
              >
            </div>

            <div class="app-field-wrapper">
              <label for="genre">Жанр</label>
              <input
                id="genre"
                type="text"
                class="app-field"
                :value="book.genre"
              >
            </div>

            <div class="app-field-wrapper">
              <label>Категории</label>
              <AppSelect
                class="app-field"
                :options="categories"
                :selected="book.categories"
              />
            </div>

            <div class="app-field-wrapper">
              <label>Язык оригинала</label>
              <AppSelect
                class="app-field"
                :options="languages"
                :selected="book.language"
              />
            </div>

            <div class="app-field-wrapper">
              <label>Скачать в формате</label>
              <AppSelect
                class="app-field"
                :options="formats"
                :selected="book.formats"
              />
            </div>
          </div>
        </div>

        <textarea
          rows="5"
          class="border border-blue-300 rounded-md p-5"
          :value="book.annotation"
        />

        <div class="app-buttons-container">
          <button type="button" class="app-button border border-blue-100 flex items-center gap-2">
            <ArrowLeftIcon class="h-5 w-5" />
            <span>Назад к списку книг</span>
          </button>
          <button type="submit" class="app-button bg-blue-100">Сохранить</button>
        </div>
      </div>
    </form>
  </div>
</div>
</template>

<script setup>
import { computed, ref, onMounted } from 'vue'

import { ArrowLeftIcon } from '@heroicons/vue/20/solid'
import AppSelect from './AppSelect.vue'

import { useRoute } from 'vue-router'

import { BOOK_MODEL } from '../constants.js'


const route = useRoute()
const bookId = route.params.bookId

const loading = ref(false)

const book = ref(BOOK_MODEL)
const getBook = () => {}

// TODO: getReferences
const categories = ref([])
const formats = ref([])
const languages = ref([])

const getFormats = () => {}
const getLanguages = () => {}
const getCategories = () => {}

const cover = computed(() => {
  return `data:image/gpeg;base64,${book.value.bigPreview}` || ''
})

onMounted(() => {
  // Promise.all([getBook(), getCategories(), getLanguages(), getFormats()])
  //   .catch((error) => {
  //     console.log(error)
  //   }).finally(() => {
  //     loading.value = false
  //   })
})
</script>

<style>
/* layout  */
.app-page {
  @apply p-10;
}

@media (min-width: 768px) {
  .app-page {
    @apply px-20;
  }
}

@media (min-width: 1280px) {
  .app-page {
    @apply px-40;
  }
}

/* Маленькие экраны */
.app-fields-container {
  @apply flex flex-col gap-y-4;
}

.app-field-wrapper {
  @apply flex flex-col;
  @apply text-sm text-left font-bold;
}

.app-field {
  @apply pb-2 text-base font-normal;
  @apply border-b border-blue-300;
  height: 32px;
}

.book-cover-large {
  display: none;
}

.book-cover-small {
  @apply border border-blue-300 rounded-md;
  background: url('../assets/vue.svg') no-repeat center;
  background-size: 50%;
  min-height: 300px;
  display: block;
}

.app-buttons-container {
  @apply flex flex-col gap-4 mt-5;
}

.app-button {
  min-width: 200px;
  @apply p-2;
}

/* Большие экраны */
@media (min-width: 768px) {
  .app-buttons-container {
    @apply flex-row;
  }

  .app-fields-container {
    @apply flex gap-y-4;
  }

  .app-field-wrapper {
    @apply grid grid-flow-col items-center gap-4;
    @apply text-sm font-bold;
    grid-template-columns: 1fr 5fr;
    text-align: left;
  }

  .app-field {
    @apply p-2;
  }

  .book-cover-large {
    @apply border border-blue-300 rounded-md;
    background: url('../assets/vue.svg') no-repeat center;
    background-size: 50%;
    min-height: 250px;
    min-width: 200px;
    display: block;
  }

  .book-cover-small {
    display: none;
  }
}
</style>
