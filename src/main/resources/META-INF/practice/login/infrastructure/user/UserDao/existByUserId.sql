SELECT
EXISTS (
  SELECT
    account_name
  FROM
    users
  WHERE
    id = /*^ userId */''
)
;