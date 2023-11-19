-- 데이터 삽입
CREATE TABLE video (
                       video_num INT AUTO_INCREMENT PRIMARY KEY,
                       video_title VARCHAR(50) NOT NULL,
                       video_url VARCHAR(300) NOT NULL,
                       video_sort VARCHAR(20) NOT NULL
);

INSERT INTO video (video_num, video_title, video_sort, video_url) VALUES
                                                                      (1, "전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]", "전신", "https://www.youtube.com/embed/gMaB-fG4u4g"),
                                                                      (2, "하루 15분! 전신 칼로리 불태우는 다이어트 운동", "전신", "https://www.youtube.com/embed/swRNeYw1JkY"),
                                                                      (3, "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]", "상체", "https://www.youtube.com/embed/54tTYO-vU2E"),
                                                                      (4, "상체비만 다이어트 최고의 운동 [상체 핵매운맛]", "상체", "https://www.youtube.com/embed/QqqZH3j_vH0"),
                                                                      (5, "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]", "하체", "https://www.youtube.com/embed/tzN6ypk6Sps"),
                                                                      (6, "저는 하체 식주의자 입니다", "하체", "https://www.youtube.com/embed/u5OgcZdNbMo"),
                                                                      (7, "11자복근 복부 최고의 운동 [복근 핵매운맛]", "복부", "https://www.youtube.com/embed/PjGcOP-TQPE"),
                                                                      (8, "(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)", "복부", "https://www.youtube.com/embed/7TLk7pscICk");

INSERT INTO video (video_num, video_title, video_sort, video_url) VALUES
                                                                      (9, "※5분 복부 운동※ 휴식없이 하는 악마의 복부 운동 (feat. 뱃살, 옆구리살)", "복부", "https://www.youtube.com/watch?v=j-KbcDa8aHc"),
                                                                      (10, "복부운동 짧고 굵게! 운동효율 갑! [6 MINS ABS WORKOUT]", "복부", "https://www.youtube.com/watch?v=sVQqBDBZhmI"),
                                                                      (11, "[ENG] 딱 20분🔥 하루 운동 끝장내는 전신 & 아랫배 & 타바타 올인원 루틴", "전신", "https://www.youtube.com/watch?v=zruKSHfD7b4"),
                                                                      (12, "NO 층간소음 - 고민없이 하나로 끝내는 전신운동 근력 유산소 - 운동 못한 날 죄책감 씻어줄 30분 홈트", "전신", "https://www.youtube.com/watch?v=4kZHHPH6heY"),
                                                                      (13, "상체 다이어트 운동 BEST [팔뚝살/겨드랑이살/가슴어깨/등살]", "상체", "https://www.youtube.com/watch?v=C3TqpGgnIoI"),
                                                                      (14, "하체 날, 딱 10분 밖에 없다면 - 스쿼트 10가지 동작 - 하체운동 홈트 루틴", "하체", "https://www.youtube.com/watch?v=DWYDL-WxF1U"),
                                                                      (15, "하체비만 11자다리 최고의 운동 [하체 핵매운맛]", "하체", "https://www.youtube.com/watch?v=NDsjmxTROEo"),
                                                                      (16, "균형잡힌 탄탄한 상체와 멋진 자세를 위한 덤벨 상체 운동 홈트 22분", "상체", "https://www.youtube.com/watch?v=U7IH4wyVMBU");
