-- 코드를 입력하세요
SELECT AI.ANIMAL_ID, AI.NAME 
FROM ANIMAL_INS AI, ANIMAL_OUTS AO 
WHERE AI.ANIMAL_ID = AO.ANIMAL_ID AND AI.DATETIME > AO.DATETIME 
ORDER BY AI.DATETIME;