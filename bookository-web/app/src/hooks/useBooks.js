import { ref, computed } from 'vue'
import { getBooks } from '../api'

export function useBooks() {
  const filterParams = ref({
    name: '',
    author: '',
    language: '',
    genre: '',
    categories: []
  })

  const queryParams = computed(() => {
    const filtered =  Object.fromEntries(Object.entries(filterParams.value).filter(([ _, value ]) => {
      return value && value.length
    }))

    if(filtered.categories) {
      filtered.categories = filtered.categories.toString()
    }

    return filtered
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
      books.value = await getBooks(queryParams.value)
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
