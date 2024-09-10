import { API_HOST } from '../constants.js'
import axios from 'axios'

/**
 * @typedef {import('./categories.js').Category}
 */

/**
 * @typedef  BookContentInfo
 * @property {number} id
 * @property {number} bookId
 * @property {string} format
 * @property {string} size
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
 * @property {BookContentInfo[]} bookContentInfo
 * @property {number} createdAt
 * @property {number} updatedAt
 */
/**
 * @returns {Promise<Book[]>}
 */
export const getBooks = async (params) => {
  const response = await axios.get(`${ API_HOST }/books`, { params })
  return response.data
}

/**
 * @param {string} id
 * @returns {Promise<Book>}
 */
export const getBook = (id) => {
  return fetch(`${ API_HOST }/books/${ id }`)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
    .then((response) => response)
}

/**
 * @param {Object} book
 * @returns {Promise<Book>}
 */
export const createBook = (book) => {
  const options = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(book)
  }

  return fetch(`${ API_HOST }/books`, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      // TODO: return response.json()
      return true
    })
    .then((response) => response)
}

/**
 * @param {Object} book
 * @param {string} id
 * @returns {Promise<Book>}
 */
export const updateBook = (id, book) => {
  const options = {
    method: 'PUT', headers: {
      'Content-Type': 'application/json'
    }, body: JSON.stringify(book)
  }

  return fetch(`${ API_HOST }/books/${ id }`, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
    .then((response) => response)
}

/**
 * @param {string} id
 * @returns {Promise<Book>}
 */
export const deleteBook = (id) => {
  const options = {
    method: 'delete'
  }

  return fetch(`${ API_HOST }/books/${ id }`, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
    .then((response) => response)
}

/**
 * @param {Object} cover
 * @param {string} id
 * @returns {Promise<Book>}
 */
export const updateBookCover = (id, cover) => {
  const data = new FormData()
  data.append('cover', cover)

  const options = {
    method: 'PUT', body: data
  }

  return fetch(`${ API_HOST }/books/${ id }/cover`, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
    .then((response) => response)
}
