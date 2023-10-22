export const BASE_URL = window._getEnv_('API_HOST')

/**
 * @typedef {import('./api.books.js').Book}
 */

/**
 * @typedef Book
 */
export const BOOK_MODEL = {
  id:'',
  name: '',
  author: '',
  annotation: '',
  genre: '',
  cover: '',
  language: '',
  categories: [],
  book: ''
}

export const LANGUAGES = [
  { id: 'EN', name: 'английский' },
  { id: 'RU', name: 'русский' }
]
