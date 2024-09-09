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

  const selectCategoryParam = (categoryId) => {
    if(params.value.categories.includes(categoryId)) {
      params.value.categories = params.value.categories.filter((item) => item !== categoryId)
    } else {
      params.value.categories.push(categoryId)
    }
  }

  const selectFilterParam = (paramType, selectedParam) => {
    if(paramType === 'category') {
      selectCategoryParam(selectedParam)
    }

    params.value[paramType] = selectedParam
  }

  const clearFilterParams = async () => {
    params.value.name = ''
    params.value.author = ''
    params.value.language = ''
    params.value.genre = ''
    params.value.categories = []
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
    selectFilterParam,
    clearFilterParams
  }
}
