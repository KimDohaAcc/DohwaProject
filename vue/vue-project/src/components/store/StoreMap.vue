<template>
    <div class="wrapper">
      <div ref="container"></div>
      <div class="control-btns">
        <button @click="displayMarkers">마커 표시</button>
        <button @click="displayMarkersWithInfo">InfoWindow</button>
      </div>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  
  export default {
    setup() {
      const container = ref(null);
      let map = null;
      const markers = [];
  
      const loadScript = () => {
        const script = document.createElement('script');
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=41f86733b132d1fba2949b424d9de05e`;
        script.addEventListener('load', () => kakao.maps.load(initMap));
        document.head.appendChild(script);
      };
  
      const initMap = () => {
        const options = {
          center: new kakao.maps.LatLng(37.2429362, 131.8624647),
          level: 5
        };
        map = new kakao.maps.Map(container.value, options);
      };
  
      const getMarkerPositions = () => {
        return [
          { title: '카카오', latlng: new kakao.maps.LatLng(33.450705, 126.570677) },
          { title: '생태연못', latlng: new kakao.maps.LatLng(33.450936, 126.569477) },
          { title: '텃밭', latlng: new kakao.maps.LatLng(33.450705, 126.570677) },
          { title: '근린공원', latlng: new kakao.maps.LatLng(33.451393, 126.570738) }
        ];
      };
  
      const displayMarkers = () => {
        const positions = getMarkerPositions();
  
        if (markers.length > 0) {
          markers.forEach((item) => {
            item.setMap(null);
          });
        }
  
        const imgSrc = '/src/assets/map/markerStar.png';
        const imgSize = new kakao.maps.Size(24, 35);
        const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
  
        positions.forEach((position) => {
          const marker = new kakao.maps.Marker({
            map,
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
        map.setBounds(bounds);
      };
  
      onMounted(() => {
        if (window.kakao?.maps) {
          initMap();
        } else {
          loadScript();
        }
      });
  
      return {
        container,
        displayMarkers
      };
    }
  };
  </script>
  
  <style scoped lang="scss">
  .wrapper {
    background: greenyellow;
    height: 100%;
    width: 100%;
  
    & div:first-child {
      width: 100%;
      height: 500px;
    }
  }
  </style>
  