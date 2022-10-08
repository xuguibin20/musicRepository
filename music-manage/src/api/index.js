import {get,post} from '@/api/http.js'

//判断管理员是否登陆成功
export const getLoginStatus = (params) => post(`admin/login/status`, params);

// ---------歌手相关---------
//查询歌手
export const getAllSinger = () => get('singer/allSinger');

//添加歌手
export const setSinger = (params) => post('singer/add', params);

//编辑歌手
export const updateSinger = (params) => post('singer/update', params);

//删除歌手
export const delSinger = (id) => get(`singer/delete?id=${id}`);


// ---------歌曲相关---------
//添加歌曲
// export const setSinger = (params) => post('singer/add', params);

//根据歌手id查询歌曲
export const songOfSingerId = (id) => get(`song/singer/detail?singerId=${id}`);

//编辑歌曲信息
export const updateSongMsg = (params) => post('song/updateSongMsg', params);

//删除歌曲
export const delSong = (id) => get(`song/delete?id=${id}`);