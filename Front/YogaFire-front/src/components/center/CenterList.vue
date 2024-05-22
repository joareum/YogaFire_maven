<template>
    <div>
      <div class="yogaCenter">
          <table>
          <thead>
            <tr>
              <th v-for="header in headers" :key="header">{{ header }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(row, rowIndex) in rows" :key="rowIndex">
              <td v-for="(cell, cellIndex) in row" :key="cellIndex">
                  <img v-if="cellIndex === 4" src="/map.png" alt="Map Icon" class="map-icon" @click="openModal(row)">
                  <span v-else>{{ cell }}</span>
              </td>
          </tr>
        </tbody>
        </table>
      </div>
      
<CenterMapPopUp v-if="modalData" :data="modalData" @sendClose="closeModalView"/>
    </div>
  </template>
  
  <script>
  import CenterMapPopUp from '@/components/center/CenterMapPopUp.vue'
  export default {
    name: 'CenterList',
    components: {
        CenterMapPopUp,
    },
    data() {
      return {
        modalData: null,
        headers: ['요가원 이름', '요가원 주소', '요가원 전화번호', '요가원 운영 시간', '지도'],
        rows: [
          ['요가,심', '경남 양산시 물금읍 증산역로 135 5층 503호(퍼스트조양)', '0507-1362-4901', '09:00 - 22:00', ''],
          ['현플라잉요가', '경남 양산시 물금읍 야리1길 22 미래타워 506호', '0507-1348-7464', '09:00 - 22:00', ''],
          ['발리요가 & 필라', '경남 양산시 양산역1길 24 5층', '0507-1363-7796', '09:30 - 22:00', ''],
          ['씨엘요가', '경남 양산시 물금읍 야리1길 20 5층 504,505호', '0507-1378-6360', '09:00 - 21:00', ''],
          ['물금플라잉&요가', '경남 양산시 물금읍 물금로 9 더스퀘어 3층 302호', '0507-1363-4699', '09:00 - 22:00', ''],
          ['숨,쉼 요가명상', '대전 서구 문정로 64 솔림빌딩 501호', '042-489-1689', '07:00 - 21:00', ''],
          ['요가솔필라테스 갈마점', '대전 서구 계룡로 394 2층', '010-9524-6235', '09:30 - 22:00', ''],
          ['요가늘 아쉬탕가', '대전 유성구 계룡로74번길 32 3층', '0507-1314-9922', '09:30 - 22:00', ''],
          ['데일리요가앤필라테스', '대전 서구 계룡로 599 한밭새마을금고사옥 7층 704호 705호', '0507-1409-3848' , '08:30 - 22:30', ''],
          ['쉼표요가 유성점', '대전 유성구 원신흥로100번길 37-9 1층 101호', '0507-1387-4551', '06:00 - 22:00', '']
        ]
      };
    },
    methods: {
      openModal(row) {
      console.log("클릭 발생", row)
      console.log('Kakao API Key:', import.meta.env.VITE_KAKAO_API_KEY);
      this.modalData = row;
      console.log("이벤트 끝")
    },
    closeModalView() {
      this.modalData = null;
    },
  }
  };
  </script>
  
  <style scoped>
  .yogaCenter{
    padding-left: 180px;
    padding-right:40px;
  }

  table {
    width: 90%;
    border-collapse: collapse;
    border: 1px solid #444444;

    
  }
  
  th, td {
    border: 1px solid black;
    padding: 8px;
    text-align: center;
  }
  
  th {
    background-color: #f2f2f2;
  }

  .map-icon {
  width: 24px; /* 아이콘 너비 조절 */
  height: 24px; /* 아이콘 높이 조절 */
}
  </style>
  