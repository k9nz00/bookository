import { shallowMount } from '@vue/test-utils'
import AppInput from './AppInput.vue'

const wrapperFactory = (mountingOptions = {}) => {
  return shallowMount(AppInput, mountingOptions)
}

describe('AppInput.vue', () => {
  let wrapper

  afterEach(() => {
    wrapper.unmount()
  })

  test('Должен корректно отображать плейсхолдер', async () => {
    wrapper = wrapperFactory({
      props: {
        placeholder: 'Введите данные'
      }
    })

    expect(wrapper.find('input').attributes('placeholder')).toBe('Введите данные')
  })

  test('Должен корректно отображать лейбл', () => {
    wrapper = wrapperFactory({
      props: {
        label: 'Автор'
      }
    })

    expect(wrapper.find('label').text()).contains('Автор')
  })

  describe('Должен корректно отображать значение', () => {
    test('когда значение не передано', () => {
      wrapper = wrapperFactory({
        props: {
          modelValue: ''
        }
      })

      const value = wrapper.find('input').element.value
      expect(value).toBe('')
    })

    test('когда значение передано', () => {
      wrapper = wrapperFactory({
        props: {
          modelValue: 'Преступление и наказание'
        }
      })

      const value = wrapper.find('input').element.value
      expect(value).toBe('Преступление и наказание')
    })
  })
})
