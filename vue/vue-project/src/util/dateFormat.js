function useformatDate(date) {
    const options = {
      year: 'numeric',
      month: 'numeric',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
    };
    return new Intl.DateTimeFormat('ko-KR', options).format(date);
  }

  export { useformatDate };
