import { BASE_URL } from '../constants.js'

/**
 * @typedef {import('./categories.js').Category}
 */

/**
 * @typedef {Object} Book
 * @property {number} id
 * @property {string} name
 * @property {string} author
 * @property {string} genre
 * @property {string} annotation
 * @property {Boolean} isAvailable
 * @property {string} language
 * @property {Category[]} categories
 * @property {string} bigPreview - Base64
 * @property {Array} bookContentInfo

 */


export default {
  /**
   * @returns {Promise<Book[]>}
   */
  getBooks() {
    return fetch(`${BASE_URL}/books` )
      .then((response) => response.json())
      .then((response) => response)
  },

  /**
   * @param {number} id
   * @returns {Promise<Book>}
   */
  getBook(id) {
    return fetch(`${BASE_URL}/books/${id}` )
      .then((response) => response.json())
      .then((response) => response)
  }
}
