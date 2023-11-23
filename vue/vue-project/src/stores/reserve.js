// useReserveStore.js
import { ref, computed, watch } from 'vue';

import { useUserStore } from '@/stores/user';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'
//일반, 유저권한이 필요할 때 

export function useReserveStore() {
    const reserveDate = ref('');
    const reservePhone = ref('');
    const phoneNumberRegex = /^[0-9]*$/;
    const selectedLocation = ref('');
    const selectedTime = ref('');
    const userStore = useUserStore();
    const lApi = "http://localhost:8080";
    const dApi = "https://healthpanda.site";


    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = `${currentDate.getMonth() + 1}`.padStart(2, '0');
    const currentDay = `${currentDate.getDate()}`.padStart(2, '0');
    const minDate = computed(() => `${currentYear}-${currentMonth}-${currentDay}`);

    const showPhoneNumberWarning = computed(() => {
        return (
            reservePhone.value !== '' &&
            (reservePhone.value.length !== 11 || !reservePhone.value.match(phoneNumberRegex))
        );
    });

    const phoneNumberWarningMessage = computed(() => {
        if (reservePhone.value.length !== 11) {
            return '11자리의 번호만 입력해주세요 (하이픈(-) 제외)';
        } else {
            return '번호만 입력해주세요 (하이픈(-) 제외)';
        }
    });

    const isCreateDisabled = computed(() => {
        return showPhoneNumberWarning.value || reserveDate.value === '' || !selectedLocation || !selectedTime;
    });

    const locations = ['서울', '대전', '부울경', '구미', '광주'];


    function selectLocation(location) {
        selectedLocation.value = location;
        updateReservationDetails();
    }

    const times = ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'];


    function selectTime(time) {
        selectedTime.value = time;
        updateReservationDetails();
    }

    const reservationDetails = ref([]);

    const selectedInfo = ref([]); // 선택한 정보를 저장하는 변수 추가
    function updateReservationDetails() {
        if (selectedLocation.value && selectedTime.value && reservePhone.value && reserveDate.value) {
            reservationDetails.value = [];
            reservationDetails.value.push(`지점: ${selectedLocation.value}`);
            reservationDetails.value.push(`예약 날짜: ${reserveDate.value}`);
            reservationDetails.value.push(`시간대: ${selectedTime.value}`);
            reservationDetails.value.push(`전화번호: ${reservePhone.value}`);
        }
    }

    function updateSelectedInfo() {
        if (selectedLocation.value && selectedTime.value && reservePhone.value && reserveDate.value) {
            selectedInfo.value = reservationDetails.value;
        }
    }
    const reserveDateTime = computed(() => {
        if (reserveDate.value && selectedTime.value) {
            const [hour, minute] = selectedTime.value.split(':');
            const date = new Date(reserveDate.value);
            date.setHours(parseInt(hour) + 9, parseInt(minute), 0, 0); // 시간에 9시간 추가

            const isoString = date.toISOString().split('.')[0];
            return isoString;
        }
        return '';
    });

    const createReserve = () => {
        if (reservationDetails.value.length === 4) {
            const reserveData = {
                time: reserveDateTime.value,
                user: userStore.loginUser,
                phone: reservePhone.value,
                store: selectedLocation.value,
            };
            axiosInstanceWithToken.post(`${lApi}/auth/reserve/create`, reserveData)
                .then(response => {
                    console.log('예약이 성공적으로 생성되었습니다.', response.data);
                    alert("예약이 성공적으로 되었습니다.");
                    // 예약이 성공적으로 생성되었을 때 수행할 작업 추가
                })
                .catch(error => {
                    console.error('예약 생성 중 오류가 발생했습니다.', error);
                    console.log(reserveData);
                    // 오류 발생 시 수행할 작업 추가
                });
        }
    };

    function handlePhoneNumberInput(event) {
        let phoneNumber = event.target.value;
        phoneNumber = phoneNumber.replace(/-/g, '');
        reservePhone.value = phoneNumber.slice(0, 11);
        updateReservationDetails();
    }

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
        updateSelectedInfo,
        reserveDateTime,
        createReserve,
        handlePhoneNumberInput,
        selectedInfo,
        updateReservationDetails,
    };
}