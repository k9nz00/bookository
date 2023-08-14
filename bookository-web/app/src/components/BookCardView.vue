<template>
<div>
  <!-- TODO: navbar -->
  <div class="bg-green-100 w-full h-20 px-40" />

  <div class="app-page">
    <div v-if="loading">Загружается...</div>

    <div v-else-if="!loading && !book">Нет такой книги</div>

    <form
      v-else
      method="POST"
      enctype="multipart/form-data"
      @submit.prevent="submit"
    >
      <div class="app-fields-container">
        <!-- ЗАГРУЗИТЬ ОБЛОЖКУ -->
        <input
          type="file"
          id="cover"
          name="cover"
          @change="loadCover($event.target.files)"
        >

        <!-- ОБЛОЖКА MOBILE -->
        <img
          class="book-cover-small"
          :src="cover"
          alt=""
        >

        <div class="flex gap-5">
         <!-- ОБЛОЖКА DESKTOP -->
          <img
            class="book-cover-large"
            :src="cover"
            alt=""
          >

          <!-- НАЗВАНИЕ -->
          <div class="w-full space-y-2">
            <div class="app-field-wrapper">
              <label for="name">Название</label>
              <input
                id="name"
                type="text"
                class="app-field"
                placeholder="Укажите название"
                :value="book.name"
              >
            </div>

            <!-- АВТОР -->
            <div class="app-field-wrapper">
              <label for="author">Автор</label>
              <input
                id="author"
                type="text"
                class="app-field"
                placeholder="Укажите автора"
                :value="book.author"
              >
            </div>

            <!-- ЖАНР -->
            <div class="app-field-wrapper">
              <label for="genre">Жанр</label>
              <input
                id="genre"
                type="text"
                class="app-field"
                placeholder="Укажите жанр"
                :value="book.genre"
              >
            </div>

            <!-- КАТЕГОРИИ -->
            <!-- TODO: multiselect or autocomplete -->
            <div class="app-field-wrapper">
              <label>Категории</label>
              <AppSelect
                class="app-field"
                placeholder="Выберите категории"
                :selected="book.categories[0]"
                :options="categories"
              />
            </div>

            <!-- ЯЗЫК ОРИГИНАЛА -->
            <div class="app-field-wrapper">
              <label>Язык оригинала</label>
              <AppSelect
                class="app-field"
                placeholder="Выберите язык"
                :options="languages"
                :selected="languages.find(item => item.id === book.language)"
              />
            </div>

            <!-- TODO: СКАЧАТЬ В ФОРМАТЕ -->
            <div class="app-field-wrapper">
              <label>Скачать</label>
              <div class="app-field border-none flex items-center gap-4 font-normal text-blue-500">
                <div
                  v-for="format in formats"
                  :key="format.id"
                >
                {{ format.name }}
                </div>
              </div>
            </div>

            <!-- TODO: Загрузить файл книги -->
            <input type="file">
          </div>
        </div>

        <!-- АННОТАЦИЯ -->
        <textarea
          rows="5"
          class="border border-gray-300 rounded-md p-5"
          placeholder="Добавьте аннотацию"
          :value="book.annotation"
        />

        <!-- СОХРАНИТЬ -->
        <div class="app-buttons-container">
          <button
            type="button"
            class="app-button border border-blue-100 flex items-center gap-2"
            @click="router.push('/')"
          >
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

import { useRoute, useRouter } from 'vue-router'

import { BOOK_MODEL } from '../constants.js'

import categoriesApi from '../api/categories.js'
import bookApi from '../api/book.js'

const route = useRoute()
const router = useRouter()
const bookId = ref(route.params.bookId || 2)

const loading = ref(false)

const book = ref(BOOK_MODEL)
const getBook = () => {
  if(bookId.value) {
    return bookApi.getBook(bookId.value).then(data => (book.value = data))
  }

  return Promise.resolve()
}

const cover = computed(() => {
  return book.value.bigPreview ? `data:image/gpeg;base64,${book.value.bigPreview}` : ''
})

const loadCover = (files) => {
  let file = files[0]

  let reader = new FileReader()

  reader.readAsDataURL(file)

  reader.onload = function() {
    book.value.bigPreview  = reader.result
  }
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

const formats = ref([
  { id: 1, name: 'txt', disabled: false },
  { id: 2, name: 'fb2', disabled: false },
  { id: 3, name: 'pdf', disabled: true },
  { id: 4, name: 'epub', disabled: true },
  { id: 5, name: 'doc', disabled: true }
])
const languages = ref([
  { id: 'EN', name: 'английский' },
  { id: 'RU', name: 'русский' },
])

onMounted(() => {
  loading.value = true
  Promise.all([
    getCategories(),
    getBook()
  ])
    .catch((error) => {
      console.log(error)
    }).finally(() => {
      loading.value = false
    })
})

const submit = () => {
}
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
