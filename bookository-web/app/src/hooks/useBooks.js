import { ref } from 'vue'
import { getBooks } from '../api'

const params = ref({
  name: '',
  author: ''
})

const isBooksLoading = ref(false)
export function useBooks() {
  const books = ref([])
  const loadBooks = async () => {
    isBooksLoading.value = true
    try {
      books.value = await getBooks(params.value)
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
