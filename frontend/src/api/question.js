import api from './index'

export const getQuestions = (params) => api.get('/questions', { params })
export const getQuestionDetail = (id) => api.get('/questions/' + id)
export const publishQuestion = (data) => api.post('/questions', data)
export const answerQuestion = (id, data) => api.post('/questions/' + id + '/answers', data)
export const commentAnswer = (answerId, data) => api.post('/questions/answers/' + answerId + '/comments', data)
export const acceptAnswer = (answerId, userId) => api.put('/questions/answers/' + answerId + '/accept', null, { params: { userId } })
export const likeQuestion = (id, userId) => api.post('/questions/' + id + '/like', null, { params: { userId } })
export const unlikeQuestion = (id, userId) => api.delete('/questions/' + id + '/like', { params: { userId } })
