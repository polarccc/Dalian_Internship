import request from '@/utils/request'

// 查询内推发布列表
export function listRecommendationItem(query) {
  return request({
    url: '/recommendationItem/recommendationItem/list',
    method: 'get',
    params: query
  })
}

// 查询内推发布详细
export function getRecommendationItem(recName,pubName) {
  return request({
    url: '/recommendationItem/recommendationItem/' + recName+'/'+pubName,
    method: 'get'
  })
}

// 新增内推发布
export function addRecommendationItem(data) {
  return request({
    url: '/recommendationItem/recommendationItem',
    method: 'post',
    data: data
  })
}

// 修改内推发布
export function updateRecommendationItem(data) {
  return request({
    url: '/recommendationItem/recommendationItem',
    method: 'put',
    data: data
  })
}

// 删除内推发布
export function delRecommendationItem(recName) {
  return request({
    url: '/recommendationItem/recommendationItem/' + recName,
    method: 'delete'
  })
}
