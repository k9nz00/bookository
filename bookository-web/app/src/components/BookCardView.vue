<template>
  <div class="app-page">
    <div v-if="loading">Загружается...</div>

    <div v-else-if="!loading && !book">Нет такой книги</div>

    <form v-else @submit.prevent="submit">
      <div class="app-fields-container">
        <!-- ОБЛОЖКА -->
        <img
          class="book-cover-mobile"
          :src="cover"
          alt=""
          @click="upload"
        >
        <!-- ЗАГРУЗИТЬ ОБЛОЖКУ -->
        <input
          class="hidden"
          type="file"
          id="cover"
          ref="coverInput"
          name="cover"
          @change="onUploadCover($event.target.files)"
        >

        <div class="flex gap-5">
          <!-- ОБЛОЖКА -->
          <img
            class="book-cover-desktop"
            :src="cover"
            alt=""
            @click="upload"
          >
          <!-- ЗАГРУЗИТЬ ОБЛОЖКУ -->
          <input
            class="hidden"
            type="file"
            id="cover"
            ref="coverInput"
            name="cover"
            @change="onUploadCover($event.target.files)"
          >

          <!-- НАЗВАНИЕ -->
          <div class="w-full space-y-4">
            <div class="app-field-wrapper">
              <label for="name">Название</label>
              <input
                v-model="book.name"
                id="name"
                type="text"
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
                type="text"
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
                type="text"
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
                :options="languages"
                :selected="languages.find(item => item.id === book.language)"
                @select="selectLanguage"
              />
            </div>

            <!-- ЗАГРУЗИТЬ ФАЙЛ КНИГИ -->
            <input type="file" @change="loadBookFile($event.target.files)">
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
            class="app-button border border-blue-100 flex items-center gap-2"
            @click="router.push('/')"
          >
            <ArrowLeftIcon class="h-5 w-5" />
            <span>Назад к списку книг</span>
          </button>

          <!-- СОХРАНИТЬ -->
          <button type="submit" class="app-button bg-blue-100">Сохранить</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'

import { ArrowLeftIcon } from '@heroicons/vue/20/solid'
import AppSelect from './AppSelect.vue'
import AppAutocomplete from './AppAutocomplete.vue'

import { useRoute, useRouter } from 'vue-router'

import { BOOK_MODEL } from '../constants.js'

import categoriesApi from '../api/categories.js'
import bookApi from '../api/book.js'

const route = useRoute()
const router = useRouter()
const bookId = ref(route.params.bookId)

const book = ref(BOOK_MODEL)
const getBook = () => {
  if(bookId.value) {
    return bookApi.getBook(bookId.value).then(data => (book.value = data))
  }

  return Promise.resolve()
}

const cover = computed(() => {
  // return book.value.bigPreview ? `data:image/gpeg;base64,${book.value.bigPreview}` : ''
  return book.value.bigPreview || ''
})

const coverInput = ref(null)
const upload = () => {
  if (!coverInput.value) {
    console.error('Нет доступа к полю для выбора файлов')
    return
  }

  coverInput.value.click()
}

const onUploadCover = (uploadedFiles) => {
  displayCover(uploadedFiles[0])
}

const displayCover = (file) => {
  book.value.bookCover = file

  let reader = new FileReader()

  reader.readAsDataURL(file)

  reader.onload = function() {
    book.value.bigPreview  = reader.result
  }
}

const loadBookFile = (files) => {
  book.value.book = files[0]
}

const categories = ref([
  { id: 1, name: 'русская литература' },
  { id: 2, name: 'зарубежная литература' },
  { id: 3, name: 'классика'},
  { id: 5, name: 'поэзия' }
])
const getCategories = () => {
  return categoriesApi.getCategories().then((data) => {
    categories.value = data
  })
}

const languages = ref([
  { id: 'EN', name: 'английский' },
  { id: 'RU', name: 'русский' },
])

const selectLanguage = (selectedLanguage) => {
  book.value.language = selectedLanguage.id
}

const selectCategories = (selectedCategories) => {
  book.value.categories = selectedCategories
}

const loading = ref(false)
onMounted(() => {
  if (bookId.value) {
    loading.value = true
    Promise.all([
      getCategories(),
      getBook()
    ]).catch((error) => {
      console.log(error)
    }).finally(() => {
      loading.value = false
    })
  }
})


const submit = () => {
  const data = new FormData()

  data.append('name', book.value.name)
  data.append('author', book.value.author)
  data.append('genre', book.value.genre)
  data.append('annotation', book.value.annotation)
  data.append('bookCover', book.value.bookCover)
  data.append('book', book.value.book)
  data.append('categories[]', JSON.stringify(book.value.categories))
  data.append('language', book.value.language)

  bookApi.createBook(data)
    .then(response => console.log(response))
    .catch((error) => {
      console.log(error)
    })
}
</script>

<style>
/* layout  */
.app-page {
  @apply py-20 px-10;
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

/* tablet, mobile */
.app-fields-container {
  @apply flex flex-col gap-y-4;
}

.app-field-wrapper {
  @apply flex flex-col;
  @apply text-left font-bold;
}

.app-field {
  @apply pb-2 font-normal;
  @apply border-b border-gray-300;
  height: 32px;
}

.book-cover-desktop {
  display: none;
}

.book-cover-mobile {
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

/* desktop */
@media (min-width: 768px) {
  .app-buttons-container {
    @apply flex-row;
  }

  .app-fields-container {
    @apply flex gap-y-4;
  }

  .app-field-wrapper {
    @apply grid grid-flow-col items-center gap-4;
    @apply font-bold;
    grid-template-columns: 1fr 5fr;
    text-align: left;
  }

  .app-field {
    @apply p-2;
  }

  .book-cover-desktop {
    @apply border border-blue-300 rounded-md;
    background: url('../assets/vue.svg') no-repeat center;
    background-size: 50%;
    min-height: 250px;
    min-width: 200px;
    display: block;
  }

  .book-cover-mobile {
    display: none;
  }
}
</style>
