import request from '@/utils/request'

// 查询信息查询列表
export function listBooks(query) {
  return request({
    url: '/booksys/books/list',
    method: 'get',
    params: query
  })
}

// 查询信息查询详细
export function getBooks(id) {
  return request({
    url: '/booksys/books/' + id,
    method: 'get'
  })
}

// 新增信息查询
export function addBooks(data) {
  return request({
    url: '/booksys/books',
    method: 'post',
    data: data
  })
}

// 修改信息查询
export function updateBooks(data) {
  return request({
    url: '/booksys/books',
    method: 'put',
    data: data
  })
}

// 删除信息查询
export function delBooks(id) {
  return request({
    url: '/booksys/books/' + id,
    method: 'delete'
  })
}
