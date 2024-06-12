import js from '@eslint/js'
import pluginVue from 'eslint-plugin-vue'

export default [
  js.configs.recommended,
  ...pluginVue.configs['flat/recommended'],
  {
    'rules': {
      'semi': 'off',
      'prefer-const': 'error',
      'eol-last': 'error',
      'indent': [ 'error', 2 ],
      'linebreak-style': [ 'error', 'unix' ],
      'quotes': [ 'error', 'single' ],
      'vue/no-unused-vars': 'error'
    },
    languageOptions: {
      globals: {
        describe: false,
        test: false,
        expect: false,
        afterEach: false
      }
    },
    ignores: ['/dist/', 'config/*', '/node_modules/']
  }
]
