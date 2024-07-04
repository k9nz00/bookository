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

export const TEST_BOOK = {
  id: 1,
  name: 'Большая книга сказок',
  author: 'Шарль Перро',
  annotation: 'В книгу вошли сказки "Красная шапочка", "Три медведя", "Гензель и Гретель", "Джек и бобовый стебель", "Кот в сапогах", "Золушка", "Белоснежка и семь гномов", "Красавица и чудовище", "Гамельнский крысолов", "Рип Ван Винкль", "Волшебная лампа Аладдина", "Дюймовочка", "Гадкий утенок", "Русалочка", "Огниво", "Новый наряд короля", "Снежная королева", "Принцесса на горошине", "Соловей".',
  genre: 'Сказки',
  language: 'Русский',
  categories: ['Зарубежная литература', 'Для детей', 'Категория', 'Художественная литература', 'Сказки']
}

export const TEST_BOOKS = [TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK, TEST_BOOK]

export const LANGUAGES = [
  { id: 'EN', name: 'английский' },
  { id: 'RU', name: 'русский' }
]
