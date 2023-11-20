<template>
  <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="@\assets\판다2.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>유연한 헬스 서비스</h5>
          <p>Some representative placeholder content for the first slide.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="@\assets\판다3.jpg" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>맞춤형 피트니스</h5>
          <p>Some representative placeholder content for the second slide.</p>
        </div>
      </div>
      <div class="carousel-item">
        <img src="@\assets\푸바오.png" class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>다양한 프로그램과 이벤트</h5>
          <p>Some representative placeholder content for the third slide.</p>
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

  bottom: 0;
  left: 0;
  width: 100%;
  background-color: #264653;
  color: white;
  z-index: 9999;
}
.row g-5{
  background-attachment: #264653;
}
</style>
