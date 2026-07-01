import api from './index'

export const getResources = (params) => api.get('/resources', { params })
export const uploadResource = (formData) => api.post('/resources/upload', formData, { headers: { 'Content-Type': 'multipart/form-data' } })
export const deleteResource = (id, userId) => api.delete('/resources/' + id, { params: { userId } })
export const getCourses = () => api.get('/admin/courses')
export const homeStats = () => api.get('/admin/stats/home')
export const adminLogin = (username, password) => api.post('/admin/login', null, { params: { username, password } })
export const violationQuestion = (id) => api.put('/admin/questions/' + id + '/violation')
export const addCourse = (data) => api.post('/admin/courses', data)
export const updateCourse = (id, data) => api.put('/admin/courses/' + id, data)
export const deleteCourse = (id) => api.delete('/admin/courses/' + id)
