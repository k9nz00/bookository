<template>
  <div class="app-page">
    <div v-if="loading">Загружается...</div>

    <div v-else-if="!loading && !book">Нет такой книги</div>

    <form
      v-else
      @submit.prevent="submit"
    >
      <div class="app-fields-container">
        <!-- ОБЛОЖКА MOBILE -->
        <BookCover v-model="book.cover" is-mobile />

        <div class="flex gap-5">
          <!-- ОБЛОЖКА DESKTOP -->
          <BookCover v-model="book.cover" />

          <!-- НАЗВАНИЕ -->
          <div class="w-full space-y-4">
            <div class="app-field-wrapper">
              <label for="name">Название</label>
              <input
                v-model="book.name"
                id="name"
                class="app-field"
                placeholder="Укажите название"
              >
            </div>

            <!-- АВТОР -->
            <div class="app-field-wrapper">
              <label for="author">Автор</label>
              <input
                v-model="book.author"
                id="author"
                class="app-field"
                placeholder="Укажите автора"
              >
            </div>

            <!-- ЖАНР -->
            <div class="app-field-wrapper">
              <label for="genre">Жанр</label>
              <input
                v-model="book.genre"
                id="genre"
                class="app-field"
                placeholder="Укажите жанр"
              >
            </div>

            <!-- КАТЕГОРИИ -->
            <div class="app-field-wrapper">
              <label>Категории</label>
              <AppAutocomplete
                class=""
                placeholder="Добавьте категорию"
                :options="categories"
                @select="selectCategories"
              />
            </div>

            <!-- ЯЗЫК ОРИГИНАЛА -->
            <div class="app-field-wrapper">
              <label>Язык оригинала</label>
              <AppSelect
                placeholder="Выберите язык"
                :options="LANGUAGES"
                :selected="LANGUAGES.find(item => item.id === book.language)"
                @select="selectLanguage"
              />
            </div>

            <!-- ЗАГРУЗИТЬ ФАЙЛ КНИГИ -->
            <input
              type="file"
              @change="loadBookFile($event.target.files)"
            >
          </div>
        </div>

        <!-- АННОТАЦИЯ -->
        <textarea
          v-model="book.annotation"
          rows="5"
          class="border border-gray-300 rounded-md p-5"
          placeholder="Добавьте аннотацию"
        />

        <div class="app-buttons-container">
          <!-- НАЗАД К СПИСКУ КНИГ -->
          <button
            type="button"
            class="app-button bordered"
            @click="router.push('/')"
          >
            <ArrowLeftIcon class="h-5 w-5"/>
            <span>Назад к списку книг</span>
          </button>

          <!-- СОХРАНИТЬ -->
          <button
            type="submit"
            class="app-button"
          >
            Сохранить
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'

import { ArrowLeftIcon } from '@heroicons/vue/20/solid'
import AppSelect from './AppSelect.vue'
import AppAutocomplete from './AppAutocomplete.vue'
import BookCover from './BookCover.vue'

import { BOOK_MODEL, LANGUAGES } from '../constants.js'
import { getCategories, getBook, createBook } from '../api/index.js'

import { useRoute, useRouter } from 'vue-router'
import { useFormData } from '../hooks/useFormData.js'

const route = useRoute()
const router = useRouter()

const bookId = ref(route.params.bookId)
const book = ref(BOOK_MODEL)
const getBookCard = () => {
  if (bookId.value) {
    return getBook(bookId.value).then(data => (book.value = data))
  }
  return Promise.resolve()
}

const categories = ref([])
const getCategoriesOptions = () => {
  return getCategories().then((data) => {
    categories.value = data
  })
}
const selectCategories = (selectedCategories) => {
  book.value.categories = selectedCategories.join(',')
}

const selectLanguage = (selectedLanguage) => {
  book.value.language = selectedLanguage.id
}

const loadBookFile = (files) => {
  book.value.book = files[0]
}

const loading = ref(false)
onMounted(() => {
  loading.value = true
  Promise.all([
    getCategoriesOptions(),
    getBookCard()
  ]).catch((error) => {
    console.log(error)
  }).finally(() => {
    loading.value = false
  })
})

const { appendFormData } = useFormData()
const submit = () => {
  const formData = appendFormData(book.value)

  createBook(formData)
    .then(response => console.log(response))
    .catch((error) => {
      console.log(error)
    })
}
</script>
