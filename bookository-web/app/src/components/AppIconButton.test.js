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

  test('Не должен отрисовывать иконку, если входной параметр не определен', () => {
    wrapper = wrapperFactory()

    expect(wrapper.find('arrow-left-icon-stub').exists()).toBe(false)
  })

  test('Должен отрисовывать иконку в соответствии с входным параметром', () => {
    wrapper = wrapperFactory({
      props: {
        icon: 'ArrowLeftIcon'
      }
    })

    expect(wrapper.find('arrow-left-icon-stub').exists()).toBe(true)
  })
})
