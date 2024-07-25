<template>
  <div class="app-page">
    <div v-if="loading">
      Загружаем данные
    </div>

    <form v-else @submit.prevent>
      <div class="app-fields-container">
        <div class="flex gap-5">
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
              v-if="hasCategories"
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
          <AppSubmitButton class="bg-blue-100" @click="submit">
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

import { BOOK_MODEL, LANGUAGES } from '../constants.js'
import { getCategories, createBook } from '../api/index.js'
import clean from 'lodash-clean'
import cloneDeep from 'lodash.clonedeep'

import { useRouter } from 'vue-router'

const router = useRouter()
const backToBooks = () => {
  router.push('/')
}

const categories = ref([])
const hasCategories = computed(() => {
  return categories.value.length > 0
})

const loading = ref(true)
const getCategoriesOptions = async () => {
  loading.value = true
  try {
    categories.value = await getCategories()
  } catch (error) {
    alert(error)
  } finally {
    loading.value = false
  }
}

const selectCategories = (selectedCategories) => {
  book.value.categories = selectedCategories
}

onMounted(() => {
  getCategoriesOptions()
})

const book = ref(cloneDeep(BOOK_MODEL))

const submit = () => {
  if (!book.value.name) {
    alert('Нельзя сохранить книгу без названия')
    return
  }

  createBook(clean(book.value))
    .then(() => {
      alert(`Книга ${book.value.name} успешно сохранена!`)
      backToBooks()
    })
    .catch((error) => {
      alert(error)
    })
}
</script>
