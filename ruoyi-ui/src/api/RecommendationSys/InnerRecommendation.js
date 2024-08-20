import request from '@/utils/request'

// 查询内推历史记录列表
export function listInnerRecommendation(query) {
  return request({
    url: '/RecommendationSys/InnerRecommendation/list',
    method: 'get',
    params: query
  })
}

// 查询内推历史记录详细
export function getInnerRecommendation(recLogId) {
  return request({
    url: '/RecommendationSys/InnerRecommendation/' + recLogId,
    method: 'get'
  })
}

// 新增内推历史记录
export function addInnerRecommendation(data) {
  return request({
    url: '/RecommendationSys/InnerRecommendation',
    method: 'post',
    data: data
  })
}

// 修改内推历史记录
export function updateInnerRecommendation(data) {
  return request({
    url: '/RecommendationSys/InnerRecommendation',
    method: 'put',
    data: data
  })
}

// 删除内推历史记录
export function delInnerRecommendation(recLogId) {
  return request({
    url: '/RecommendationSys/InnerRecommendation/' + recLogId,
    method: 'delete'
  })
}
