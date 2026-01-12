window.onload=function(){
    var swiper = new Swiper('.swiper-container', {
        /*loop:true,  实现从左到右一直都有数据，*/
          effect: 'coverflow',
          grabCursor: true,/*选项给Swiper用户提供小小的贴心应用，设置为true时，鼠标覆盖Swiper时指针会变成手掌形状，拖动时指针会变成抓手形状*/
          centeredSlides: true,/*改变默认居左，应该居右，图片比较少的时候，会导致感觉拖拽不动*/
          slidesPerView: 'auto',/*设置slider容器能够同时显示的slides数量,'auto'则自动根据slides的宽度来设定数量*/
          coverflowEffect: {
            rotate: 50,/*slide做3d旋转时Y轴的旋转角度*/
            stretch: 0,/*每个slide之间的拉伸值，越大slide靠得越紧。5.3.6 后可使用%百分比*/
            depth: 100,/*slide的位置深度。值越大z轴距离越远，看起来越小。*/
            modifier: 1,/*depth和rotate和stretch的倍率，相当于depth*modifier、rotate*modifier、stretch*modifier，值越大这三个参数的效果越明显。*/
            slideShadows: true,/*是否开启slide阴影*/
          },
    
          pagination: {
            el: '.swiper-pagination',
          },
          nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
    
        });
}