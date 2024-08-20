import request from '@/utils/request'

// 查询新建活动列表
export function listActivities(query) {
  return request({
    url: '/joinactivity/activity/list',
    method: 'get',
    params: query
  })
}
export function listSelected(query) {
  return request({
    url: '/joinactivity/activity/selected/'+query.user_id,
    method: 'get',
    params: query.qf
  })
}
export function listNoSelect(query) {
  return request({
    url: '/joinactivity/activity/noSelect/'+query.user_id,
    method: 'get',
    params: query.qf
  })
}

// 查询新建活动详细
export function getActivities(id) {
  return request({
    url: '/activity/activities/' + id,
    method: 'get'
  })
}

// 新增新建活动
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

// 参加活动
export function joinActivities(data) {
  return request({
    url: '/joinactivity/activity/join',//这个和其他稍微不同
    method: 'post',
    data: data
  })
}

// 删除新建活动
export function delActivities(Obj) {
  return request({
    url: '/joinactivity/activity/' + Obj.uid+"/"+Obj.activity_id,
    method: 'delete'
  })
}
