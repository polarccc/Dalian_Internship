<template>
  
    <div class="app-container">
	
  <el-carousel height="400px"  style="width:711px;margin:auto" >
    <el-carousel-item v-for="item in imgArr" :key="item">
        <div  >
		 <img :src="item.url" class="img_style">
		 
		</div>
		<a :href="item.to"><div class="div_style"><p class="p_style">{{ item.msg }}</p></div></a>
    </el-carousel-item>
	
  </el-carousel>

  <video2/>
  
    </div>
	
  </template>

  
  <script>
  import Video3 from '@/views/video3.vue'
import Video2 from '../../video2.vue'
  
  export default {
    components:{
			Video3,
			Video2
		},
	data() {
      return {
	  
		
      currentIndex :0,//当前所在图片下标
		timer:null,//定时轮询
		imgArr:[
					{	id:0,
						url:require('@/assets/images/genshin/1.png'),
						msg:'原神新版本1',
						to:'https://ys.mihoyo.com/',
					},{
						id:1,
						url:require('@/assets/images/genshin/2.jpg'),
						msg:'原神新版本2',
						to:'https://www.miyoushe.com/ys/',
					},{
						id:2,
						url:require('@/assets/images/genshin/3.jpg'),
						msg:'原神新版本3',
						to:'https://sr.mihoyo.com/',
					},{
						id:3,
						url:require('@/assets/images/genshin/4.jpg'),
						msg:'原神新版本4',
						to:'https://bh3.mihoyo.com/main',
					},{
						id:4,
						url:require('@/assets/images/genshin/5.jpg'),
						msg:'原神新版本5',
						to:'https://zzz.mihoyo.com/main/',
					},
				],
      };
    },
   
  }
  </script>

<style>
.el-carousel{
	border-radius: 20px;
	box-shadow: 0 0 20px #7092ce; 
}
/* .el-carousel__item{
	box-shadow: inset 0 0 30px #8505ee; 
} */
  /* .el-carousel__item h3 {
    color: #e81a07;
    font-size: 18px;
    opacity: 0.75; */
    /* line-height: 900px; */
    /* margin: 0;
  } */
  /* ::v-deep .el-carousel__item div {
	position: relative;
	opacity: 0.9;
	color: #8505ee;
	font-size: 18px;
	margin: 0;
	background-color:rgba(255,0,0,0.5);
  } */
  .img_style{
	width: 100%;
  }
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
/* 
  .pic_item {
  position: relative;
  height: 100%;
} */
/* .pic_item:hover{
  cursor: pointer;
} */
.pic_item img {
  width: 100%;
  /* height: 100%; */
}

.pic_item h3 {
  position: absolute;
  left: 20rem;
  bottom: 2rem;
}
 .div_style{
	/* position: relative;
	opacity: 0.9;
	color: #8505ee;
	font-size: 18px;
	margin: 0;
	background-color:rgba(255,0,0,0.5); */
	position:absolute;
	/* margin: auto; */
	/* z-index: 5; */
	left: 0px;
	top: 300px;
	width: 711px; 
	height: 100px;
	font-size: 20px;
	color: rgb(181, 180, 180);
	/* background-color: rgb(77, 88, 92); */
	background: linear-gradient(rgb(120, 120, 122), rgb(109, 142, 175));
	opacity: 0.65;
	border-radius: 0px;
	text-align: center;
 }
 .p_style{
	position: absolute; 
	text-align: center;
	width: 711px; 
	height: 100px;
	color: #262f38;
 }

</style>
  