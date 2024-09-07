import { shallowMount } from '@vue/test-utils'
import AppIconButton from './AppIconButton.vue'

const wrapperFactory = (mountingOptions = {}) => {
  return shallowMount(AppIconButton, mountingOptions)
}

describe('AppIconButton.vue', () => {
  let wrapper

  afterEach(() => {
    wrapper.unmount()
  })

  test('Должен эмитить событие при клике', async () => {
    wrapper = wrapperFactory()

    await wrapper.find('button').trigger('click')

    expect(wrapper.emitted()).toHaveProperty('click')
    expect(wrapper.emitted().click).toHaveLength(1)
  })

  describe('должен корректно показывать иконку', () => {
    test('когда входной параметр не передан', () => {
      wrapper = wrapperFactory()

      expect(wrapper.find('arrow-left-icon-stub').exists()).toBe(false)
    })

    test('когда входной параметр передан правильно', () => {
      wrapper = wrapperFactory({
        props: {
          icon: 'ArrowLeftIcon'
        }
      })

      expect(wrapper.find('arrow-left-icon-stub').exists()).toBe(true)
    })

    test('когда входной параметр передан неправильно', () => {
      wrapper = wrapperFactory({
        props: {
          icon: 'RandomIcon'
        }
      })

      expect(wrapper.find('random-icon-stub').exists()).toBe(false)
    })
  })
})
