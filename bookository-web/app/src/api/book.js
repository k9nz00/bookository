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
 * @property {Array} bookContentInfo
 * @property {number} createdAt
 * @property {number} updatedAt
 */
/**
 * @returns {Promise<Book[]>}
 */
export const getBooks = (params) => {
  return fetch(`${ API_HOST }/books?author=${params.author}&name=${params.name}`)
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

/**
 * @param {string} bookId
 * @param {string} bookContentId
 * @returns {Promise<String>}
 */
export const getBookContent = (bookId, bookContentId) => {
  return fetch(`${ API_HOST }/books/${ bookId }/book-content/${ bookContentId }`)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
    .then((response) => response)
}

/**
 * @param {string} bookId
 * @param {string} bookContent
 * @returns {Promise<void>}
 */
export const createBookContent = (bookId, bookContent) => {
  const options = {
    method: 'PUT', body: bookContent
  }

  return fetch(`${ API_HOST }/books/${ bookId }/attach`, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${response.status}`)
      }

      return response.json()
    })
    .then((response) => response)
}
