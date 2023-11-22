<template>
  <div>
    
    <StoreIntroduce></StoreIntroduce>
    <div class="container">
    <h1 class="title">지  점</h1>
    <StoreMap></StoreMap>
    <FooterView/>
    </div>
  </div>
</template>

<script setup>
import StoreIntroduce from '@/components/store/StoreIntroduce.vue';
import StoreMap from '@/components/store/StoreMap.vue';
import FooterView from '@/views/FooterView.vue';

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
  left: 0;
  width: 100%;
  background-color: #264653;
  color: white;
  z-index: 9999;
}
.title{
  font-size: 70px;
  text-align: center;
  margin-top: 80px;
  margin-bottom: 80px;
}
</style>





