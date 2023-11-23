
<template>
    <div class="wrapper">
      <div class="control-btns">
        <div class="grid-container">
          <button class="custom-btn">지점 소개</button>
          <button class="custom-btn" @click="toggleMap('서울')">서울</button>
          <button class="custom-btn" @click="toggleMap('대전')">대전</button>
          <button class="custom-btn" @click="toggleMap('광주')">광주</button>
          <button class="custom-btn" @click="toggleMap('구미')">구미</button>
          <button class="custom-btn" @click="toggleMap('부울경')">부울경</button>
        </div>
      </div>
      <div class="map-container" :class="{ 'map-visible': mapVisible }">
        <div ref="container" class="map-content"></div>
      </div>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  
  export default {
    setup() {
      const container = ref(null);
      const map = ref(null);
      const markers = [];
      const mapVisible = ref(false);
      let lastCity = null;
  
      const loadScript = () => {
        const script = document.createElement('script');
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=41f86733b132d1fba2949b424d9de05e`;
        script.addEventListener('load', () => kakao.maps.load());
        document.head.appendChild(script);
      };
  
      const getMarkerPositions = () => {
        return [
          { title: '서울', latlng: new kakao.maps.LatLng(37.501318, 127.039658) },
          { title: '대전', latlng: new kakao.maps.LatLng(36.355416, 127.298413) },
          { title: '광주', latlng: new kakao.maps.LatLng(35.204158, 126.807191) },
          { title: '구미', latlng: new kakao.maps.LatLng(36.108191, 128.413988) },
          { title: '부울경', latlng: new kakao.maps.LatLng(35.095705, 128.856004) }
        ];
      };
  
      const toggleMap = (city) => {
        if (lastCity === city) {
          mapVisible.value = !mapVisible.value;
        } else {
          mapVisible.value = true;
          lastCity = city;
  
          if (!map.value) {
            const options = {
              center: new kakao.maps.LatLng(37.501318, 127.039658),
              level: 5
            };
  
            map.value = new kakao.maps.Map(container.value, options);
          } else {
            markers.forEach((item) => {
              item.setMap(null);
            });
          }
  
          const positions = getMarkerPositions().filter((pos) => pos.title === city);
  
          if (positions.length === 0) {
            console.error('해당 도시의 위치를 찾을 수 없습니다.');
            return;
          }
  
          const imgSrc = '/src/assets/마커.png';
          const imgSize = new kakao.maps.Size(35, 35);
          const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
  
          positions.forEach((position) => {
            const marker = new kakao.maps.Marker({
              map: map.value,
              position: position.latlng,
              title: position.title,
              image: markerImage
            });
            markers.push(marker);
          });
  
          const bounds = positions.reduce(
            (bounds, position) => bounds.extend(position.latlng),
            new kakao.maps.LatLngBounds()
          );
          map.value.setBounds(bounds);
  
          // Scroll map container to center
          const mapElement = document.querySelector('.map-container');
          if (mapElement) {
            const topOffset = mapElement.offsetTop;
            const windowHeight = window.innerHeight;
            const scrollY = topOffset - (windowHeight / 2);
            window.scrollTo({ top: scrollY, behavior: 'smooth' });
          }
        }
  
        // Set the last city as the currently selected city
      };
      
      loadScript();
  
      return {
        container,
        toggleMap,
        mapVisible
      };
    }
  };
  </script>
  
  <style scoped lang="scss">
  .wrapper {
    background: white;
    width: 100%;
    margin: 0; /* wrapper의 마진을 없애는 부분 */
    margin-bottom: 20%;
  }
  .grid-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: -1px; /* 버튼 사이 간격 */
    margin-bottom: 50px;
    align-items: center; /* 수직 가운데 정렬 */
    justify-content: center; /* 수평 가운데 정렬 */
    
  }
  .map-container {
    overflow: hidden;
    transition: height 0.5s;
    height: 0;
    opacity: 0;
    visibility: hidden;
    align-items: center; /* 수직 가운데 정렬 */
  justify-content: center; /* 수평 가운데 정렬 */

  }
  
  .map-visible {
    height: 500px;
    opacity: 1;
    visibility: visible;
    transition: height 1.5s ease, opacity 1.5s ease, visibility 1.5s ease;
  }
  
  .map-content {
  height: 500px;
  display: flex;
  align-items: center; /* 수직 가운데 정렬 */
  justify-content: center; /* 수평 가운데 정렬 */
}
  
  .control-btns {
    margin-top: 20px;
  }
  
  .custom-btn {
    /* 버튼의 스타일을 여기에 추가하세요 */
    padding: 10px 20px;
    border: 1px solid white;
    border-radius: 5px;
    background-color: #1A2A5F;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s, color 0.3s;
    height: 100px;
    font-size: 35px;
    
    width: 22vw;
  }
  
  .custom-btn:hover {
    background-color: #ddd;
    color: #000;
  }
  </style>
  