import { API_HOST } from '../constants.js'

/**
 * @typedef {Object} Category
 * @property {string} name
 * @property {string} id
 */

/**
 * @returns {Promise<Category[]>}
 */
export const getCategories = () => {
  return fetch(`${ API_HOST }/categories`)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
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

  return fetch(`${ API_HOST }/categories`, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
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

  return fetch(`${ API_HOST }/categories/${ category.id }`, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
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

  return fetch(`${ API_HOST }/categories/${ id }`, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
    .then((response) => response)
}
