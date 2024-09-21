export const removeEmptyFieldsFromObject = (rawObject) => {
  return Object.fromEntries(Object.entries(rawObject).filter(([ _, value ]) => {
    return value && value.length
  }))
}
