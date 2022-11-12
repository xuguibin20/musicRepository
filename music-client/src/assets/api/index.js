import { get, post } from "./http.js";

// ------------歌手相关--------------

//查询歌手
export const getAllSinger = () => get(`singer/allSinger`);

// ------------歌曲相关--------------

//添加歌曲
//根据歌手id查询歌曲
// export const addSong =(params) => post(`song/add,params);
//根据歌手id查询歌曲
export const songOfSingerId = (singerId) =>
  get(`song/singer/detail?singerId=${singerId}`);
//根据歌曲id查询歌曲
export const songOfSongId = (songId) =>
  get(`song/songOfSongId?songId=${songId}`);
//根据歌名精确查询歌曲
export const songOfName = (keywords) =>
  get(`song/songOfName?songName=${keywords}`);
//根据歌名模糊查询歌曲
export const likeSongOfName = (keywords) =>
  get(`song/likeSongOfName?songName=${keywords}`);
//查询所有歌曲
export const getAllSong = () => get(`song/allSong`);
// 编辑歌曲信息
export const updateSongMsg = (params) => post(`song/updateSongMsg`, params);
// 删除歌曲
export const deleteSong = (id) => get(`song/delete?id=${id}`);
// 根据主键查询歌曲
export const selectByPrimaryKey = (id) =>
  get(`song/selectByPrimaryKey?id=${id}`);

// ------------歌单相关--------------

//查询歌单
export const getAllSongList = () => get(`songList/allSongList`);
//根据歌单id查询歌曲列表
export const listSongDetail = (songListId) =>
  get(`listSong/detail?songListId=${songListId}`);

// ------------用户相关--------------

//查询用户
export const getAllConsumer = () => get(`consumer/allConsumer`);
//添加用户
export const setConsumer = (params) => post("consumer/add", params);
//注册用户
export const registerConsumer = (params) =>
  post("consumer/registerConsumer", params);
//用户登录
export const loginConsumer = (params) => post("consumer/loginConsumer", params);

// ------------评价相关--------------

//新增评价
export const setRank = (params) => post("rank/add", params);

//获取指定歌单的平均分
export const getRankOfSongListId = (songListId) =>
  get(`rank?songListId=${songListId}`);
