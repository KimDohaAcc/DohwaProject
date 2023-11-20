<template>
  <div>
    <label for="reserveDate">예약 날짜:</label>
    <input type="date" id="reserveDate" v-model="reserveDate" :min="minDate">

    <label for="reservePhone">전화번호:</label>
    <input type="text" id="reservePhone" v-model="reservePhone" @input="handlePhoneNumberInput">
    <p v-if="showPhoneNumberWarning" style="color: red;">
      {{ phoneNumberWarningMessage }}
    </p>

    <!-- 지점 선택 버튼 -->
    <div>
      <button
          v-for="(location, index) in locations"
          :key="index"
          @click="selectLocation(location)"
          :class="{ 'selected': selectedLocation === location }"
          @mousedown.prevent
      >
        {{ location }}
      </button>
    </div>

    <!-- 시간대 선택 버튼 -->
    <div>
      <button
          v-for="(time, index) in times"
          :key="index"
          @click="selectTime(time)"
          :class="{ 'selected': selectedTime === time }"
          @mousedown.prevent
      >
        {{ time }}
      </button>
    </div>

    <button :disabled="isCreateDisabled" @click="createReserve" class="create-button">예약 정보 확인</button>

    <!-- 선택한 정보 표시 -->
    <div v-if="reservationDetails.length !== 0">
      <h3>선택한 정보:</h3>
      <ul>
        <li v-for="(detail, index) in reservationDetails" :key="index">{{ detail }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue';
import { useReserveStore } from "@/stores/reserve";
import axios from 'axios';

export default {
  setup() {
    const {
      reserveDate,
      reservePhone,
      phoneNumberRegex,
      minDate,
      showPhoneNumberWarning,
      phoneNumberWarningMessage,
      isCreateDisabled,
      locations,
      selectedLocation,
      selectLocation,
      times,
      selectedTime,
      selectTime,
      reservationDetails,
      updateReservationDetails,
      reserveDateTime,
      createReserve,
      handlePhoneNumberInput,
    } = useReserveStore();

    return {
      reserveDate,
      reservePhone,
      phoneNumberRegex,
      minDate,
      showPhoneNumberWarning,
      phoneNumberWarningMessage,
      isCreateDisabled,
      locations,
      selectedLocation,
      selectLocation,
      times,
      selectedTime,
      selectTime,
      reservationDetails,
      updateReservationDetails,
      reserveDateTime,
      createReserve,
      handlePhoneNumberInput,
    };
  }
};
</script>

<style scoped>
/* 필요한 스타일 작성 */
button {
  margin-right: 10px;
}

button.selected {
  background-color: lightblue;
  font-weight: bold;
}

/* 예약 생성 버튼 스타일 */
.create-button {
  margin-top: 10px;
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.create-button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}
</style>
