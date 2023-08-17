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
    return fetch(`${ BASE_URL }/book-categories`)
      .then((response) => response.json())
      .then((response) => response)
  },

  /**
   * @param {Category} category
   * @returns {Promise<Category>}
   */
  createCategory(category) {
    const options = {
      method: 'POST',
      body: category
    }

    return fetch(`${ BASE_URL }/book-categories`, options)
      .then((response) => response.json())
      .then((response) => response)
  },

  /**
   * @param {Category} category
   * @returns {Promise<void>}
   */
  updateCategory(category) {
    const options = {
      method: 'PUT',
      body: category
    }

    return fetch(`${ BASE_URL }/book-categories/${ category.id }`, options)
      .then((response) => response.json())
      .then((response) => response)
  },

  /**
   * @param {number} id
   * @returns {Promise<void>}
   */
  deleteCategory(id) {
    const options = {
      method: 'DELETE'
    }

    return fetch(`${ BASE_URL }/book-categories/${ id }`, options)
      .then((response) => response.json())
      .then((response) => response)
  }
}
