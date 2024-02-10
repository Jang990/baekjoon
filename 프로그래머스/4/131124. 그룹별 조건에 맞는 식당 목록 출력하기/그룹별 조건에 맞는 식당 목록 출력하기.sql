SELECT MP.MEMBER_NAME, RR.REVIEW_TEXT, DATE_FORMAT(RR.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM MEMBER_PROFILE MP
INNER JOIN REST_REVIEW RR ON MP.MEMBER_ID = RR.MEMBER_ID
WHERE MP.MEMBER_ID IN (
    SELECT MEMBER_ID
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
    HAVING COUNT(*) = (
        SELECT COUNT(*)
        FROM REST_REVIEW RR2
        GROUP BY MEMBER_ID
        ORDER BY COUNT(*) DESC
        LIMIT 1
    )
)
ORDER BY RR.REVIEW_DATE, RR.REVIEW_TEXT


