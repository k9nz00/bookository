import { ref, computed } from 'vue'
import { getBooks } from '../api'

const params = ref({
  name: '',
  author: ''
})

const filteredParams = computed(() => {
  return Object.fromEntries(Object.entries(params.value).filter(([ _, value ]) => {
    return value
  }))
})

const isBooksLoading = ref(false)
export function useBooks() {
  const books = ref([])
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
    loadBooks
  }
}
