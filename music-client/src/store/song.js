const song = {
  state: {
    isPlay: false, //是否播放中
    url: "", //歌曲地址
    id: "", //歌曲id
    listOfSongs: [], //当前歌曲列表
    playButtonUrl: "#icon-bofang", //播放状态的图标
    duration: 0, //音乐的时长
    curTime: 0, //当前歌曲播放的时间
    changeTime: 0, //指定播放时刻
    songName: "", //歌名
    singerName: "", //歌手名字
    songPic: "", //歌曲图片
    autoNext: true, //当前歌曲播放完毕自动播放下一首
    lyric: [], //歌词
    tempList: {}, //单个歌单信息
    listIndex: null, //当前歌曲在歌单中的位置
    volume: 50, //音量
  },
  getters: {
    volume: (state) => {
      let volume = state.volume;
      if (!volume.length) {
        volume = JSON.parse(window.sessionStorage.getItem("volume") || null);
      }
      return volume;
    },
    listIndex: (state) => {
      let listIndex = state.listIndex;
      if (!listIndex.length) {
        listIndex = JSON.parse(
          window.sessionStorage.getItem("listIndex") || null
        );
      }
      return listIndex;
    },
    tempList: (state) => {
      let tempList = state.tempList;
      if (!tempList.length) {
        tempList = JSON.parse(
          window.sessionStorage.getItem("tempList") || null
        );
      }
      return tempList;
    },
    lyric: (state) => {
      let lyric = state.lyric;
      if (!lyric.length) {
        lyric = JSON.parse(window.sessionStorage.getItem("lyric") || null);
      }
      return lyric;
    },
    autoNext: (state) => {
      let autoNext = state.autoNext;
      if (!autoNext.length) {
        autoNext = JSON.parse(
          window.sessionStorage.getItem("autoNext") || null
        );
      }
      return autoNext;
    },
    songPic: (state) => {
      let songPic = state.songPic;
      if (!songPic.length) {
        songPic = JSON.parse(window.sessionStorage.getItem("songPic") || null);
      }
      return songPic;
    },
    singerName: (state) => {
      let singerName = state.singerName;
      if (!singerName.length) {
        singerName = JSON.parse(
          window.sessionStorage.getItem("singerName") || null
        );
      }
      return singerName;
    },
    songName: (state) => {
      let songName = state.songName;
      if (!songName.length) {
        songName = JSON.parse(
          window.sessionStorage.getItem("songName") || null
        );
      }
      return songName;
    },
    listOfSongs: (state) => {
      let listOfSongs = state.listOfSongs;
      if (!listOfSongs.length) {
        listOfSongs = JSON.parse(
          window.sessionStorage.getItem("listOfSongs") || null
        );
      }
      return listOfSongs;
    },
    changeTime: (state) => {
      let changeTime = state.changeTime;
      if (!changeTime.length) {
        changeTime = JSON.parse(
          window.sessionStorage.getItem("changeTime") || null
        );
      }
      return changeTime;
    },
    curTime: (state) => {
      let curTime = state.curTime;
      if (!curTime.length) {
        curTime = JSON.parse(window.sessionStorage.getItem("curTime") || null);
      }
      return curTime;
    },
    duration: (state) => {
      let duration = state.duration;
      if (!duration.length) {
        duration = JSON.parse(
          window.sessionStorage.getItem("duration") || null
        );
      }
      return duration;
    },

    isPlay: (state) => state.isPlay,
    url: (state) => {
      let url = state.url;
      if (!url) {
        url = JSON.parse(window.sessionStorage.getItem("url") || null);
      }

      return url;
    },
    id: (state) => {
      let id = state.id;
      if (!id) {
        id = JSON.parse(window.sessionStorage.getItem("id") || null);
      }
      return id;
    },

    playButtonUrl: (state) => {
      let playButtonUrl = state.playButtonUrl;
      if (!playButtonUrl) {
        playButtonUrl = JSON.parse(
          window.sessionStorage.getItem("playButtonUrl") || null
        );
      }
      return playButtonUrl;
    },
  },
  mutations: {
    setListOfSongs: (state, listOfSongs) => {
      state.listOfSongs = listOfSongs;
      window.sessionStorage.setItem("listOfSongs", JSON.stringify(listOfSongs));
    },
    //这里的isPlay是外界传过来的值，不是getter里的
    setisPlay: (state, isPlay) => {
      state.isPlay = isPlay;
    },
    setUrl: (state, url) => {
      //这里的url是外界传过来的值，不是getter里的
      state.url = url;
      window.sessionStorage.setItem("url", JSON.stringify(url));
    },
    setId: (state, id) => {
      //这里的id是外界传过来的值，不是getter里的
      state.id = id;
      window.sessionStorage.setItem("id", JSON.stringify(id));
    },
    setplayButtonUrl: (state, playButtonUrl) => {
      //这里的playButtonUrl是外界传过来的值，不是getter里的
      state.playButtonUrl = playButtonUrl;
      window.sessionStorage.setItem(
        "playButtonUrl",
        JSON.stringify(playButtonUrl)
      );
    },
    setDuration: (state, duration) => {
      //这里的durationdurationduration是外界传过来的值，不是getter里的
      state.duration = duration;
      window.sessionStorage.setItem("duration", JSON.stringify(duration));
    },
    setCurTime: (state, curTime) => {
      //这里的curTime是外界传过来的值，不是getter里的
      state.curTime = curTime;
      window.sessionStorage.setItem("curTime", JSON.stringify(curTime));
    },
    setChangeTime: (state, changeTime) => {
      //这里的changeTime是外界传过来的值，不是getter里的
      state.changeTime = changeTime;
      window.sessionStorage.setItem("changeTime", JSON.stringify(changeTime));
    },
    setSongName: (state, songName) => {
      //这里的songName是外界传过来的值，不是getter里的
      state.songName = songName;
      window.sessionStorage.setItem("songName", JSON.stringify(songName));
    },
    setSingerName: (state, singerName) => {
      //这里的singerName是外界传过来的值，不是getter里的
      state.singerName = singerName;
      window.sessionStorage.setItem("singerName", JSON.stringify(singerName));
    },
    setSongPic: (state, songPic) => {
      //这里的songPic是外界传过来的值，不是getter里的
      state.songPic = songPic;
      window.sessionStorage.setItem("songPic", JSON.stringify(songPic));
    },
    setAutoNext: (state, autoNext) => {
      //这里的autoNext是外界传过来的值，不是getter里的
      state.autoNext = autoNext;
      window.sessionStorage.setItem("autoNext", JSON.stringify(autoNext));
    },
    setLyric: (state, lyric) => {
      //这里的lyric是外界传过来的值，不是getter里的
      state.lyric = lyric;
      window.sessionStorage.setItem("lyric", JSON.stringify(lyric));
    },
    setTempList: (state, tempList) => {
      //这里的tempList是外界传过来的值，不是getter里的
      state.tempList = tempList;
      window.sessionStorage.setItem("tempList", JSON.stringify(tempList));
    },
    setListIndex: (state, listIndex) => {
      //这里的listIndex是外界传过来的值，不是getter里的
      state.listIndex = listIndex;
      window.sessionStorage.setItem("listIndex", JSON.stringify(listIndex));
    },
    setVolume: (state, volume) => {
      //这里的volume是外界传过来的值，不是getter里的
      state.volume = volume;
      window.sessionStorage.setItem("volume", JSON.stringify(volume));
    },
  },
};

export default song;
