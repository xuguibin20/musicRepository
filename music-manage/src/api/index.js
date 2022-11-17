import { get, post } from "@/api/http.js";

// ---------用户相关---------
// 获得所有用户
export const getAllConsumer = () => get("consumer/allConsumer");

//添加用户
export const setConsumer = (params) => post("consumer/add", params);

//编辑用户SongList
export const updateConsumer = (params) => post("consumer/update", params);

//删除用户
export const delConsumer = (id) => get(`consumer/delete?id=${id}`);

//判断管理员是否登陆成功
export const getLoginStatus = (params) => post(`admin/login/status`, params);

// ---------歌手相关---------
//获得所有歌手
export const getAllSinger = () => get("singer/allSinger");

//添加歌手
export const setSinger = (params) => post("singer/add", params);

//编辑歌手SongList
export const updateSinger = (params) => post("singer/update", params);

//删除歌手
export const delSinger = (id) => get(`singer/delete?id=${id}`);

// ---------歌曲相关---------
//添加歌曲
// export const setSinger = (params) => post('singer/add', params);

//获得所有歌曲
export const getAllSong = () => get("song/allSong");

//根据歌手id查询歌曲
export const songOfSingerId = (id) => get(`song/singer/detail?singerId=${id}`);

//编辑歌曲信息
export const updateSongMsg = (params) => post("song/updateSongMsg", params);

//删除歌曲
export const delSong = (id) => get(`song/delete?id=${id}`);

// 根据歌曲id查询歌曲对象
export const songOfSongId = (id) => get(`song/songOfSongId?songId=${id}`);

//根据歌曲名获取歌曲对象
export const songOfSongName = (songName) =>
  get(`song/songOfSongName?songName=${songName}`);
// ---------歌单相关---------
//查询歌单
export const getAllSongList = () => get("songList/allSongList");

//添加歌单
export const setSongList = (params) => post("songList/add", params);

//编辑歌单
export const updateSongList = (params) => post("songList/update", params);

//删除歌单
export const delSongList = (id) => get(`songList/delete?id=${id}`);

// ---------歌单的歌曲相关---------
//根据歌单id查询歌曲列表
export const listSongOfSongListId = (songListId) =>
  get(`listSong/listSongOfSongListId?songListId=${songListId}`);

//给歌单增加歌曲
export const addSongforList = (params) => post("listSong/add", params);

//删除歌单里的歌曲
export const delSongofListSong = (id) => get(`listSong/delete?id=${id}`);
