import { ref } from 'vue'
import { getCover } from '../api'

export function useCover() {
  const cover = ref('')
  const loadCover = async (bookId) => {
    cover.value = await getCover(bookId)
  }

  return {
    cover,
    loadCover
  }
}
