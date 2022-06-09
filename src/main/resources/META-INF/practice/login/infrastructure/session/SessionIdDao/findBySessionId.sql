SELECT
  session_id,
  user_id,
  expire_at
FROM
  session_ids
WHERE
  session_id = /*^ sessionId */''
;