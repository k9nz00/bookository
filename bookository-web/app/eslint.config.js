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
      'vue/no-unused-vars': 'error',
      'vue/max-attributes-per-line': [ 'warn', {
        'singleline': {
          'max': 2
        }
      } ],
      'vue/singleline-html-element-content-newline': [ 'warn', {
        'ignoreWhenNoAttributes': true,
        'ignoreWhenEmpty': true
      } ],
      'vue/attributes-order': [ 'warn', {
        'order': [
          'DEFINITION',
          'TWO_WAY_BINDING',
          'LIST_RENDERING',
          'CONDITIONALS',
          'RENDER_MODIFIERS',
          'GLOBAL',
          [ 'UNIQUE', 'SLOT' ],
          'OTHER_DIRECTIVES',
          'OTHER_ATTR',
          'CONTENT',
          'EVENTS'
        ],
        'alphabetical': false
      } ]
    },
    languageOptions: {
      globals: {
        describe: false,
        test: false,
        expect: false,
        afterEach: false
      }
    },
    ignores: [ '/dist/', 'config/*', '/node_modules/' ]
  }
]
