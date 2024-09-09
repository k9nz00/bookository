import { ref, computed } from 'vue'
import { getBooks } from '../api'
import { removeEmptyFieldsFromObject } from '../utils'

export function useBooks() {
  const filterParams = ref({
    name: '',
    author: '',
    language: '',
    genre: '',
    categories: []
  })

  const preparedFilterParams = computed(() => {
    const params = removeEmptyFieldsFromObject(filterParams.value)

    if(params.categories) {
      params.categories = params.categories.toString()
    }

    return params
  })

  const clearFilterParams = async () => {
    filterParams.value.name = ''
    filterParams.value.author = ''
    filterParams.value.language = ''
    filterParams.value.genre = ''
    filterParams.value.categories = []
    await loadBooks()
  }

  const books = ref([])
  const isBooksLoading = ref(false)
  const loadBooks = async () => {
    isBooksLoading.value = true
    try {
      books.value = await getBooks(preparedFilterParams.value)
    } catch (error) {
      console.log(error)
    } finally {
      isBooksLoading.value = false
    }
  }

  return {
    isBooksLoading,
    books,
    filterParams,
    loadBooks,
    clearFilterParams
  }
}
