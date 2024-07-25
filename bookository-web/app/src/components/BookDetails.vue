<template>
  <div class="app-page">
    <div v-if="loading">
      Загружается...
    </div>

    <div v-else-if="cardLoadingError">
      Нет такой книги
    </div>

    <form v-else @submit.prevent>
      <div class="app-fields-container">
        <!-- ОБЛОЖКА MOBILE -->
        <BookCover
          v-if="bookId"
          :book-id="bookId"
          :is-mobile="true"
        />

        <div class="flex gap-5">
          <!-- ОБЛОЖКА DESKTOP -->
          <BookCover
            v-if="bookId"
            :book-id="bookId"
            :disabled="!bookId"
            :preview="book.bigPreview"
          />

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
            <div class="flex gap-5">
              <div
                v-for="category in book.categories"
                :key="category.id"
                class="category-label"
              >
                {{ category.name }}
              </div>
            </div>


            <!-- ЯЗЫК ОРИГИНАЛА -->
            <AppSelect
              v-model:selected="book.language"
              placeholder="Выберите язык"
              label="Язык оригинала"
              :options="LANGUAGES"
            />

            <!-- ЗАГРУЗИТЬ ФАЙЛ КНИГИ -->
<!--            <input-->
<!--              v-if="bookId"-->
<!--              type="file"-->
<!--              :disabled="!bookId"-->
<!--              @change="loadBookFile($event.target.files)"-->
<!--            >-->

            <!-- СКАЧАТЬ В ФОРМАТЕ -->
            <BookDownloader
              :book="book"
              :book-content="book.bookContentInfo"
            />
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
<!--          <AppSubmitButton class="bg-blue-100" @click="submit">-->
<!--            Сохранить-->
<!--          </AppSubmitButton>-->

          <AppSubmitButton class="bg-red-400" @click="deleteCurrentBook">
            Удалить книгу
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
import { getCategories, getBook, createBook, updateBook, deleteBook } from '../api/index.js'
import clean from 'lodash-clean'

import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const backToBooks = () => {
  router.push('/')
}

const bookId = computed(() => {
  return route.params.bookId
})

const book = ref(BOOK_MODEL)
const cardLoadingError = ref(false)
const getBookCard = () => {
  if (bookId.value) {
    return getBook(bookId.value)
      .then(data => (book.value = data))
      .catch(() => {
        cardLoadingError.value = true
      })
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
  book.value.categories = selectedCategories
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


const submit = () => {
  if (!book.value.name) {
    alert('Нельзя сохранить книгу без названия')
    return
  }

  const updatedBook = {
    name: book.value.name,
    author: book.value.author || '',
    annotation: book.value.annotation || '',
    genre: book.value.genre || '',
    language: book.value.language || '',
    categories: (book.value.categories || []).map(category => category.id ? category.id : category)
  }

  if (bookId.value) {
    updateBook(bookId.value, clean(updatedBook))
      .then(() => {
        alert('Книга успешно обновлена!')
      })
      .catch((error) => {
        alert(error)
        console.log(error)
      })
  } else {
    createBook(clean(updatedBook))
      .then(() => {
        // TODO: bookId
        router.push({ path: route.path, query: { id: '123' } })
      })
      .catch((error) => {
        alert(error)
        console.log(error)
      })
  }
}

const deleteCurrentBook = () => {
  deleteBook(bookId.value)
  backToBooks()
}
</script>

<style scoped>
.category-label {
  @apply border-2 border-blue-600 rounded-xl pr-1 pl-1;
  width: fit-content;
}

</style>
