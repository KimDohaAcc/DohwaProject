
<template>
  <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
        aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="@\assets\덤벨.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h2 class="content">머신에 집중 특화된 GYM!</h2>
          <h3 class="content">헬스판다의 머신 트레이닝은 무게나 회수 등</h3>
          <h3 class="content"> 컨디션의 변화와 효과를 실감할 수 있습니다.</h3>

        </div>
      </div>
      <div class="carousel-item">
        <img src="@\assets\운동여자.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h2 class="content">최고의 자랑은 직원</h2>
          <h3 class="content">1:1 맞춤 트레이닝은 물론 언제나 최적의 환경에서 운동을</h3>

          <h3 class="content">할 수 있도록 매일 클럽 환경 정비에 노력하고 있습니다.</h3>
        </div>
      </div>
      <div class="carousel-item">
        <img src="@\assets\메인3.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h2 class="content">헬스 유저들을 위한 맞춤 카카오톡 채널 운영</h2>
          <h3 class="content">헬스판다는 카카오톡 채널을 통해 여러분의 식단과 운동 관리에 도움을 드립니다.</h3>
        </div>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</template>

<script>



export default {
  components: {

  },
};

</script>

<style>
.row g-5 {
  background-attachment: #264653;
}

.pics {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.wrapper {
  max-width: 600px;
  margin: 0 auto;
}

.carousel {
  width: 100%;
}

.carousel-item {
  width: 100%;
  max-height: 800px;
}

.content {

  font-weight: bold;
  color: white;
}


.carousel-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}



.img-size {
  width: 50px;
  height: fit-content;
}
</style>