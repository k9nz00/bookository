import { API_HOST } from '../constants.js'

/**
 * @param {string} bookId
 * @param {string} contentId
 * @returns {Promise<void>}
 */
export const getBookContent = (bookId, contentId) => {
  let name
  return fetch(`${ API_HOST }/books/${bookId}/content/${contentId}`, {
    method: 'GET',
    mode: 'cors'
  })
     .then(response => {
       if (!response.ok) {
         throw new Error('Network response was not ok');
       }

       name = decodeURIComponent(response.headers.get('Real-File-Name'))
       return response.blob()
     })
     .then(result => {
       const url = window.URL.createObjectURL(result);
       const a = document.createElement('a')
       a.href = url
       a.download = name
       document.body.appendChild(a)
       a.click()
       a.remove()
       window.URL.revokeObjectURL(url)
     })
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
