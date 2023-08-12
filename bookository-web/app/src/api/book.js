import { BASE_URL } from '../constants.js'

/**
 * @typedef {Object} Book
 * @property {string} name
 * @property {string} id
 */

/**
 * @param {string} id
 * @return {Promise<Book>}
 */
export default {
  getBook(id) {
    return fetch(`${BASE_URL}/books/${id}` )
      .then((response) => response.json())
      .then((response) => response)
  }
}
