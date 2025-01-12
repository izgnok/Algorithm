SELECT 
    MEMBER_ID, 
    MEMBER_NAME, 
    GENDER, 
    DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d') AS DATE_OF_BIRTH
FROM 
    MEMBER_PROFILE
WHERE 
    MONTH(DATE_OF_BIRTH) = 3
    AND GENDER = 'W' -- 여성 회원을 필터링하는 조건 (예: 'F'가 여성이라는 가정)
    AND TLNO IS NOT NULL -- 전화번호가 NULL이 아닌 경우만 포함
ORDER BY 
    MEMBER_ID ASC;