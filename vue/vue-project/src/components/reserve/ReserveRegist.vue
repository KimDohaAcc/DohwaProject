<template>
  <div class="survey-container">
    <div class="form-group">
      <label for="reserveDate" class="label">예약 날짜</label>
      <input type="date" id="reserveDate" v-model="reserveDate" :min="minDate" class="input-date">
    </div>

    <div class="form-group">
      <label for="reservePhone" class="label">전화번호</label>
      <input type="text" id="reservePhone" v-model="reservePhone" @input="handlePhoneNumberInput" class="input-text">
      <p v-if="showPhoneNumberWarning" class="warning">{{ phoneNumberWarningMessage }}</p>
    </div>

    <div class="form-group" style="margin-bottom: 20px;">
      <h3>선택 지점</h3>
      <div class="location-selection">
        <div class="location-buttons">
          <button
            v-for="(location, index) in locations"
            :key="index"
            @click="selectLocation(location)"
            :class="{ 'selected': selectedLocation === location }"
            @mousedown.prevent
            class="location-button"
          >
            {{ location }}
          </button>
        </div>
      </div>
    </div>

    <div class="form-group" style="margin-bottom: 20px;">
      <h3>선택 시간</h3>
      <div class="time-selection">
        <div class="time-buttons">
          <button
            v-for="(time, index) in times"
            :key="index"
            @click="selectTime(time)"
            :class="{ 'selected': selectedTime === time }"
            @mousedown.prevent
            class="time-button"
          >
            {{ time }}
          </button>
        </div>
      </div>
    </div>

    <div v-if="reservationDetails.length !== 0" class="selected-info">
      <h4>선택한 정보</h4>
      <ul>
        <p v-for="(detail, index) in reservationDetails" :key="index">{{ detail }}</p>
      </ul>
    </div>
    <button :disabled="isCreateDisabled" @click="createReserve" class="create-button">예약하기</button>
  </div>
</template>

<script>
import { useReserveStore } from "@/stores/reserve";

export default {
  setup() {
    const {
      reserveDate,
      reservePhone,
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
      createReserve,
      handlePhoneNumberInput,
    } = useReserveStore();

    return {
      reserveDate,
      reservePhone,
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
      createReserve,
      handlePhoneNumberInput,
    };
  },
};
</script>

<style scoped>
/* 필요한 스타일 작성 */
.survey-container {
  padding: 20px;
}

.form-group {
  margin-bottom: 50px;
}

.label {
  font-weight: bold;
}

.input-date,
.input-text {
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
  width: 100%;
}

.warning {
  color: red;
  font-style: italic;
}

.location-selection,
.time-selection {
  margin-top: 20px;
}

.location-buttons{
  margin-bottom: 80px;
}
.time-buttons {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.location-button,
.time-button {
  padding: 8px 16px;
  margin: 0 8px 10px 0;
  border: 1px solid #ccc;
  border-radius: 20px;
  background-color: #f0f0f0;
  color: #333;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.location-button.selected,
.time-button.selected {
  background-color: #1A2A5F;
  color: white;
  border-color: #1A2A5F;
}

.create-button {
  margin-top: 20px;
  padding: 12px 24px;
  background-color: #1A2A5F;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.create-button:disabled {
  background-color: #e0e0e0;
  cursor: not-allowed;
}

.create-button:hover:enabled {
  background-color: #1A2A5F;
}
</style>
