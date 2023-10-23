import { shallowMount } from '@vue/test-utils'
import AppButton from './AppButton.vue'


const wrapperFactory = (mountingOptions = {}) => {
  return shallowMount(AppButton, mountingOptions)
}

describe('AppButton.vue', () => {
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
})
