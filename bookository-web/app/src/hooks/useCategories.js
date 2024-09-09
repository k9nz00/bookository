import { ref } from 'vue'
import { getCategories } from '../api'

export function useCategories() {
  const categories = ref([])
  const loadCategories = async () => {
    try {
      categories.value = await getCategories()
    } catch (error) {
      console.log(error)
    }
  }

  return {
    categories,
    loadCategories
  }
}
