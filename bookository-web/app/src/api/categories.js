import { BASE_URL } from '../constants.js'

/**
 * @typedef {Object} Category
 * @property {string} name
 * @property {string} id
 */

/**
 * @returns {Promise<Category[]>}
 */
export default {
  getCategories() {
    return fetch(`${BASE_URL}/book-categories`)
      .then((response) => response.json())
      .then((response) => response)
  }
}
