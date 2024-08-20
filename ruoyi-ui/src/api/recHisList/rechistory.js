import request from '@/utils/request'

// 查询内推历史列表
export function listRechistory(query) {
  return request({
    url: '/recHisList/rechistory/list',
    method: 'get',
    params: query
  })
}

// 查询内推历史详细
export function getRechistory(recLogId) {
  return request({
    url: '/recHisList/rechistory/' + recLogId,
    method: 'get'
  })
}

// 新增内推历史
export function addRechistory(data) {
  return request({
    url: '/recHisList/rechistory',
    method: 'post',
    data: data
  })
}

// 修改内推历史
export function updateRechistory(data) {
  return request({
    url: '/recHisList/rechistory',
    method: 'put',
    data: data
  })
}

// 删除内推历史
export function delRechistory(recLogId) {
  return request({
    url: '/recHisList/rechistory/' + recLogId,
    method: 'delete'
  })
}
