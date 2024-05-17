<template>
    <div class="modal">
        <h2>Detail Map</h2>
        <div id="map" class="map-container"></div>
        <button type="button" @click="closeModal"
            class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center">close</button>
    </div>
</template>

<script setup>
import { onMounted, defineProps, defineEmits } from 'vue';

const props = defineProps(['data']);
const emit = defineEmits(['sendClose']);

const closeModal = () => {
    emit('sendClose');
}

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY}&libraries=services`;
    script.addEventListener('load', () => {
      kakao.maps.load(initMap);
    });
    document.head.appendChild(script);
  }
});

const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  const map = new kakao.maps.Map(container, options);

  const geocoder = new kakao.maps.services.Geocoder();

  geocoder.addressSearch(props.data[1], (result, status) => {
    if (status === kakao.maps.services.Status.OK) {
      const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
      const marker = new kakao.maps.Marker({
        map: map,
        position: coords,
      });
      map.setCenter(coords);
    }
  });
};

</script>

<style scoped>
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  z-index: 1000;
}

.modal button {
  float: right;
}

.map-container {
  width: 400px; /* 지도 너비 */
  height: 300px; /* 지도 높이 */
  margin-top: 20px;
}

</style>