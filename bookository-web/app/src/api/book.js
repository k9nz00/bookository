import { API_HOST } from '../constants.js'

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


/**
 * @returns {Promise<Book[]>}
 */
export const getBooks = () => {
  return fetch(`${ API_HOST }/books`)
    .then((response) => response.json())
    .then((response) => response)
}

/**
 * @param {string} id
 * @returns {Promise<Book>}
 */
export const getBook = (id) => {
  return fetch(`${ API_HOST }/books/${ id }`)
    .then((response) => response.json())
    .then((response) => response)
}

/**
 * @param {FormData} book
 * @returns {Promise<Book>}
 */
export const createBook = (book) => {
  const options = {
    method: 'POST',
    body: book
  }

  return fetch(`${ API_HOST }/books`, options)
    .then((response) => response.json())
    .then((response) => response)
}

/**
 * @param {FormData} book
 * @returns {Promise<Book>}
 */
export const updateBook = (book) => {
  const options = {
    method: 'PUT',
    body: book
  }

  return fetch(`${ API_HOST }/books/${ book.id }`, options)
    .then((response) => response.json())
    .then((response) => response)
}

/**
 * @param {string} bookId
 * @param {string} bookContentId
 * @returns {Promise<String>}
 */
export const getBookContent = (bookId, bookContentId) => {
  return fetch(`${ API_HOST }/books/${ bookId }/book-content/${ bookContentId }`)
    .then((response) => response.text())
    .then((response) => response)
}

/**
 * @param {string} bookId
 * @param {string} bookContent
 * @returns {Promise<void>}
 */
export const createBookContent = (bookId, bookContent) => {
  const options = {
    method: 'PUT',
    body: bookContent
  }

  return fetch(`${ API_HOST }/books/${ bookId }/attach`, options)
    .then((response) => response.json())
    .then((response) => response)
}
