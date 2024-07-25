import { API_HOST } from '../constants.js'

export const getCover = (bookId) => {
  return fetch(`${ API_HOST }/books/${ bookId }/cover`)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Ошибка запроса: статус ${ response.status }`)
      }
      return response.json()
    })
    .then((response) => response.imageData)
    .catch((err) => console.error(err));
}
