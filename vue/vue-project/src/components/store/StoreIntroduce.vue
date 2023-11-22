<template>
  <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="@\assets\메인1.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h2>머신에 집중 특화된 GYM!</h2>
          <h3>헬스판다의 머신 트레이닝은 무게나 회수 등 컨디션의 변화와 효과를 실감할 수 있습니다.</h3>
          
        </div>
      </div>
      <div class="carousel-item">
        <img src="@\assets\메인2.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h2>최고의 자랑은 직원</h2>
          <h3>1:1 맞춤 트레이닝은 물론</h3>
            <h3>언제나 최적의 환경에서 운동을 할 수 있도록</h3>
          <h3>매일 클럽 환경 정비에 노력하고 있습니다.</h3>
        </div>
      </div>
      <div class="carousel-item">
        <img src="@\assets\메인3.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h2>헬스 유저들을 위한 맞춤 카카오톡 채널 운영</h2>
          <h3>헬스판다는 카카오톡 채널을 통해 여러분의 식단과 운동 관리에 도움을 드립니다.</h3>
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
  <router-view></router-view>
  <StoreMap />
  <footer class>
    <div class="container py-5">
      <div class="row g-5">
        <div class="col-lg-3 col-md-6">
          <h4 class="mb-4">헬스 판다</h4>
          <p class="mb-2"><i class="fa fa-map-marker-alt text-primary me-3"></i>테헤란로212, 강남구, 서울특별시</p>
          <p class="mb-2"><i class="fa fa-phone-alt text-primary me-3"></i>010 7270 6318</p>
          <p class="mb-2"><i class="fa fa-envelope text-primary me-3"></i>healthPanda@gmail.com</p>

        </div>
        <div class="col-lg-3 col-md-6">
          <h4 class="mb-4">Quick Links</h4>
          <p class="btn btn-link" href="">About Us</p>
          <p class="btn btn-link" href="">Contact Us</p>
          <p class="btn btn-link" href="">Our Services</p>

        </div>
        <div class="col-lg-3 col-md-6">
          <h4 class="mb-4">영업 시간</h4>
          <p class="mb-1">Monday - Friday</p>
          <h6 class="text-light">05:00 am - 12:00 am</h6>
          <p class="mb-1">Saturday</p>
          <h6 class="text-light">05:00 am - 22:00 pm</h6>
          <p class="mb-1">Sunday</p>
          <h6 class="text-light">05:00 am - 22:00 pm</h6>
        </div>
        <div class="col-lg-3 col-md-6">
          <h4 class="mb-4">가맹 문의</h4>
          <p>Dolor amet sit justo amet elitr clita ipsum elitr est.</p>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>

import StoreMap from './StoreMap.vue';

export default {
  components: {
    StoreMap,
  },
};

</script>

<style>
footer .btn.btn-link {
  color: white;
}

.text-primary {
  color: white;
}

#nav-collapse {
  list-style: none;
}
footer {
  background-color: #264653;
  color: white;
  z-index: 9999;
  width: 100%;
  position: relative;
  bottom: 0;
  left: 0;
}
.row g-5{
  background-attachment: #264653;}
  .pics {      /* 전체 케러셀 */
            width: 100%;
            height: 100%;
            /* float: left;
            position: relative;
            left: 50%; */
            object-fit: cover;
        }

.carousel-inner {
            width: 100%;
            height: 100%; /* 이미지 높이 변경 */
        }

.carousel-item {
            width: 100%;
            height: 100%;
        }

.d-block {
            display: block;
            width: auto;
            height: 100%;
        }
  .carousel-caption d-none d-md-block{
    margin-bottom: 200px;
  }

.img-size {
  width: 50px;
  height: fit-content;
}


</style>
