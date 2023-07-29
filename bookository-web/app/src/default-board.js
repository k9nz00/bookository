import { uuid } from './utils'

export default {
  name: 'workshop',
  shelves: [
    {
      id: 'todo',
      name: 'Хочу прочитать',
      type: 'shelf',
      cards: [
        {
          type: 'card',
          description: '',
          name: 'Война и мир',
          id: uuid(),
          shelfId: 'todo',
          color: 'bg-green-200'
        },
        {
          type: 'card',
          description: '',
          name: 'Мёртвые души',
          id: uuid(),
          shelfId: 'todo',
          color: 'bg-green-200'
        },
        {
          type: 'card',
          description: '',
          name: 'Идиот',
          id: uuid(),
          shelfId: 'todo',
          color: 'bg-green-200'
        }
      ]
    },
    {
      id: 'in-progress',
      name: 'Читаю',
      type: 'shelf',
      cards: [
        {
          type: 'card',
          description: '',
          name: 'Гранатовый браслет',
          id: uuid(),
          shelfId: 'in-progress',
          color: 'bg-purple-200'
        }
      ]
    },
    {
      id: 'done',
      name: 'Прочитано',
      type: 'shelf',
      cards: [
        {
          type: 'card',
          description: '',
          name: 'Евгений Онегин',
          id: uuid(),
          shelfId: 'done',
          color: 'bg-sky-300'
        }
      ]
    }
  ]
}
