<template>
  <footer>
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
  <script setup>
  
  </script>
  
  <style scoped>
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
    background-color: #264653;
    color: white;
    z-index: 9999;
    flex-flow: column;
    width: 100%;
  }
  
  
  </style>