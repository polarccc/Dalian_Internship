import request from '@/utils/request'

// 查询活动列表
export function listActivities(query) {
  return request({
    url: '/activity/activities/list',
    method: 'get',
    params: query
  })
}

// 通过id查询活动
export function getActivities(id) {
  return request({
    url: '/activity/activities/' + id,
    method: 'get'
  })
}

// 新增活动
export function addActivities(data) {
  return request({
    url: '/activity/activities',
    method: 'post',
    data: data
  })
}

// 修改新建活动
export function updateActivities(data) {
  return request({
    url: '/activity/activities',
    method: 'put',
    data: data
  })
}

// 删除新建活动
export function delActivities(id) {
  return request({
    url: '/activity/activities/' + id,
    method: 'delete'
  })
}
