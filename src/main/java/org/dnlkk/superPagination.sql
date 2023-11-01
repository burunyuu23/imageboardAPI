WITH RankedMessages AS (
    SELECT message_table.*,
           thread_table.id AS thread_tableid,
           thread_table.name AS thread_tablename,
           thread_table.created_date AS thread_tablecreated_date,
           attachment_table.id AS attachment_tableid,
           attachment_table.msg_id AS attachment_tablemsg_id,
           attachment_table.attachment AS attachment_tableattachment,
           reply_table.id AS reply_tableid,
           reply_table.reply_id AS reply_tablereply_id,
           reply_table.msg_id AS reply_tablemsg_id,
           reply_table.id AS reply_tableid,
           reply_table.reply_id AS reply_tablereply_id,
           ROW_NUMBER() OVER (PARTITION BY message_table.id ORDER BY message_table.id) AS rn
    FROM message_table
             LEFT JOIN thread_table ON message_table.thread_id = thread_table.id
             LEFT JOIN attachment_table ON message_table.id = attachment_table.msg_id
             LEFT JOIN reply_table ON message_table.id = reply_table.reply_id
)
   , UniqueRankedMessages AS (
    SELECT *
    FROM RankedMessages
    WHERE rn = 1
    LIMIT 10
)
SELECT *
FROM RankedMessages
WHERE id IN (SELECT id FROM UniqueRankedMessages)