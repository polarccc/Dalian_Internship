import request from '@/utils/request'

// 查询信息查询2列表
export function listBooks2(query) {
  return request({
    url: '/booksys2/books2/list',
    method: 'get',
    params: query
  })
}

// 查询信息查询2详细
export function getBooks2(id) {
  return request({
    url: '/booksys2/books2/' + id,
    method: 'get'
  })
}

// 新增信息查询2
export function addBooks2(data) {
  return request({
    url: '/booksys2/books2',
    method: 'post',
    data: data
  })
}

// 修改信息查询2
export function updateBooks2(data) {
  return request({
    url: '/booksys2/books2',
    method: 'put',
    data: data
  })
}

// 删除信息查询2
export function delBooks2(id) {
  return request({
    url: '/booksys2/books2/' + id,
    method: 'delete'
  })
}
