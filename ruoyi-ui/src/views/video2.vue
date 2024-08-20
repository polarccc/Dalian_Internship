<template>
    <video-player
		class="video-player vjs-custom-skin"
			  ref="videoPlayer"
			  :playsinline="true"
			  :options="playerOptions" />
</template>

<script>
export default{
    data(){
        return{
            playerOptions: {
                    playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
                    autoplay: true, //如果true,浏览器准备好时开始回放。
                    muted: true, // 默认情况下将会消除任何音频。
                    loop: false, // 导致视频一结束就重新开始。
                    controls:false,//隐藏了控件
                    preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
                    language: 'zh-CN',
                    aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                    fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                    sources: [{
                        // type: "video/mp4",
						// 这里的种类支持很多种：基本视频格式、直播、流媒体等，具体可以参看git网址项目
						// src:"https://v.afbcs.cn/k5ffVM"
						// url地址
                        src: require("@/assets/video/genShinStart.mp4" )
                    }],
                    poster: "https://p1.music.126.net/5zs7IvmLv7KahY3BFzUmrg==/109951163635241613.jpg?param=600y500", // 你的封面地址
                    notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
                    controlBar: {
                        timeDivider: true,
                        durationDisplay: true,
                        remainingTimeDisplay: false,
                        fullscreenToggle: true  // 全屏按钮
                    }
                }
        }
    },
    methods:{
        doPlay(){
            this.$refs.videoPlayer.player.requestFullscreen();
        }
    }
}
</script>

<style>
/* 禁用控件，个人不喜欢这个，会露出进度条 */
/* .vjs-custom-skin > .video-js {
  pointer-events: none
} */

</style>