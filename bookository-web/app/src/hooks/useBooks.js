import { ref, computed } from 'vue'
import { getBooks } from '../api'

export function useBooks() {
  const params = ref({
    name: '',
    author: '',
    language: ''
  })

  const filteredParams = computed(() => {
    return Object.fromEntries(Object.entries(params.value).filter(([ _, value ]) => {
      return value
    }))
  })

  const selectLanguageParam = (languageId) => {
    params.value.language = languageId
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
    clearParams
  }
}
