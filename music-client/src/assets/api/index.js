import {get,post} from "./http.js";


//查询歌手
export const getAllSinger =() => get(`singer/allSinger`);


//根据歌手id查询歌曲
export const songOfSingerId =(id) => get(`song/singer/detail?singerId=${id}`);
//根据歌曲id查询歌曲对象
export const songOfSongId = (id) => get(`song/detail?songId=${id}`);
//根据歌名模糊查询歌曲
export const likeSongOfName = (keywords) => get(`song/likeSongOfName?songName=${keywords}`);
//查询所有歌曲
export const getAllSong = () => get(`song/allSong`);


//查询歌单
export const getAllSongList =() => get(`songList/allSongList`);


//根据歌单id查询歌曲列表
export const listSongDetail = (songListId) => get(`listSong/detail?songListId=${songListId}`);


//查询用户
export const getAllConsumer =() => get(`consumer/allConsumer`);