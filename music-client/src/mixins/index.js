import { likeSongOfName } from "@/assets/api/index.js";

export const mixin = {
    methods: {
        //提示信息
        notify(title, type) {
            this.$notify({
                title: title,
                type: type,
            })
        },
        //获取图片地址
        attachImageUrl(srcUrl) {
            return srcUrl ? this.$store.state.configure.HOST + '/' + srcUrl : '@/assets/img/user.jpg';
        },
        //根据歌名模糊查询歌曲
        getSong() {
            if (!this.$route.query.keywords) {
                this.$store.commit('setListOfSongs', []);
                this.notify('您输入的内容为空', 'warning');
            } else {
                likeSongOfName(this.$route.query.keywords).then((res) => {
                    if (!res.length) {
                        this.$store.commit('setListOfSongs', []);
                        this.notify('系统暂无符合条件的歌曲', 'warning');
                    } else {
                        this.$store.commit('setListOfSongs', res);
                    }
                }).catch((err) => { console.log(err); })
            }
        },
        //获取名字前半部分
        replaceLName() {
            let arr = str.split('-');
            return arr[0];
        }, //获取名字后半部分
        replaceFName() {
            let arr = str.split('-');
            return arr[1];
        },
    }
}