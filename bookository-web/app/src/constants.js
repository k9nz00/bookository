const isDev = import.meta.env.MODE === 'development'
export const API_HOST = isDev ? 'http://192.168.1.110:8020/api' : window._getEnv_('API_HOST')

/**
 * @typedef {import('./api.books.js').Book}
 */

/**
 * @typedef Book
 */
export const BOOK_MODEL = {
  name: '',
  author: '',
  annotation: '',
  genre: '',
  language: '',
  categories: [],
}

export const LANGUAGES = [
  { id: 'EN', name: 'английский' },
  { id: 'RU', name: 'русский' }
]
