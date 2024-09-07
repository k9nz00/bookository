import { ref, computed } from 'vue'
import { getBooks } from '../api'

export function useBooks() {
  const params = ref({
    name: '',
    author: '',
    language: '',
    genre: '',
    categories: []
  })

  const filteredParams = computed(() => {
    const filtered =  Object.fromEntries(Object.entries(params.value).filter(([ _, value ]) => {
      return value && value.length
    }))

    if(filtered.categories) {
      filtered.categories = filtered.categories.toString()
    }

    return filtered
  })

  const selectLanguageParam = (languageId) => {
    params.value.language = languageId
  }

  const selectCategoryParam = (categoryId) => {
    if(params.value.categories.includes(categoryId)) {
      params.value.categories = params.value.categories.filter(item => item !== categoryId)
    } else {
      params.value.categories.push(categoryId)
    }
  }

  const clearParams = async () => {
    params.value.name = ''
    params.value.author = ''
    await loadBooks()
  }

  const books = ref([])
  const isBooksLoading = ref(false)
  const loadBooks = async () => {
    isBooksLoading.value = true
    try {
      books.value = await getBooks(filteredParams.value)
    } catch (error) {
      console.log(error)
    } finally {
      isBooksLoading.value = false
    }
  }

  return {
    isBooksLoading,
    books,
    params,
    loadBooks,
    selectLanguageParam,
    selectCategoryParam,
    clearParams
  }
}
