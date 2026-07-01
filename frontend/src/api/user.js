import api from './index'

export const login = (studentId, password) => api.post('/user/login', null, { params: { studentId, password } })
export const register = (data) => api.post('/user/register', data)
export const getProfile = (userId) => api.get('/user/profile', { params: { userId } })
export const updateProfile = (userId, data) => api.put('/user/profile', data, { params: { userId } })
export const follow = (followerId, followingId) => api.post('/user/follow', null, { params: { followerId, followingId } })
export const unfollow = (followerId, followingId) => api.delete('/user/follow', { params: { followerId, followingId } })
export const myQuestions = (userId) => api.get('/user/my-questions', { params: { userId } })
export const addFavorite = (data) => api.post('/user/favorite', data)
export const removeFavorite = (id) => api.delete('/user/favorite/' + id)
export const getFavorites = (userId) => api.get('/user/favorites', { params: { userId } })
