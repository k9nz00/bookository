import { BASE_URL } from '../constants.js'

/**
 * @typedef {Object} Category
 * @property {string} name
 * @property {string} id
 */

/**
 * @returns {Promise<Category[]>}
 */
export const getCategories = () => {
  return fetch(`${ BASE_URL }/book-categories`)
    .then((response) => response.json())
    .then((response) => response)
}

/**
 * @param {Category} category
 * @returns {Promise<Category>}
 */
export const createCategory = (category) => {
  const options = {
    method: 'POST',
    body: category
  }

  return fetch(`${ BASE_URL }/book-categories`, options)
    .then((response) => response.json())
    .then((response) => response)
}

/**
 * @param {Category} category
 * @returns {Promise<void>}
 */
export const updateCategory = (category) => {
  const options = {
    method: 'PUT',
    body: category
  }

  return fetch(`${ BASE_URL }/book-categories/${ category.id }`, options)
    .then((response) => response.json())
    .then((response) => response)
}

/**
 * @param {number} id
 * @returns {Promise<void>}
 */
export const deleteCategory = (id) => {
  const options = {
    method: 'DELETE'
  }

  return fetch(`${ BASE_URL }/book-categories/${ id }`, options)
    .then((response) => response.json())
    .then((response) => response)
}
