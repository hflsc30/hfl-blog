import request from '@/utils/request'

export function findArticleList(params) {
  return request.findArticles({
    url: '/articles',
    method: 'get',
    params
  })
}
