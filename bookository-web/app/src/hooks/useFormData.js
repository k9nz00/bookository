export function useFormData() {
  const appendFormData = (form) => {
    const data = new FormData()

    const entries = Object.entries(form)
    for (const [key, value] of entries) {
      if(value) {
        data.append(key, value)
      }
    }
    return data
  }

  return {
    appendFormData
  }
}
