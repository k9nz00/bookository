import { ref } from 'vue'
import { getBooks } from '../api'

const isBooksLoading = ref(false)
export function useBooks() {
  const books = ref([])
  const loadBooks = async () => {
    isBooksLoading.value = true
    try {
      books.value = await getBooks()
    } catch (error) {
      alert(error)
    } finally {
      isBooksLoading.value = false
    }
  }

  return {
    isBooksLoading,
    books,
    loadBooks
  }
}
