import request from '@/utils/request'

// 查询博文列表
export function listArticles(query) {
  return request({
    url: '/system/articles/list',
    method: 'get',
    params: query
  })
}

// 查询博文详细
export function getArticles(id) {
  return request({
    url: '/system/articles/' + id,
    method: 'get'
  })
}

// 新增博文
export function addArticles(data) {
  return request({
    url: '/system/articles',
    method: 'post',
    data: data
  })
}

// 修改博文
export function updateArticles(data) {
  return request({
    url: '/system/articles',
    method: 'put',
    data: data
  })
}

// 删除博文
export function delArticles(id) {
  return request({
    url: '/system/articles/' + id,
    method: 'delete'
  })
}
