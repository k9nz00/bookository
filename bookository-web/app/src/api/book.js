import { BASE_URL } from '../constants.js'

/**
 * @typedef {import('./categories.js').Category}
 */

/**
 * @typedef {Object} Book - TODO: уточнить типы данных во всех полях
 * @property {string} id
 * @property {string} name
 * @property {string} author
 * @property {string} genre
 * @property {string} language
 * @property {string} annotation
 * @property {Category[]} categories
 * @property {string} cover - Base64
 * @property {String[]} formats - TODO: доступные форматы для скачивания
 * @property {string} status - TODO: читаю, хочу прочитать, прочитано
 * @property {string} type - TODO: type card для доски
 */

/**
 * @param {string} id
 * @returns {Promise<Book>}
 */
export default {
  getBook(id) {
    return fetch(`${BASE_URL}/books/${id}` )
      .then((response) => response.json())
      .then((response) => response)
  }
}
