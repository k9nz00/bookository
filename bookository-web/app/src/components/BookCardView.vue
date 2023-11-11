<template>
  <div class="app-page">
    <div v-if="loading">Загружается...</div>

    <div v-else-if="cardLoadingError">Нет такой книги</div>

    <form v-else @submit.prevent>
      <div class="app-fields-container">
        <!-- ОБЛОЖКА MOBILE -->
        <BookCover
          v-model="book.cover"
          :preview="book.bigPreview"
          is-mobile
        />

        <div class="flex gap-5">
          <!-- ОБЛОЖКА DESKTOP -->
          <BookCover v-model="book.cover" :preview="book.bigPreview" />

          <!-- НАЗВАНИЕ -->
          <div class="w-full space-y-4">
            <AppInput
              v-model="book.name"
              id="name"
              placeholder="Укажите название"
              label="Название"
            />

            <!-- АВТОР -->
            <AppInput
              v-model="book.author"
              id="author"
              placeholder="Укажите автора"
              label="Автор"
            />

            <!-- ЖАНР -->
            <AppInput
              v-model="book.genre"
              id="genre"
              placeholder="Укажите жанр"
              label="Жанр"
            />

            <!-- КАТЕГОРИИ -->
            <AppAutocomplete
              placeholder="Добавьте категорию"
              label="Категории"
              :options="categories"
              :selected-options="book.categories"
              @select="selectCategories"
            />

            <!-- ЯЗЫК ОРИГИНАЛА -->
            <AppSelect
              v-model:selected="book.language"
              placeholder="Выберите язык"
              label="Язык оригинала"
              :options="LANGUAGES"
            />

            <!-- ЗАГРУЗИТЬ ФАЙЛ КНИГИ -->
            <input
              type="file"
              @change="loadBookFile($event.target.files)"
            >

            <!-- СКАЧАТЬ В ФОРМАТЕ -->
            <template v-for="content in book.bookContentInfo" :key="content.id">
              <BookDownloader :book-content="content" :book-name="book.name" />
            </template>
          </div>
        </div>

        <!-- АННОТАЦИЯ -->
        <AppTextarea
          v-model="book.annotation"
          placeholder="Добавьте аннотацию"
        />

        <div class="app-buttons-container">
          <!-- НАЗАД К СПИСКУ КНИГ -->
          <AppIconButton
            icon="ArrowLeftIcon"
            @click="backToBooks"
          >
            Назад к списку книг
          </AppIconButton>

          <!-- СОХРАНИТЬ -->
          <AppSubmitButton @click="submit">
            Сохранить
          </AppSubmitButton>
        </div>
      </div>
    </form>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue'

import AppSelect from './AppSelect.vue'
import AppAutocomplete from './AppAutocomplete.vue'
import AppInput from './AppInput.vue'
import AppTextarea from './AppTextarea.vue'
import AppSubmitButton from './AppSubmitButton.vue'
import AppIconButton from './AppIconButton.vue'
import BookDownloader from './BookDownloader.vue'
import BookCover from './BookCover.vue'

import { BOOK_MODEL, LANGUAGES } from '../constants.js'
import { getCategories, getBook, createBook } from '../api/index.js'

import { useRoute, useRouter } from 'vue-router'
import { useFormData } from '../hooks/useFormData.js'

const route = useRoute()
const router = useRouter()
const backToBooks = () => {
  router.push('/')
}

const bookId = ref(route.params.bookId)
const book = ref(BOOK_MODEL)
const getBookCard = () => {
  if (bookId.value) {
    return getBook(bookId.value).then(data => (book.value = data))
  }
  return Promise.resolve()
}
const cardLoadingError = computed(() => {
  return !loading.value && bookId.value && !book.value.name
})

const categories = ref([])
const getCategoriesOptions = () => {
  return getCategories().then((data) => {
    categories.value = data
  })
}
const selectCategories = (selectedCategories) => {
  book.value.categories = selectedCategories.join(',')
}

// TODO: Проверка на тип файла
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
  // TODO: Проверка на пустые поля

  createBook(formData)
    .catch((error) => {
      console.log(error)
    })
}
</script>
