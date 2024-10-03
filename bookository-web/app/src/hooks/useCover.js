import { ref } from 'vue'
import { getCover, updateBookCover } from '../api'

export function useCover() {
  const cover = ref('')
  const loadCover = async (bookId) => {
    cover.value = await getCover(bookId)
  }
  const updateCover = async (bookId, file) => {
    await updateBookCover(bookId, file)
  }

  return {
    cover,
    loadCover,
    updateCover
  }
}
